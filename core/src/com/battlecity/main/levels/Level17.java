package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level17 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 17;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            4, 4,
            2, 2,
            4, 4, 4, 4, 4, 4, 4,
            1, 1, 1, 1, 1, 1, 1,
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "w:HD", "x:F", "x:F", "x:F", "x:F", "x:F", "w:HD", "x:F", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "w:F", "x:F", "x:F", "i:F", "i:F", "i:F", "w:F", "w:F", "x:F"},
            {"x:F", "w:F", "x:F", "x:F", "w:F", "x:F", "s:F", "i:F", "i:F", "i:F", "i:F", "i:F", "x:F"},
            {"i:F", "i:F", "i:F", "s:HL", "w:F", "x:F", "x:F", "w:F", "i:F", "i:F", "i:F", "i:F", "x:F"},
            {"i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "w:F", "w:F", "w:HR", "w:HL", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "s:HR", "i:F", "i:F", "i:F", "i:F", "w:F", "w:HR", "w:HL", "x:F", "s:HU", "s:HU"},
            {"w:F", "w:F", "w:F", "w:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "w:F", "w:F"},
            {"x:F", "x:F", "x:F", "w:F", "w:F", "i:F", "i:F", "i:F", "i:F", "s:HL", "x:F", "x:F", "x:F"},
            {"x:F", "w:F", "w:F", "w:F", "x:F", "i:F", "i:F", "i:F", "w:F", "w:F", "x:F", "w:F", "x:F"},
            {"i:F", "i:F", "i:F", "w:F", "i:F", "x:F", "x:F", "x:F", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"i:F", "i:F", "i:F", "i:F", "i:F", "s:HU", "x:F", "s:HU", "x:F", "x:F", "w:HD", "w:F", "x:F"},
            {"w:F", "i:F", "i:F", "i:F", "i:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "w:F", "x:F", "x:F", "x:F"},
            {"w:F", "w:F", "s:HL", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "w:F", "x:F", "w:F", "x:F"}};

        return tiles;
    }
}
