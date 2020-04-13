package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level30 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 30;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            1, 1, 1, 1,
            2, 2, 2, 2, 2, 2, 2, 2,
            3, 3, 3, 3,
            4, 4, 4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "w:HD", "w:HD", "x:F", "x:F", "x:F", "s:HD", "x:F", "x:F"},
            {"x:F", "s:HD", "s:HD", "x:F", "w:HD", "t:F", "t:F", "s:HD", "x:F", "w:HD", "t:F", "w:HD", "x:F"},
            {"w:HD", "t:F", "t:F", "w:HD", "t:F", "t:F", "t:F", "t:F", "w:HD", "t:F", "t:F", "t:F", "w:HD"},
            {"t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F"},
            {"s:F", "t:F", "W:F", "t:F", "t:F", "t:F", "t:F", "t:F", "W:F", "t:F", "t:F", "t:F", "t:F"},
            {"t:F", "t:F", "W:F", "W:F", "W:F", "t:F", "t:F", "t:F", "W:F", "W:F", "W:F", "t:F", "s:F"},
            {"t:F", "t:F", "t:F", "t:F", "W:F", "t:F", "x:F", "t:F", "t:F", "t:F", "W:F", "t:F", "t:F"},
            {"t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "x:F"},
            {"t:F", "t:F", "t:F", "t:F", "t:F", "w:HU", "w:HU", "t:F", "t:F", "t:F", "t:F", "t:F", "s:HU"},
            {"s:HU", "t:F", "t:F", "t:F", "w:HU", "x:F", "x:F", "w:HU", "x:F", "t:F", "t:F", "s:HU", "x:F"},
            {"x:F", "s:HU", "w:HU", "w:HU", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "w:HU", "w:HU", "w:HU", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "x:F", "x:F", "x:F"}};

        return tiles;
    }
}
