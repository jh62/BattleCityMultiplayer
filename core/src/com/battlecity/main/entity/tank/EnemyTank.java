package com.battlecity.main.entity.tank;

import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.*;
import com.battlecity.main.entity.tiles.Tile;
import com.battlecity.main.events.TimerManager.Task;
import com.battlecity.main.global.Presets;
import com.battlecity.main.global.enums.Event;
import com.battlecity.main.global.enums.Tier;
import com.battlecity.main.net.Client;
import com.battlecity.main.net.Server;
import com.battlecity.main.world.*;



/**
 *
 * @author JH62
 */
public class EnemyTank extends Tank
{

    transient boolean isPowerUpTank = false;
    private boolean powerUpEffectShow = false;
    private int damage = 0;

    EnemyTank() {

    }

    public EnemyTank(float x, float y) {
        this(x, y, Tier.FIRST);
    }

    public EnemyTank(float x, float y, Tier tier) {
        this(x, y, tier, false);
    }

    public EnemyTank(float x, float y, Tier tier, boolean isPowerUpTank) {
        super(x, y, SIDE.ENEMY);
        setTier(tier);
        this.isPowerUpTank = isPowerUpTank;

        switch(tier) {
            case FIRST: {
                this.veloticy = Presets.ENTITY_VELOCITY_SLOW;
                break;
            }
            case SECOND: {
                this.veloticy = Presets.ENTITY_VELOCITY_FAST;
                break;
            }
            default: {
                this.veloticy = Presets.ENTITY_VELOCITY_NORMAL;
                break;
            }
        }
    }

    public int getDamage() {
        return damage;
    }

    public int getMaxDamage() {
        return tier == Tier.FOUR ? 4 : 0;
    }

    public void setIsPowerUpTank(boolean isPowerTank) {
        this.isPowerUpTank = isPowerTank;
    }

    public boolean getIsPowerUpTank() {
        return this.isPowerUpTank;
    }

    public boolean powerEffectShowing() {
        return this.powerUpEffectShow;
    }

    @Override
    public void setState(State state) {
        super.setState(state);

        switch(state) {
            case RESPAWNING: {
                final Task task_respawn = new Task()
                {
                    @Override
                    public void run() {
                        setState(State.MOVING);
                    }
                };

                float duration = AssetsGame.manager().animations.get("respawn").
                        getAnimationDuration() * 1000;
                World.getTimerManager().scheduleTask(task_respawn,
                                                     (int)duration);

                break;
            }
            case EXPLODING: {
                final Task task_explode = new Task()
                {
                    @Override
                    public void run() {
                        setState(State.EXPLODED);
                    }
                };

                float duration = AssetsGame.manager().animations.
                        get("explosion").getAnimationDuration() * 1000;
                World.getTimerManager().scheduleTask(task_explode,
                                                     (int)duration);
                break;
            }
            case EXPLODED: {
                final Task task_showScore = new Task()
                {
                    @Override
                    public void run() {
                        setState(State.DEAD);
                    }
                };
                World.getTimerManager().scheduleTask(task_showScore, 500);
                break;
            }
        }
    }

    @Override
    public void onSpawn() {
        super.onSpawn();

        setFacing(Facing.DOWN);
        setState(State.RESPAWNING);

        World.getTimerManager().scheduleTask(moveAI, 5000, 5000);
        World.getTimerManager().scheduleTask(shootAI, 600, 600);

        if(isPowerUpTank) {
            World.getTimerManager().scheduleTask(powerUpBlinking, 250, 250);
        }
    }

    @Override
    public void onDammageBy(Entity attacker) {

        if(attacker instanceof Bullet) {

            if(((Bullet)attacker).getSide() == this.
               getSide()) {
                return;
            }

            this.damage++;

            if(isPowerUpTank) {
                isPowerUpTank = false;
                ((ServerWorld)Server.getInstance().getWorld()).spawnPowerUp();
            }

            if(this.damage >= getMaxDamage()) {
                this.setState(State.EXPLODING);
                PlayerTank pTank = (PlayerTank)((Bullet)attacker).getOwner();
                pTank.onEnemyKilled(this.tier);
            }
            else {
                Server.getInstance().sendEventToAll(Event.ON_ENEMY_HIT, -1);
            }
        }
    }

    @Override
    public void setFacing(Facing facing) {
        super.setFacing(facing);
    }

    @Override
    public void onShooting() {
        super.onShooting();

        if(this.bullet != null) {
            switch(tier) {
                case FIRST: {
                    this.bullet.setVeloticy(Presets.BULLET_SLOW);
                    break;
                }
                case SECOND: {
                    this.bullet.setVeloticy(Presets.BULLET_NORMAL);
                    break;
                }
                case THIRD: {
                    this.bullet.setVeloticy(Presets.BULLET_FAST);
                    break;
                }
                case FOUR: {
                    this.bullet.setVeloticy(Presets.BULLET_NORMAL);
                    break;
                }
            }
        }
    }

//    public void pickRandomDirection() {
//        double chance = Math.random();
//
//        if(chance > 0.75) {
//            this.setFacing(Facing.RIGHT);
//        }
//        else if(chance > 0.5) {
//            this.setFacing(Facing.UP);
//        }
//        else if(chance > 0.25) {
//            this.setFacing(Facing.LEFT);
//        }
//        else {
//            this.setFacing(Facing.DOWN);
//        }
//    }

    public void headTowardsBase() {
        Tile eagle = Client.instance().getWorld().getCurrentLevel().getEagle();

        if(eagle == null)
            return;

        boolean right = getX() > eagle.getX();
        boolean above = getY() > eagle.getY();

        if(right) {
            setFacing(Facing.LEFT);
        }
        else {
            setFacing(Facing.RIGHT);
        }

        if(above) {
            setFacing(Facing.DOWN);
        }
        else {
            setFacing(Facing.UP);
        }
    }

    transient private final Task shootAI = new Task()
    {
        @Override
        public void run() {

            if(!isAlive()) {
                this.cancel();
                return;
            }

            if(getState() != State.MOVING) {
                if(getBullet() != null) {
                    getBullet().setState(State.DEAD);
                }
                return;
            }

            if(isFrozen()) {
                return;
            }

            setAction(Action.SHOOTING);
        }
    };

    transient private final Task moveAI = new Task()
    {

        @Override
        public void run() {
            if(!isAlive()) {
                this.cancel();
                return;
            }

            if(getState() != State.MOVING) {
                return;
            }

            if(isFrozen()) {
                return;
            }

            double r = Math.random();

            if(0.9 > r) {
                headTowardsBase();
            }
            else {
                pickRandomDirection();
            }
        }
    };

    transient private final Task powerUpBlinking = new Task()
    {
        @Override
        public void run() {
            if(!isAlive()) {
                this.cancel();
                return;
            }

            if(getDamage() > 0) {
                powerUpEffectShow = false;
            }
            else {
                powerUpEffectShow = !(powerUpEffectShow);
            }
        }
    };

}
