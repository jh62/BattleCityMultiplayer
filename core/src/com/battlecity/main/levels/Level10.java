package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level10 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 10;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            2, 2,
            3, 3, 3, 3,
            4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "w:HR", "w:HU", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "w:F", "w:HU", "w:HL"},
            {"w:HR", "w:HU", "x:F", "x:F", "w:F", "x:F", "t:F", "t:F", "x:F", "w:F", "x:F", "x:F", "w:HR"},
            {"w:F", "x:F", "x:F", "x:F", "w:F", "t:F", "t:F", "t:F", "t:F", "w:F", "x:F", "x:F", "w:HR"},
            {"w:F", "x:F", "x:F", "w:HR", "w:F", "t:F", "s:F", "s:F", "t:F", "w:F", "w:HL", "x:F", "w:F"},
            {"w:HR", "w:HD", "w:HD", "w:F", "W:F", "W:F", "W:F", "W:F", "W:F", "W:F", "w:F", "w:F", "w:F"},
            {"x:F", "w:F", "w:F", "w:F", "s:F", "s:F", "w:F", "s:F", "s:F", "w:F", "w:F", "w:F", "w:HL"},
            {"x:F", "x:F", "w:F", "w:F", "s:F", "x:F", "w:F", "x:F", "s:F", "w:F", "w:F", "w:HL", "x:F"},
            {"x:F", "x:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:HL", "x:F"},
            {"w:F", "t:F", "w:HU", "w:HU", "w:HU", "s:F", "s:F", "w:HU", "w:HU", "w:HU", "w:HU", "t:F", "w:F"},
            {"w:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "w:F"},
            {"x:F", "x:F", "t:F", "t:F", "t:F", "eW:SBR", "eW:HD", "eW:SBL", "t:F", "t:F", "t:F", "t:F", "x:F"},
            {"x:F", "x:F", "x:F", "w:HL", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "w:HL", "x:F", "x:F"}};

        return tiles;
    }
}
