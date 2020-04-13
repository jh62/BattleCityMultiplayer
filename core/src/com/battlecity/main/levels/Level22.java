package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level22 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 22;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            2, 2, 2, 2, 2, 2, 2, 2,
            1, 1, 1, 1, 1, 1,
            3, 3,
            4, 4, 4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "x:F", "t:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "t:F", "s:F", "t:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "t:F", "x:F", "x:F", "t:F", "x:F", "x:F", "t:F", "t:F", "x:F", "x:F", "x:F"},
            {"x:F", "t:F", "w:F", "t:F", "x:F", "x:F", "x:F", "t:F", "w:F", "w:F", "t:F", "x:F", "x:F"},
            {"x:F", "x:F", "t:F", "w:F", "t:F", "x:F", "x:F", "x:F", "t:F", "t:F", "x:F", "x:F", "t:F"},
            {"t:F", "x:F", "x:F", "t:F", "x:F", "x:F", "t:F", "x:F", "x:F", "x:F", "x:F", "t:F", "s:F"},
            {"w:F", "t:F", "x:F", "x:F", "x:F", "t:F", "s:F", "t:F", "x:F", "x:F", "t:F", "x:F", "t:F"},
            {"s:F", "w:F", "t:F", "x:F", "x:F", "x:F", "t:F", "x:F", "x:F", "t:F", "s:F", "t:F", "x:F"},
            {"w:F", "t:F", "x:F", "x:F", "t:F", "x:F", "x:F", "x:F", "t:F", "x:F", "t:F", "x:F", "x:F"},
            {"t:F", "x:F", "x:F", "t:F", "w:F", "t:F", "x:F", "t:F", "w:F", "t:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "t:F", "w:F", "t:F", "x:F", "x:F", "t:F", "x:F", "x:F", "t:F", "x:F"},
            {"x:F", "t:F", "x:F", "x:F", "t:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "x:F", "t:F", "s:F", "t:F"},
            {"t:F", "s:F", "t:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "t:F", "w:F", "t:F", "x:F"}};

        return tiles;
    }
}
