package com.battlecity.main.render;

import com.battlecity.main.entity.Bullet;
import com.battlecity.main.entity.tank.EnemyTank;
import com.battlecity.main.entity.tank.PlayerTank;
import com.battlecity.main.entity.tiles.BaseWall;
import com.battlecity.main.entity.tiles.Eagle;
import com.battlecity.main.events.powers.PowerUP;
import com.battlecity.main.entity.tiles.Tile;
import com.battlecity.main.events.powers.*;
import com.battlecity.main.levels.BaseLevel;
import java.util.HashMap;

/**
 *
 * @author Pablis
 */
public abstract class RenderRegistry {

    private static boolean initialized = false;

    private static final HashMap<Class<? extends IRenderable>, Render> RENDER_REGISTRY
            = new HashMap<>();

    public static void initialize() {
        if (!initialized) {
            RenderRegistry.add(BaseLevel.class, new LevelRender());
            RenderRegistry.add(Tile.class, new TileRenderer());
            RenderRegistry.add(BaseWall.class, new TileRenderer());
            RenderRegistry.add(PlayerTank.class, new PlayerTankRender());
            RenderRegistry.add(EnemyTank.class, new EnemyTankRender());
            RenderRegistry.add(PowerUP.class, new PowerUpRenderer());
            RenderRegistry.add(PowerHelmet.class, new PowerUpRenderer());
            RenderRegistry.add(PowerShovel.class, new PowerUpRenderer());
            RenderRegistry.add(PowerGrenade.class, new PowerUpRenderer());
            RenderRegistry.add(PowerTank.class, new PowerUpRenderer());
            RenderRegistry.add(PowerStar.class, new PowerUpRenderer());
            RenderRegistry.add(PowerClock.class, new PowerUpRenderer());
            RenderRegistry.add(PowerCloak.class, new PowerUpRenderer());
            RenderRegistry.add(PowerBounce.class, new PowerUpRenderer());
            RenderRegistry.add(PowerHealth.class, new PowerUpRenderer());
            RenderRegistry.add(Bullet.class, new BulletRenderer());
            RenderRegistry.add(Eagle.class, new EagleRenderer());
            initialized = true;
        }
    }

    public static void add(
            Class<? extends IRenderable> object,
            Render renderclass) {
        RENDER_REGISTRY.put(object, renderclass);
    }

    public static Render getRenderer(
            Class<? extends IRenderable> object) {
        return RENDER_REGISTRY.get(object);
    }
}
