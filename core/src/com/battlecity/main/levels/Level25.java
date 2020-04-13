package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level25 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 25;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            3, 3,
            2, 2, 2, 2, 2, 2, 2, 2,
            4, 4, 4, 4, 4, 4, 4, 4, 4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "s:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "s:F", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "x:F", "s:F", "x:F", "x:F", "s:F", "x:F", "s:F", "s:F"},
            {"x:F", "w:F", "x:F", "x:F", "x:F", "w:F", "x:F", "s:F", "w:F", "x:F", "x:F", "x:F", "s:F"},
            {"x:F", "x:F", "x:F", "x:F", "w:F", "w:F", "x:F", "w:F", "w:F", "x:F", "s:F", "x:F", "x:F"},
            {"x:F", "x:F", "s:F", "x:F", "w:F", "x:F", "x:F", "w:F", "w:F", "x:F", "w:F", "w:F", "x:F"},
            {"s:F", "x:F", "s:F", "x:F", "x:F", "w:F", "x:F", "s:F", "x:F", "x:F", "s:F", "w:F", "x:F"},
            {"x:F", "x:F", "w:F", "w:F", "x:F", "w:F", "x:F", "x:F", "x:F", "w:F", "s:F", "x:F", "x:F"},
            {"x:F", "s:F", "w:F", "w:F", "x:F", "w:F", "w:F", "x:F", "w:F", "w:F", "x:F", "x:F", "w:F"},
            {"x:F", "w:F", "x:F", "x:F", "x:F", "w:F", "s:F", "x:F", "x:F", "x:F", "x:F", "w:F", "w:F"},
            {"x:F", "x:F", "x:F", "w:F", "x:F", "w:F", "w:F", "s:F", "x:F", "s:F", "x:F", "x:F", "w:F"},
            {"w:F", "x:F", "w:F", "w:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "w:F", "s:F", "x:F", "x:F"},
            {"w:F", "x:F", "w:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "w:F", "w:F", "w:F", "x:F"}};

        return tiles;
    }
}
