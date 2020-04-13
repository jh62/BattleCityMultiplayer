package com.battlecity.main.entity.tiles;

import com.badlogic.gdx.math.Rectangle;
import com.battlecity.main.entity.Bullet;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.global.Presets;
import com.battlecity.main.global.enums.Tier;
import java.util.LinkedList;



/**
 *
 * @author Pablis
 */
public class Tile extends Entity
{

    public static enum TileType
    {
        AIR("x"),
        BRICKS("w"),
        WALL("s"),
        EAGLE("e"),
        EAGLE_WALL("eW"),
        SPAWN("*"),
        ICE("i"),
        TREES("t"),
        WATER("W");

        public final String id;

        TileType(String id) {
            this.id = id;
        }
    }

    public static enum RegionType
    {
        FULL("F"),
        HALF_LEFT("HL"),
        HALF_RIGHT("HR"),
        HALF_UP("HU"),
        HALF_DOWN("HD"),
        SINGLE_UPPER_LEFT("SUL"),
        SINGLE_UPPER_RIGHT("SUR"),
        SINGLE_BOTTOM_LEFT("SBL"),
        SINGLE_BOTTOM_RIGHT("SBR");

        public final String id;

        RegionType(String id) {
            this.id = id;
        }
    }

    public int tileID;

    private TileType tileType = TileType.AIR;
    private RegionType regionType = RegionType.FULL;
    boolean isAlive = true;

    private final LinkedList<TileRegion> tileRegions = new LinkedList<>();

    Tile() {

    }

    /**
     * Creates a basic tile.
     *
     * @param x
     * @param y
     */
    public Tile(float x, float y) {
        this(x, y, TileType.AIR, RegionType.FULL);
    }

    /**
     * Creates a tile with a specific material and type.
     *
     * @param x
     * @param y
     * @param tyleType what kind of tile this is.
     * @param regionType the type of tile. This determines the amount of tile
     * regions generated.
     */
    public Tile(float x, float y, TileType tyleType, RegionType regionType) {
        super(x, y, Presets.TILE_FULL_SIZE, Presets.TILE_FULL_SIZE);
        this.tileType = tyleType;
        this.regionType = regionType;
        generateBounds();
    }

    public void destroy() {
        for(TileRegion r : getRegions()) {
            r.destroy();
        }
    }

    public void setRegionType(RegionType regionType) {
        this.regionType = regionType;
    }

    public TileType getTileType() {
        return this.tileType;
    }

    public void setTileType(TileType type) {
        this.tileType = type;
    }

    public Tier getTier() {
        switch(getTileType()) {
            case WALL: {
                return Tier.FOUR;
            }
            default: {
                return Tier.FIRST;
            }
        }
    }

    public RegionType getBoundsType() {
        return this.regionType;
    }

    public LinkedList<TileRegion> getRegions() {
        return tileRegions;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public boolean onCollision(Entity attacker) {

        if(this.tileType == TileType.ICE || this.tileType == TileType.TREES) {
            return false;
        }

        if(this.tileType == TileType.WATER) {
            return !(attacker instanceof Bullet);
        }

        if(this.getTileType() == TileType.WALL) {
            if(!(attacker instanceof Bullet)) {
                return true;
            }
            else if((((Bullet)attacker).getTier() != Tier.FOUR)) {
                return true;
            }
        }

        final Rectangle attackerBounds = attacker.getBounds();
        boolean collision = false;
        int dam = 0;

        for(TileRegion region : tileRegions) {
            if(region.isDestroyed()) {
                dam++;
                continue;
            }

            Rectangle tBounds = region.getBounds();

            if(attackerBounds.overlaps(tBounds)) {
                if(attacker instanceof Bullet) {
                    collision = true;

                    if(((Bullet)attacker).getTier() == Tier.FOUR) {
                        if(this.getTileType() == TileType.WALL) {
                            region.damage(attacker.getFacing());
                        }
                        else {
                            region.destroy();
                        }
                    }
                    else {
                        region.damage(attacker.getFacing());
                    }
                }
                else {
                    return true;
                }
            }
        }

        if(dam >= tileRegions.size()) {
            this.isAlive = false;
        }

        return collision;
    }

    private void generateBounds() {

        byte regionNumber = 0;
        float offsetX = 0;
        float offsetY = 0;
        float size = 8;

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                Rectangle trBounds = new Rectangle(x + offsetX, y + offsetY,
                                                   size,
                                                   size);
                TileRegion tr = new TileRegion(trBounds, regionNumber);
                tileRegions.add(tr);
                offsetX += size;
                regionNumber++;
            }

            offsetX = 0;
            offsetY += size;
        }

        int[] remove = null;

        switch(regionType) {
            case HALF_DOWN: {
                remove = new int[]{2, 3};
                break;
            }
            case HALF_UP: {
                remove = new int[]{0, 1};
                break;
            }
            case HALF_LEFT: {
                remove = new int[]{1, 3};
                break;
            }
            case HALF_RIGHT: {
                remove = new int[]{0, 2};
                break;
            }
            case SINGLE_UPPER_RIGHT: {
                remove = new int[]{0, 1, 2};
                break;
            }
            case SINGLE_UPPER_LEFT: {
                remove = new int[]{0, 1, 3};
                break;
            }
            case SINGLE_BOTTOM_LEFT: {
                remove = new int[]{1, 2, 3};
                break;
            }
            case SINGLE_BOTTOM_RIGHT: {
                remove = new int[]{0, 2, 3};
                break;
            }
            case FULL: {
                this.bounds.set(this.x, this.y, this.width, this.height);
                return;
            }
        }

        if(remove != null) {
            for(int i = (remove.length - 1); i >= 0; i--) {
                int idx = remove[i];
                tileRegions.remove(idx);
            }
        }

        float tileX = tileRegions.getFirst().bounds.x;
        float tileY = tileRegions.getFirst().bounds.y;
        float tileWidth = tileX - (tileRegions.getLast().bounds.x +
                                   tileRegions.getLast().bounds.width);
        float tileHeight = tileY - (tileRegions.getLast().bounds.y +
                                    tileRegions.
                                    getLast().bounds.height);

        this.bounds.set(tileX, tileY, Math.abs(tileWidth), Math.abs(tileHeight));
    }

    @Override
    public Rectangle getBounds() {
        return this.bounds;
    }

}
