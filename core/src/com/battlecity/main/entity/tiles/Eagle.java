package com.battlecity.main.entity.tiles;

import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.Bullet;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.global.enums.Event;
import com.battlecity.main.net.Server;

/**
 *
 * @author JH62
 */
public class Eagle extends Tile {

    private int[] walls;

    public Eagle(float x, float y) {
        super(x, y);
        setTileType(TileType.EAGLE);
        setRegionType(RegionType.FULL);
    }

    public void kill() {
        this.isAlive = false;
    }

    void setWalls(int[] walls) {
        this.walls = walls;
    }

    @Override
    public boolean onCollision(Entity attacker) {
        return this.isAlive;
    }

    @Override
    public void onDammageBy(Entity attacker) {
        if (attacker instanceof Bullet) {
            this.kill();
            AssetsGame.manager().soundManager.getSound("eagleDead").play();
            Server.getInstance().sendEventToAll(Event.ON_WORLD_GAMEOVER, -1);
        }
    }
}
