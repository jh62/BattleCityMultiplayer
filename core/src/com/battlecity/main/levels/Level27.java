package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level27 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 27;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            3, 3,
            4, 4, 4, 4, 4, 4, 4, 4,
            2, 2, 2, 2, 2, 2, 2, 2,
            1, 1
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"s:F", "s:F", "x:F", "x:F", "s:F", "x:F", "x:F", "s:F", "s:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "s:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F", "s:F", "x:F", "s:F", "s:F", "t:F"},
            {"x:F", "s:F", "x:F", "x:F", "s:F", "s:F", "s:F", "x:F", "t:F", "x:F", "s:F", "x:F", "x:F"},
            {"x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "s:F", "x:F", "s:F", "s:F", "s:F", "x:F", "x:F"},
            {"t:F", "s:F", "s:F", "x:F", "s:F", "w:F", "s:F", "w:F", "w:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "s:F", "t:F", "s:F", "t:F", "x:F", "x:F", "w:F", "x:F", "x:F", "s:F", "s:F"},
            {"x:F", "x:F", "s:F", "x:F", "x:F", "t:F", "x:F", "x:F", "s:F", "x:F", "x:F", "s:F", "x:F"},
            {"x:F", "x:F", "w:F", "x:F", "x:F", "s:F", "x:F", "x:F", "s:F", "s:F", "w:F", "s:F", "x:F"},
            {"t:F", "s:F", "s:F", "s:F", "t:F", "t:F", "w:F", "s:F", "s:F", "x:F", "w:F", "s:F", "x:F"},
            {"x:F", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "t:F", "t:F", "x:F", "w:F", "x:F"},
            {"x:F", "x:F", "x:F", "s:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "t:F", "x:F", "w:F", "x:F"},
            {"x:F", "x:F", "x:F", "s:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "s:F", "x:F", "w:F", "x:F"}};

        return tiles;
    }
}
