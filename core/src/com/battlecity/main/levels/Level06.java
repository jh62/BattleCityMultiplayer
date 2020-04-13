package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level06 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 6;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            3, 3, 3, 3, 3, 3, 3,
            2, 2,
            1, 1, 1, 1, 1, 1, 1, 1, 1,
            4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "x:F", "w:HR", "x:F", "w:HL", "t:F", "t:F", "x:F", "x:F", "x:F"},
            {"x:F", "w:HL", "s:HR", "x:F", "w:HL", "x:F", "x:F", "x:F", "w:HR", "t:F", "w:HL", "w:HR", "t:F"},
            {"x:F", "w:HL", "s:HR", "x:F", "w:HL", "x:F", "w:F", "x:F", "w:HR", "t:F", "w:HL", "w:HR", "t:F"},
            {"x:F", "w:F", "x:F", "x:F", "w:F", "x:F", "s:F", "x:F", "w:F", "t:F", "x:F", "w:F", "t:F"},
            {"x:F", "x:F", "x:F", "w:HR", "s:HU", "x:F", "w:F", "x:F", "w:HU", "s:HL", "x:F", "t:F", "t:F"},
            {"w:F", "w:F", "w:HL", "x:F", "x:F", "t:F", "w:F", "t:F", "x:F", "x:F", "w:HR", "w:F", "w:F"},
            {"x:F", "x:F", "x:F", "x:F", "w:HR", "t:F", "t:F", "t:F", "w:HL", "x:F", "x:F", "x:F", "x:F"},
            {"s:F", "w:F", "w:F", "x:F", "w:HU", "t:F", "t:F", "t:F", "w:HU", "w:HR", "w:F", "w:F", "s:F"},
            {"s:HU", "s:HU", "s:HU", "x:F", "w:HD", "x:F", "t:F", "x:F", "w:HD", "x:F", "s:HU", "s:HU", "s:HU"},
            {"x:F", "w:F", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "w:F", "w:HL", "x:F", "x:F", "w:HU", "x:F", "w:HU", "x:F", "x:F", "w:HR", "w:F", "t:F"},
            {"x:F", "x:F", "w:HU", "x:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "x:F", "t:F", "t:F", "t:F"},
            {"x:F", "x:F", "w:HD", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "w:HD", "t:F", "t:F"}};

        return tiles;
    }
}
