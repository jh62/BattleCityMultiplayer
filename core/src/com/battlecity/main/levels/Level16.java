package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level16 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 16;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            2, 2,
            4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "s:F", "t:F", "s:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "t:F", "x:F", "t:F", "s:HD", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "t:F", "x:F", "x:F", "x:F", "x:F", "t:F", "w:HD", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "t:F", "t:F", "x:F", "x:F", "t:F", "x:F", "t:F", "s:HD", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "t:F", "x:F", "t:F", "x:F", "t:F", "x:F", "x:F", "t:F", "w:HD", "x:F", "x:F", "x:F"},
            {"x:F", "t:F", "x:F", "x:F", "t:F", "x:F", "x:F", "x:F", "t:F", "t:F", "s:HD", "x:F", "x:F"},
            {"x:F", "x:F", "t:F", "x:F", "x:F", "x:F", "x:F", "t:F", "t:F", "t:F", "t:F", "w:HD", "x:F"},
            {"x:F", "x:F", "x:F", "t:F", "x:F", "x:F", "t:F", "x:F", "t:F", "t:F", "t:F", "t:F", "x:F"},
            {"w:F", "x:F", "x:F", "x:F", "x:F", "x:F", "t:F", "x:F", "x:F", "t:F", "t:F", "t:F", "s:F"},
            {"w:F", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F", "t:F", "x:F", "t:F", "t:F", "t:F", "t:F"},
            {"s:F", "w:F", "w:F", "x:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "t:F", "x:F", "t:F", "t:F", "t:F"},
            {"s:F", "s:F", "w:F", "w:F", "x:F", "eW:HR", "e:F", "eW:HL", "t:F", "x:F", "x:F", "t:F", "t:F"}};

        return tiles;
    }
}
