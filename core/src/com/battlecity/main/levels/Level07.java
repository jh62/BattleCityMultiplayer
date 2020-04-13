package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level07 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 7;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            1, 1, 1,
            2, 2, 2, 2,
            3, 3, 3, 3, 3, 3,
            1, 1, 1, 1, 1, 1, 1
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{{
            "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:HU", "s:HU", "x:F", "x:F", "x:F", "x:F"},
        {"x:F", "x:F", "s:F", "s:HU", "s:HU", "s:HU", "x:F", "x:F", "x:F", "x:F", "s:F", "x:F", "x:F"},
        {"x:F", "x:F", "s:F", "x:F", "x:F", "x:F", "t:F", "x:F", "s:HU", "s:F", "s:F", "x:F", "x:F"},
        {"x:F", "s:F", "x:F", "x:F", "x:F", "t:F", "s:F", "x:F", "x:F", "x:F", "s:F", "x:F", "x:F"},
        {"x:F", "x:F", "x:F", "x:F", "t:F", "s:F", "s:F", "x:F", "x:F", "x:F", "s:HU", "s:F", "x:F"},
        {"x:F", "s:F", "x:F", "t:F", "s:F", "s:F", "s:F", "x:F", "s:F", "x:F", "x:F", "x:F", "x:F"},
        {"x:F", "s:HR", "x:F", "s:F", "s:F", "x:F", "x:F", "x:F", "s:F", "s:F", "x:F", "x:F", "x:F"},
        {"s:HL", "x:F", "x:F", "x:F", "s:F", "x:F", "s:F", "s:F", "s:F", "x:F", "x:F", "s:HR", "x:F"},
        {"x:F", "s:HR", "s:F", "x:F", "x:F", "x:F", "s:F", "s:F", "t:F", "x:F", "x:F", "s:F", "x:F"},
        {"x:F", "s:F", "x:F", "x:F", "x:F", "x:F", "s:F", "t:F", "x:F", "x:F", "s:F", "s:F", "x:F"},
        {"x:F", "s:HU", "s:HU", "s:F", "x:F", "x:F", "t:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F"},
        {"x:F", "x:F", "x:F", "x:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "s:HU", "x:F", "s:HD", "s:F"},
        {"s:HD", "s:HD", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "x:F", "x:F", "x:F"}};

        return tiles;
    }
}
