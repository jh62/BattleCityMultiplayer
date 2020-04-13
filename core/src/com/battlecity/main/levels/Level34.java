package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level34 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 34;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            3, 3, 3, 3,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            4, 4, 4, 4, 4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "w:HL", "w:HR", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"w:HL", "w:HL", "w:HL", "w:HR", "x:F", "w:HL", "x:F", "x:F", "w:HL", "w:HL", "x:F", "x:F", "x:F"},
            {"w:HL", "w:HL", "w:HL", "w:F", "w:F", "x:F", "x:F", "x:F", "w:HL", "w:F", "w:HL", "x:F", "x:F"},
            {"w:HR", "w:HR", "x:F", "w:F", "w:HL", "x:F", "x:F", "w:HR", "w:HL", "w:F", "w:F", "x:F", "x:F"},
            {"x:F", "w:HL", "x:F", "w:F", "w:HR", "w:HL", "x:F", "w:HL", "w:F", "w:F", "w:F", "x:F", "x:F"},
            {"x:F", "w:HL", "w:HR", "x:F", "x:F", "w:F", "w:HL", "w:F", "w:HR", "w:HR", "w:F", "x:F", "x:F"},
            {"x:F", "w:HL", "x:F", "x:F", "w:HR", "w:F", "w:F", "w:HL", "x:F", "w:HL", "w:F", "x:F", "x:F"},
            {"x:F", "w:HR", "x:F", "x:F", "w:HL", "w:F", "w:F", "w:HL", "x:F", "w:HL", "w:F", "x:F", "x:F"},
            {"x:F", "w:HR", "w:HU", "w:HU", "x:F", "w:F", "w:F", "w:F", "x:F", "w:HL", "w:HL", "w:HU", "w:F"},
            {"x:F", "w:HR", "x:F", "x:F", "w:HR", "w:HL", "w:F", "w:HR", "w:HL", "w:HL", "w:HL", "w:HR", "w:HR"},
            {"x:F", "x:F", "w:HL", "x:F", "w:F", "w:HR", "w:HL", "w:F", "w:F", "x:F", "x:F", "w:HR", "x:F"},
            {"x:F", "x:F", "w:HL", "w:HR", "w:HL", "eW:SBR", "eW:HD", "eW:SBL", "w:F", "w:HL", "x:F", "w:HL", "x:F"},
            {"x:F", "x:F", "w:HL", "w:HR", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "w:F", "w:F", "x:F", "x:F"}};

        return tiles;
    }
}
