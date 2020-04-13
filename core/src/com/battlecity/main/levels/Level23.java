package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level23 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 23;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            4, 4, 4, 4, 4, 4,
            3, 3, 3, 3,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "s:F", "s:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "s:F", "s:F", "t:F", "t:F", "w:F", "s:F", "w:F", "t:F", "t:F", "s:F", "s:F", "x:F"},
            {"x:F", "x:F", "x:F", "s:F", "t:F", "t:F", "s:F", "t:F", "t:F", "s:F", "x:F", "x:F", "x:F"},
            {"t:F", "x:F", "x:F", "x:F", "s:F", "t:F", "t:F", "t:F", "s:F", "x:F", "x:F", "x:F", "t:F"},
            {"s:F", "t:F", "x:F", "x:F", "x:F", "t:F", "t:F", "t:F", "x:F", "x:F", "x:F", "t:F", "s:F"},
            {"t:F", "x:F", "x:F", "x:F", "s:HD", "s:HU", "t:F", "s:HU", "s:HD", "x:F", "x:F", "x:F", "t:F"},
            {"x:F", "x:F", "x:F", "x:F", "s:F", "x:F", "s:HD", "x:F", "s:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "s:F", "x:F", "x:F", "s:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "s:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "s:F", "x:F", "x:F"}};

        return tiles;
    }
}
