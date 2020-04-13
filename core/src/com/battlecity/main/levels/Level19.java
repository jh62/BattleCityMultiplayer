package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level19 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 19;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            2, 2, 2, 2,
            4, 4, 4, 4, 4, 4, 4, 4,
            1, 1, 1, 1,
            3, 3, 3, 3
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"x:F", "s:HU", "x:F", "s:HU", "x:F", "s:HU", "x:F", "s:HU", "x:F", "s:HU", "x:F", "s:HU", "x:F"},
            {"w:HD", "x:F", "w:HD", "x:F", "w:F", "x:F", "x:F", "x:F", "w:F", "x:F", "w:HD", "x:F", "w:HD"},
            {"w:F", "x:F", "w:F", "w:HU", "w:F", "x:F", "w:F", "x:F", "w:F", "w:HU", "w:F", "x:F", "w:F"},
            {"s:HU", "x:F", "s:HU", "x:F", "s:F", "x:F", "s:HU", "x:F", "s:F", "x:F", "s:HU", "x:F", "s:HU"},
            {"t:F", "t:F", "x:F", "x:F", "w:F", "x:F", "t:F", "x:F", "w:F", "x:F", "x:F", "t:F", "t:F"},
            {"t:F", "t:F", "t:F", "t:F", "w:F", "w:HU", "t:F", "w:HU", "w:F", "t:F", "t:F", "t:F", "t:F"},
            {"t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F"},
            {"w:HD", "x:F", "w:HD", "x:F", "w:F", "t:F", "t:F", "t:F", "w:F", "x:F", "w:HD", "x:F", "w:HD"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "x:F", "t:F", "x:F", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"x:F", "w:HU", "x:F", "w:HU", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "w:HU", "x:F", "w:HU", "x:F"}};

        return tiles;
    }
}
