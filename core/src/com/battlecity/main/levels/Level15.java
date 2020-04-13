package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level15 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 15;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            1, 1,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            4, 4, 4, 4, 4, 4, 4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "w:F", "w:F", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "t:F", "t:F", "w:F", "w:F", "x:F", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F"},
            {"t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "w:F", "w:F", "x:F", "x:F", "x:F"},
            {"t:F", "s:HU", "w:F", "t:F", "w:F", "w:F", "w:F", "t:F", "t:F", "t:F", "t:F", "w:F", "s:F"},
            {"t:F", "t:F", "w:F", "t:F", "t:F", "t:F", "s:HU", "t:F", "t:F", "w:F", "s:HL", "w:F", "x:F"},
            {"x:F", "t:F", "t:F", "w:F", "s:HD", "t:F", "t:F", "t:F", "t:F", "w:F", "x:F", "w:F", "x:F"},
            {"x:F", "w:F", "w:F", "w:F", "w:F", "w:F", "t:F", "t:F", "w:F", "w:F", "w:HL", "t:F", "t:F"},
            {"s:HR", "s:HU", "w:F", "w:F", "x:F", "x:F", "x:F", "w:F", "w:HU", "x:F", "x:F", "x:F", "t:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "s:HD", "w:HD", "w:HU", "t:F", "t:F", "w:F", "w:HL", "t:F"},
            {"x:F", "w:F", "x:F", "x:F", "w:HR", "w:F", "w:HU", "t:F", "t:F", "w:F", "x:F", "x:F", "t:F"},
            {"x:F", "w:F", "w:F", "w:HL", "w:HR", "w:HU", "t:F", "t:F", "w:HD", "t:F", "w:F", "t:F", "t:F"},
            {"x:F", "x:F", "w:F", "x:F", "t:F", "eW:SBR", "eW:HD", "eW:SBL", "w:F", "t:F", "w:HU", "t:F", "x:F"},
            {"x:F", "x:F", "w:HU", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "t:F", "t:F", "t:F", "x:F"}};

        return tiles;
    }
}
