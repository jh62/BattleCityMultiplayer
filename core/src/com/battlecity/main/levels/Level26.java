package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level26 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 26;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            2, 2, 2, 2, 2, 2,
            4, 4, 4, 4, 4, 4,
            1, 1, 1, 1,
            3, 3, 3, 3
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "W:F", "W:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"s:HD", "x:F", "x:F", "W:F", "t:F", "x:F", "w:HL", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"t:F", "s:HD", "x:F", "x:F", "x:F", "x:F", "s:HL", "x:F", "w:HL", "x:F", "W:F", "W:F", "x:F"},
            {"t:F", "t:F", "x:F", "s:HU", "w:HD", "w:HR", "x:F", "x:F", "s:HL", "t:F", "W:F", "x:F", "x:F"},
            {"t:F", "t:F", "t:F", "x:F", "x:F", "s:F", "w:HD", "w:HR", "x:F", "x:F", "x:F", "x:F", "s:HD"},
            {"t:F", "t:F", "s:HU", "s:HD", "x:F", "w:HR", "x:F", "s:F", "w:HD", "x:F", "x:F", "s:HD", "t:F"},
            {"t:F", "s:HU", "x:F", "x:F", "w:HU", "s:F", "x:F", "w:HR", "x:F", "s:HU", "x:F", "t:F", "t:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "w:HL", "w:HU", "s:F", "x:F", "x:F", "t:F", "t:F", "t:F"},
            {"x:F", "x:F", "W:F", "t:F", "s:HR", "x:F", "x:F", "w:HL", "w:HU", "s:HD", "s:HU", "t:F", "t:F"},
            {"x:F", "W:F", "W:F", "x:F", "w:HR", "x:F", "s:HR", "x:F", "x:F", "x:F", "x:F", "x:F", "t:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "w:HR", "x:F", "t:F", "W:F", "x:F", "x:F", "s:HU"},
            {"s:F", "x:F", "x:F", "x:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "W:F", "W:F", "x:F", "x:F"},
            {"s:F", "s:F", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "x:F", "x:F", "s:F"}};

        return tiles;
    }
}
