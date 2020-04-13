package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level03 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 3;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "t:F", "t:F", "t:F", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:HD", "s:HD", "s:HD"},
            {"w:F", "t:F", "t:F", "t:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"t:F", "t:F", "t:F", "t:F", "x:F", "x:F", "x:F", "w:F", "x:F", "w:F", "w:F", "w:F", "w:HL"},
            {"t:F", "t:F", "t:F", "t:F", "w:F", "w:F", "w:F", "w:HU", "x:F", "w:F", "x:F", "w:HR", "x:F"},
            {"t:F", "t:F", "t:F", "t:F", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "w:HR", "x:F"},
            {"x:F", "t:F", "x:F", "x:F", "x:F", "x:F", "s:F", "s:F", "s:F", "x:F", "x:F", "t:F", "x:F"},
            {"x:F", "w:HD", "x:F", "w:HD", "x:F", "x:F", "x:F", "x:F", "x:F", "t:F", "t:F", "t:F", "t:F"},
            {"w:F", "w:HL", "w:HR", "w:F", "w:HL", "w:HR", "w:HU", "w:HU", "w:HU", "t:F", "t:F", "t:F", "t:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "w:F", "x:F", "w:HD", "w:HD", "t:F", "t:F", "t:F", "t:F"},
            {"w:F", "x:F", "x:F", "s:HL", "x:F", "x:F", "x:F", "w:HU", "w:HU", "t:F", "t:F", "t:F", "x:F"},
            {"w:F", "w:F", "x:F", "s:HL", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "t:F", "t:F", "t:F", "x:F"},
            {"s:F", "w:F", "w:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "w:F", "x:F", "x:F", "x:F"}};

        return tiles;
    }
}
