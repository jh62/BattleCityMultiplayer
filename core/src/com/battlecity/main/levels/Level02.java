package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level02 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 2;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            4, 4, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "w:F", "x:F", "s:F", "x:F", "x:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "w:F", "w:F", "x:F", "w:F", "s:F", "w:F", "x:F"},
            {"x:F", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F"},
            {"t:F", "x:F", "x:F", "w:F", "x:F", "x:F", "s:F", "x:F", "x:F", "w:F", "t:F", "w:F", "s:F"},
            {"t:F", "t:F", "x:F", "x:F", "x:F", "w:F", "x:F", "x:F", "s:F", "x:F", "t:F", "x:F", "x:F"},
            {"x:F", "w:F", "w:F", "w:F", "t:F", "t:F", "t:F", "s:F", "x:F", "x:F", "t:F", "w:F", "x:F"},
            {"x:F", "x:F", "x:F", "s:F", "t:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"s:F", "w:F", "x:F", "s:F", "x:F", "w:F", "x:F", "w:F", "x:F", "x:F", "x:F", "w:F", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "w:F", "w:F", "x:F", "w:F", "s:F", "w:F", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "w:F", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "w:F", "x:F", "x:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "w:F", "w:F", "w:F", "x:F"}};

        return tiles;
    }
}
