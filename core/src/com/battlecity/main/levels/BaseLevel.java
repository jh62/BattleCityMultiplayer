package com.battlecity.main.levels;

import com.badlogic.gdx.utils.*;
import com.battlecity.main.entity.tiles.BaseWall;
import com.battlecity.main.entity.tiles.Eagle;
import com.battlecity.main.entity.tiles.Tile;
import com.battlecity.main.entity.tiles.Tile.TileType;
import com.battlecity.main.entity.tiles.Tile.RegionType;
import com.battlecity.main.global.Parameters;
import com.battlecity.main.global.Presets;
import com.battlecity.main.net.*;
import com.battlecity.main.render.IRenderable;
import java.util.*;



/**
 *
 * @author Pablis
 */
public abstract class BaseLevel implements IRenderable
{
    public enum Mode
    {
        DEATHMATCH,
        ARCADE;
    }

    /**
     * The number this startingLevel is in the levels list.
     *
     * @return
     */
    public abstract int getLevelID();

    /**
     * An array that contains the enemy spawns.
     *
     * 1 - Basic tank 2 - Fast tank 3 - Power tank 4 - Armor tank
     *
     * @return
     */
    abstract int[] getLevelSpawnlistData();

    public final Array<Tile> tileList;
    public final Array<Tile> emtpyTileList;
    public final Set<Tile> damagedTileList;
    public final Array<Tile> baseWallsTiles;
    public final Array<Tile> specialTilesList;
    public final Array<Tile> enemySpawns;
    public final Array<Tile> playerSpawns;

    public static final ArrayList<LevelMod> userLevels = new ArrayList();

    abstract String[][] getTileArray();

    Tile eagle;
    private Mode mode = Mode.ARCADE;

    public BaseLevel() {
        this.eagle = null;
        this.playerSpawns = new Array<>(2);
        this.enemySpawns = new Array<>(3);
        this.specialTilesList = new Array<>();
        this.baseWallsTiles = new Array<>(
                5);
        this.damagedTileList = new HashSet<>(4);
        this.emtpyTileList = new Array<>();
        this.tileList = new Array<>(
                Presets.MAX_TILE_HORIZONTAL * Presets.MAX_TILE_VERTICAL);
    }

    public void init() {
        ArrayList<Tile> map = this.generateTiles();

        for(Tile tile : map) {
            if(playerSpawns.size < 2) {
                if(tile.tileID == 160 || tile.tileID == 164) {
                    playerSpawns.add(tile);

                    if(tile.getTileType() != TileType.AIR && tile.getTileType() != TileType.ICE && tile.getTileType() != TileType.TREES) {
                        tile.setTileType(TileType.AIR);
                    }
                }
            }

            if(enemySpawns.size < 3) {
                if(tile.tileID == 0 || tile.tileID == 6 || tile.tileID == 12) {
                    enemySpawns.add(tile);

                    if(tile.getTileType() != TileType.AIR && tile.getTileType() != TileType.ICE && tile.getTileType() != TileType.TREES) {
                        tile.setTileType(TileType.AIR);
                    }
                }
            }

            switch(tile.getTileType()) {
                case EAGLE:
                    this.eagle = tile;
                    tileList.add(tile);
                    break;
                case EAGLE_WALL:
                    baseWallsTiles.add(tile);
                    tileList.add(tile);

                    if(getLevelID() == LevelEnd.LEVEL_END_ID)
                        ((BaseWall)tile).setProtected(true);
                    break;
                case TREES:
                    specialTilesList.add(tile);
                // fall through
                case AIR:
                case ICE:
                    emtpyTileList.add(tile);
                    break;
                default:
                    tileList.add(tile);
                    break;
            }

        }

        if(this.getEagle() == null)
            setMode(Mode.DEATHMATCH);
    }

    private ArrayList<Tile> generateTiles() {
        String[][] tempTiles = getTileArray();
        ArrayList<Tile> tileArray = new ArrayList<>(
                Presets.MAX_TILE_HORIZONTAL * Presets.MAX_TILE_VERTICAL);

        int x = 0;
        int y = Presets.TILE_FULL_SIZE * (tempTiles.length - 1);
        int id = 0;

        for(int row = 0; row < tempTiles.length; row++) {
            for(int cell = 0; cell < tempTiles[row].length; cell++) {
                String[] tileDesc = tempTiles[row][cell].split(":");

                String string_material = tileDesc[0];
                String string_region = tileDesc[1];

                boolean isSpawner = string_material.contains("*");

                TileType tileType = getTileType(string_material.replaceAll("[*]", ""));
                RegionType regionType = getRegionType(string_region);

                Tile t = null;

                if(mode == Mode.ARCADE) {
                    switch(tileType) {
                        case EAGLE:
                            t = new Eagle(x, y);
                            break;
                        case EAGLE_WALL:
                            t = new BaseWall(x, y, regionType);
                            break;
                        default:
                            break;
                    }
                }

                if(t == null)
                    t = new Tile(x, y, tileType, regionType);

                if(mode == Mode.DEATHMATCH && isSpawner)
                    playerSpawns.add(t);

                t.tileID = id++;
                tileArray.add(t);

//                if(isSpawner || t.getTileType() == TileType.SPAWN) {
//                    playerSpawns.add(t);
//                }
                x += Presets.TILE_FULL_SIZE;
            }

            x = 0;
            y -= Presets.TILE_FULL_SIZE;
        }

        return tileArray;
    }

    public void dispose() {
        tileList.clear();
        damagedTileList.clear();
        baseWallsTiles.clear();
        playerSpawns.clear();
        enemySpawns.clear();
        eagle = null;
        System.out.println("Level disposed.");
    }

    public Tile getEagle() {
        return this.eagle;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    private TileType getTileType(String chars) {
        TileType material = TileType.AIR;

        if(chars != null && chars.length() > 0) {
            for(TileType t : TileType.values()) {
                if(chars.equals(t.id)) {
                    material = t;
                    break;
                }
            }
        }

        return material;
    }

    private RegionType getRegionType(String chars) {
        RegionType type = RegionType.FULL;

        if(chars != null && chars.length() > 0) {
            for(RegionType t : RegionType.values()) {
                if(chars.equals(t.id)) {
                    type = t;
                    break;
                }
            }
        }

        return type;
    }

    public int[] getSpawnList() {
        int[] array = Parameters.randomEnemies ? ShuffleArray(
                      getLevelSpawnlistData()) : getLevelSpawnlistData();
        return array;
    }

    private int[] ShuffleArray(int[] array) {
        int index, temp;
        Random random = new Random();
        for(int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }

        return array;
    }

    public Tile getRandomEmptyTile(BaseLevel.Mode mode) {
        Iterator<Tile> it = emtpyTileList.select(new Predicate<Tile>()
        {
            @Override
            public boolean evaluate(Tile t) {
                return mode == BaseLevel.Mode.DEATHMATCH || (t.tileID > 130 && mode == BaseLevel.Mode.ARCADE);
            }
        }).iterator();

        Array<Tile> tList = new Array(Tile.class);

        while(it.hasNext()) {
            tList.add(it.next());
        }

        Tile t = tList.random();
        return t;
    }

    public static BaseLevel getLevel(int levelID) {
        BaseLevel level = null;

        switch(levelID) {
            case 0: {
                level = new LevelEnd();
                break;
            }
            case 1: {
                level = new Level01();
                break;
            }
            case 2: {
                level = new Level02();
                break;
            }
            case 3: {
                level = new Level03();
                break;
            }
            case 4: {
                level = new Level04();
                break;
            }
            case 5: {
                level = new Level05();
                break;
            }
            case 6: {
                level = new Level06();
                break;
            }
            case 7: {
                level = new Level07();
                break;
            }
            case 8: {
                level = new Level08();
                break;
            }
            case 9: {
                level = new Level09();
                break;
            }
            case 10: {
                level = new Level10();
                break;
            }
            case 11: {
                level = new Level11();
                break;
            }
            case 12: {
                level = new Level12();
                break;
            }
            case 13: {
                level = new Level13();
                break;
            }
            case 14: {
                level = new Level14();
                break;
            }
            case 15: {
                level = new Level15();
                break;
            }
            case 16: {
                level = new Level16();
                break;
            }
            case 17: {
                level = new Level17();
                break;
            }
            case 18: {
                level = new Level18();
                break;
            }
            case 19: {
                level = new Level19();
                break;
            }
            case 20: {
                level = new Level20();
                break;
            }
            case 21: {
                level = new Level21();
                break;
            }
            case 22: {
                level = new Level22();
                break;
            }
            case 23: {
                level = new Level23();
                break;
            }
            case 24: {
                level = new Level24();
                break;
            }
            case 25: {
                level = new Level25();
                break;
            }
            case 26: {
                level = new Level26();
                break;
            }
            case 27: {
                level = new Level27();
                break;
            }
            case 28: {
                level = new Level28();
                break;
            }
            case 29: {
                level = new Level29();
                break;
            }
            case 30: {
                level = new Level30();
                break;
            }
            case 31: {
                level = new Level31();
                break;
            }
            case 32: {
                level = new Level32();
                break;
            }
            case 33: {
                level = new Level33();
                break;
            }
            case 34: {
                level = new Level34();
                break;
            }
            case 35: {
                level = new Level35();
                break;
            }
            default:
                for(BaseLevel l : userLevels) {
                    if(l.getLevelID() == levelID) {
                        level = l;
                        break;
                    }
                }
        }

        return level;
    }

}
