package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level14 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 14;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
            2, 2, 2, 2,
            4, 4, 4, 4, 4, 4,};
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"t:F", "t:F", "x:F", "x:F", "w:HD", "w:F", "w:F", "w:F", "w:HD", "x:F", "x:F", "t:F", "t:F"},
            {"t:F", "x:F", "x:F", "w:HR", "w:F", "w:F", "w:F", "w:F", "w:F", "w:HL", "x:F", "x:F", "t:F"},
            {"x:F", "x:F", "x:F", "w:F", "w:F", "t:F", "w:F", "t:F", "w:F", "w:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "w:F", "t:F", "t:F", "w:F", "t:F", "t:F", "w:F", "x:F", "x:F", "x:F"},
            {"t:F", "x:F", "x:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "x:F", "x:F", "t:F"},
            {"t:F", "t:F", "x:F", "x:F", "w:F", "t:F", "w:F", "t:F", "w:F", "x:F", "x:F", "t:F", "t:F"},
            {"W:F", "W:F", "W:F", "x:F", "w:F", "w:F", "w:F", "w:F", "w:F", "x:F", "W:F", "W:F", "W:F"},
            {"x:F", "x:F", "x:F", "x:F", "w:HR", "w:HR", "w:HR", "w:HR", "w:HR", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "w:HL", "w:HL", "w:HL", "w:HL", "w:HL", "x:F", "x:F", "x:F", "x:F"},
            {"s:HR", "s:HR", "s:HR", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:HL", "s:HL", "s:HL"},
            {"w:HL", "w:HL", "w:HL", "x:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "x:F", "w:HR", "w:HR", "w:HR"},
            {"s:HL", "s:HL", "s:HL", "s:HR", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "s:HL", "s:HR", "s:HR", "s:HR"}};

        return tiles;
    }
}
