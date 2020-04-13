package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level32 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 32;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            4, 4, 4, 4, 4, 4, 4, 4,
            1, 1, 1, 1, 1, 1,
            3, 3,
            2, 2, 2, 2
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "x:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F"},
            {"i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F"},
            {"i:F", "i:F", "i:F", "w:F", "i:F", "i:F", "i:F", "i:F", "i:F", "w:F", "i:F", "i:F", "i:F"},
            {"i:F", "w:F", "x:F", "w:F", "x:F", "w:F", "i:F", "w:F", "x:F", "w:F", "x:F", "w:F", "i:F"},
            {"i:F", "w:HU", "w:HU", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F", "w:F", "w:HU", "w:HU", "i:F"},
            {"i:F", "i:F", "i:F", "w:F", "w:HD", "w:F", "s:F", "w:F", "w:HD", "w:F", "i:F", "i:F", "i:F"},
            {"s:F", "i:F", "i:F", "i:F", "x:F", "s:HU", "x:F", "s:HU", "x:F", "i:F", "i:F", "i:F", "s:F"},
            {"i:F", "i:F", "i:F", "i:F", "x:F", "w:HD", "x:F", "w:HD", "x:F", "i:F", "i:F", "i:F", "i:F"},
            {"i:F", "i:F", "i:F", "i:F", "x:F", "w:F", "x:F", "w:F", "x:F", "i:F", "i:F", "i:F", "i:F"},
            {"i:F", "i:F", "i:F", "w:F", "x:F", "x:F", "w:HD", "x:F", "x:F", "w:F", "i:F", "i:F", "i:F"},
            {"i:F", "w:F", "i:F", "w:F", "x:F", "s:HU", "s:HU", "s:HU", "x:F", "w:F", "i:F", "w:F", "i:F"},
            {"x:F", "w:F", "w:HD", "w:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "w:F", "w:HD", "w:F", "x:F"},
            {"x:F", "w:HU", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "x:F", "w:HU", "x:F"}};

        return tiles;
    }
}
