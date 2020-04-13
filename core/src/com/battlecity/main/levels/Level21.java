package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level21 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 21;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            3, 3, 3, 3, 3, 3, 3, 3,
            2, 2,
            1, 1, 1, 1, 1, 1,
            4, 4, 4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "w:HD", "w:HD", "w:HD", "x:F", "x:F", "w:HD", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "w:HD", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "x:F", "x:F", "x:F"},
            {"x:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "w:F", "w:F", "x:F", "x:F"},
            {"t:F", "t:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "t:F", "t:F", "w:F", "w:F", "x:F"},
            {"t:F", "x:F", "s:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F", "t:F", "t:F", "t:F", "x:F"},
            {"t:F", "x:F", "s:F", "x:F", "x:F", "s:F", "x:F", "x:F", "x:F", "t:F", "t:F", "t:F", "x:F"},
            {"t:F", "x:F", "x:F", "t:F", "x:F", "x:F", "x:F", "x:F", "t:F", "t:F", "w:F", "w:F", "w:HL"},
            {"t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "w:F", "w:F", "w:F", "w:HL"},
            {"w:F", "t:F", "t:F", "w:F", "w:F", "t:F", "t:F", "t:F", "w:F", "w:F", "w:F", "w:F", "x:F"},
            {"x:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "x:F", "s:F"},
            {"s:F", "x:F", "w:F", "s:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:HL", "x:F", "s:F"},
            {"x:F", "s:F", "w:F", "w:HU", "s:F", "eW:SBR", "eW:HD", "eW:SBL", "w:F", "w:F", "s:F", "s:F", "s:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "x:F", "x:F", "x:F"}};

        return tiles;
    }
}
