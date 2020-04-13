package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level05 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 5;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            5, 5, 5, 5, 5,
            4, 4,
            1, 1, 1, 1, 1, 1, 1, 1,
            2, 2, 2, 2, 2
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "w:F", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"s:HD", "x:F", "w:HD", "x:F", "w:F", "x:F", "x:F", "x:F", "s:HU", "s:HU", "s:F", "x:F", "x:F"},
            {"s:F", "x:F", "w:F", "x:F", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"w:F", "x:F", "w:F", "w:F", "w:F", "x:F", "w:F", "w:F", "x:F", "W:F", "W:F", "x:F", "W:F"},
            {"w:HU", "x:F", "x:F", "x:F", "w:HU", "x:F", "x:F", "x:F", "x:F", "W:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "w:HD", "x:F", "W:F", "W:F", "x:F", "W:F", "W:F", "W:F", "x:F", "w:F", "w:F"},
            {"w:F", "w:F", "x:F", "x:F", "W:F", "w:F", "x:F", "w:F", "w:HL", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "W:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:HR", "s:HL", "x:F"},
            {"W:F", "W:F", "W:F", "x:F", "W:F", "x:F", "s:F", "x:F", "w:F", "x:F", "s:HR", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "w:HD", "w:HD", "x:F", "x:F", "x:F", "x:F", "x:F", "s:HR", "w:F", "w:F"},
            {"x:F", "x:F", "x:F", "x:F", "w:F", "w:HU", "w:HU", "w:HU", "w:F", "w:HD", "x:F", "x:F", "x:F"},
            {"w:F", "w:F", "w:HU", "x:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "w:HU", "w:F", "x:F", "x:F"},
            {"w:HU", "x:F", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "x:F", "x:F", "x:F"}};

        return tiles;
    }
}
