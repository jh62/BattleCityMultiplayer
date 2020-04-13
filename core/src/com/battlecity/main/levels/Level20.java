package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level20 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 20;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            2, 2, 2, 2, 2, 2, 2, 2,
            1, 1,
            3, 3,
            4, 4, 4, 4, 4, 4, 4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "W:F", "x:F", "w:F", "x:F", "x:F", "w:F", "x:F", "w:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "w:F", "x:F", "s:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "W:F", "x:F", "w:HD", "s:F", "x:F", "w:F", "x:F", "w:F", "x:F", "x:F"},
            {"s:HU", "x:F", "w:F", "W:F", "x:F", "s:F", "x:F", "w:HD", "w:HU", "x:F", "w:F", "x:F", "x:F"},
            {"x:F", "x:F", "w:F", "W:F", "x:F", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"w:F", "x:F", "w:F", "W:F", "W:F", "x:F", "W:F", "W:F", "W:F", "W:F", "x:F", "x:F", "w:F"},
            {"x:F", "x:F", "x:F", "w:HD", "x:F", "x:F", "x:F", "t:F", "x:F", "W:F", "x:F", "s:HU", "s:HU"},
            {"w:F", "w:F", "w:HR", "w:F", "x:F", "s:F", "t:F", "t:F", "t:F", "W:F", "x:F", "w:HD", "w:HD"},
            {"w:HU", "x:F", "w:HR", "x:F", "x:F", "w:F", "t:F", "t:F", "t:F", "W:F", "x:F", "w:F", "x:F"},
            {"x:F", "s:HD", "x:F", "x:F", "x:F", "w:F", "x:F", "t:F", "x:F", "W:F", "x:F", "t:F", "x:F"},
            {"x:F", "w:F", "x:F", "s:HD", "x:F", "w:HU", "w:HU", "w:HU", "x:F", "x:F", "t:F", "t:F", "t:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "W:F", "t:F", "t:F", "t:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "W:F", "x:F", "t:F", "x:F"}};

        return tiles;
    }
}
