package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level18 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 18;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            4, 4, 4, 4,
            1, 1,
            3, 3, 3, 3, 3, 3,
            2, 2, 2, 2, 2, 2, 2, 2
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:F", "s:F", "s:F", "t:F", "x:F"},
            {"x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:F", "x:F", "x:F", "s:F", "x:F"},
            {"w:F", "t:F", "w:F", "x:F", "x:F", "x:F", "w:F", "w:F", "w:F", "w:F", "x:F", "s:F", "x:F"},
            {"x:F", "w:F", "t:F", "w:F", "x:F", "x:F", "w:F", "x:F", "t:F", "w:F", "s:F", "s:F", "x:F"},
            {"x:F", "x:F", "w:F", "x:F", "t:F", "s:F", "w:F", "t:F", "x:F", "w:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "s:F", "x:F", "w:F", "s:F", "w:F", "w:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "w:F", "w:F", "s:F", "w:F", "x:F", "s:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "w:F", "x:F", "t:F", "w:F", "s:F", "t:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"s:F", "s:F", "s:F", "t:F", "x:F", "w:F", "x:F", "x:F", "w:F", "w:F", "x:F", "x:F", "x:F"},
            {"s:F", "x:F", "w:F", "w:F", "w:F", "w:F", "x:F", "x:F", "w:F", "s:F", "s:F", "x:F", "x:F"},
            {"s:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:F", "w:F", "w:F", "x:F"},
            {"t:F", "s:F", "s:F", "s:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "x:F", "w:F", "s:F", "s:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "x:F", "s:F", "s:F"}};

        return tiles;
    }
}
