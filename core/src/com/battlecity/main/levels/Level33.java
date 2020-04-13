package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level33 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 33;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            2, 2, 2, 2,
            4, 4, 4, 4, 4, 4, 4, 4,
            3, 3, 3, 3,
            2, 2, 2, 2
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F"},
            {"x:F", "s:F", "x:F", "x:F", "x:F", "s:F", "x:F", "x:F", "s:F", "t:F", "t:F", "x:F", "x:F"},
            {"x:F", "x:F", "s:F", "x:F", "x:F", "x:F", "x:F", "s:F", "t:F", "s:HD", "s:HL", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "s:F", "x:F", "t:F", "t:F", "t:F", "t:F", "t:F", "x:F", "s:HR", "x:F"},
            {"x:F", "s:HL", "x:F", "x:F", "s:F", "t:F", "t:F", "s:F", "t:F", "x:F", "x:F", "s:F", "x:F"},
            {"x:F", "s:HU", "s:HL", "t:F", "x:F", "s:F", "t:F", "t:F", "s:F", "x:F", "x:F", "s:HR", "x:F"},
            {"x:F", "x:F", "t:F", "t:F", "t:F", "t:F", "t:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F"},
            {"x:F", "s:HD", "s:HL", "t:F", "x:F", "s:F", "t:F", "x:F", "x:F", "x:F", "s:F", "x:F", "x:F"},
            {"x:F", "t:F", "t:F", "t:F", "s:F", "x:F", "s:F", "x:F", "s:HD", "x:F", "x:F", "s:F", "x:F"},
            {"t:F", "t:F", "t:F", "s:F", "x:F", "x:F", "x:F", "x:F", "s:HR", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "s:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:HR", "s:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "s:HD", "s:HL", "x:F", "x:F"},
            {"s:HL", "x:F", "s:HD", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "x:F", "x:F", "x:F"}};

        return tiles;
    }
}
