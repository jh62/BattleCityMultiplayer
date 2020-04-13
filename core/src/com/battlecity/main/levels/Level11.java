package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level11 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 11;
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
            {"x:F", "x:F", "x:F", "x:F", "x:F", "s:F", "x:F", "w:F", "x:F", "w:F", "w:F", "x:F", "x:F"},
            {"x:F", "w:HR", "w:F", "w:F", "w:F", "w:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "w:HL", "x:F", "w:F", "x:F", "w:F", "w:F", "x:F", "t:F", "t:F", "t:F"},
            {"x:F", "w:HR", "x:F", "x:F", "x:F", "x:F", "x:F", "s:F", "x:F", "t:F", "t:F", "t:F", "t:F"},
            {"x:F", "w:HR", "x:F", "w:F", "w:F", "w:F", "s:F", "w:F", "w:F", "t:F", "t:F", "w:HU", "s:F"},
            {"x:F", "w:HU", "w:HU", "w:HU", "s:F", "x:F", "x:F", "w:F", "x:F", "t:F", "t:F", "x:F", "w:HR"},
            {"w:HR", "w:F", "w:F", "w:F", "x:F", "s:F", "t:F", "t:F", "t:F", "t:F", "t:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "s:F", "x:F", "x:F", "t:F", "t:F", "t:F", "t:F", "t:F", "w:F", "x:F"},
            {"s:F", "w:F", "x:F", "t:F", "t:F", "t:F", "t:F", "s:F", "t:F", "t:F", "t:F", "w:F", "x:F"},
            {"w:HR", "w:F", "t:F", "t:F", "t:F", "t:F", "t:F", "x:F", "x:F", "x:F", "x:F", "w:F", "w:HL"},
            {"x:F", "w:F", "t:F", "t:F", "x:F", "x:F", "x:F", "x:F", "s:HU", "w:F", "w:F", "w:F", "x:F"},
            {"x:F", "x:F", "t:F", "t:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "w:F", "x:F", "w:HR", "x:F"},
            {"x:F", "w:HD", "t:F", "t:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "x:F", "x:F", "x:F"}};

        return tiles;
    }
}
