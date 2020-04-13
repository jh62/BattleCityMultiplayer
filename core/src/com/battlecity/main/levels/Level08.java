package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level08 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 8;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            3, 3, 3, 3, 3, 3, 3,
            4, 4,
            2, 2, 2, 2,
            1, 1, 1, 1, 1, 1, 1,};
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "w:F", "x:F", "x:F", "w:F", "x:F", "w:HD", "x:F", "w:F", "x:F", "x:F", "x:F"},
            {"t:F", "w:F", "w:F", "w:F", "x:F", "w:F", "x:F", "s:HD", "x:F", "w:F", "w:HL", "x:F", "x:F"},
            {"t:F", "t:F", "t:F", "x:F", "x:F", "w:HU", "x:F", "w:F", "x:F", "w:HU", "x:F", "w:HR", "w:HL"},
            {"t:F", "W:F", "W:F", "W:F", "W:F", "W:F", "W:F", "W:F", "W:F", "W:F", "W:F", "x:F", "W:F"},
            {"x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "w:HD", "w:HD", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "w:F", "x:F", "x:F", "w:HR", "w:F", "w:F", "w:HU", "w:F", "w:HU", "s:HU", "s:HU"},
            {"w:F", "w:F", "x:F", "w:F", "x:F", "w:HR", "w:F", "w:F", "t:F", "w:F", "s:HD", "s:HD", "w:F"},
            {"x:F", "x:F", "x:F", "s:F", "x:F", "s:HD", "x:F", "t:F", "t:F", "t:F", "t:F", "x:F", "x:F"},
            {"W:F", "W:F", "x:F", "W:F", "W:F", "W:F", "W:F", "W:F", "x:F", "W:F", "W:F", "W:F", "W:F"},
            {"t:F", "t:F", "x:F", "w:HR", "x:F", "x:F", "w:HD", "w:HD", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"t:F", "t:F", "w:F", "x:F", "w:HL", "x:F", "x:F", "w:HR", "x:F", "s:HD", "w:HD", "w:F", "x:F"},
            {"t:F", "s:HD", "w:F", "x:F", "w:HL", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "w:HU", "x:F", "w:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "w:HD", "x:F", "w:HU", "x:F"}};

        return tiles;
    }
}
