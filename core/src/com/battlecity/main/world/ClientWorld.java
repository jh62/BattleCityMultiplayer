package com.battlecity.main.world;

import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Rectangle;
import com.battlecity.main.assets.*;
import com.battlecity.main.entity.Bullet;
import com.battlecity.main.entity.DynamicEntity;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.tiles.Eagle;
import com.battlecity.main.entity.tiles.Tile;
import com.battlecity.main.global.*;
import com.battlecity.main.levels.*;
import com.battlecity.main.net.Server;
import java.util.ArrayList;



/**
 *
 * @author Pablis
 */
public class ClientWorld extends World
{

    private ArrayList<Entity> entities;

    public ClientWorld() {
        this.isWorldInitiated = false;
        this.entities = new ArrayList<>(30);
    }

    @Override
    public void changeLevel(BaseLevel l) {
        super.changeLevel(l);
        this.entities.clear();

        int levelID = l.getLevelID();
        this.isWorldInitiated = (levelID == LevelEnd.LEVEL_END_ID);

        if(levelID != LevelEnd.LEVEL_END_ID) {
            AssetsGame.manager().soundManager.getSound("stageStart").play();
            Parameters.mode = l.getMode();
        }
        else {
            AssetsGame.manager().soundManager.getSound("gameOver").play();
        }
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public void initWorld() {
        this.isWorldInitiated = true;
    }

    @Override
    public boolean isGameOver() {
        BaseLevel.Mode mode = getCurrentLevel().getMode();
        Entity e = getCurrentLevel().getEagle();
        return mode == BaseLevel.Mode.ARCADE && e != null && !e.isAlive();
    }

    @Override
    public void update() {
        for(Entity entity : this.entities) {

            if(!entity.isAlive()) {
                continue;
            }

            if(entity instanceof Bullet) {

                if(((Bullet)entity).getState() != DynamicEntity.State.MOVING) {
                    continue;
                }

                /**
                 * Simulates on the client moving the entity in the current
                 * direction. Server-Client share the same instance of a level,
                 * so the code should only run in a pure client environment.
                 */
                if(!Server.isServerRunning()) {
                    ((Bullet)entity).moveEntity();
                }

                Rectangle dEntityBounds = entity.getBounds();

                if(this.worldBounds.contains(dEntityBounds)) {

                    for(Tile t : getCurrentLevel().tileList) {

                        if(!t.isAlive()) {
                            continue;
                        }

                        if(t instanceof Eagle) {
                            continue;
                        }

                        if(dEntityBounds.overlaps(t.getBounds())) {
                            boolean canMove = !(t.onCollision(entity));

                            if(!canMove) {
                                t.update();
                                getCurrentLevel().damagedTileList.add(t);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void addEntity(Entity e) {
        this.entities.add(e);
    }

    @Override
    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

}
