package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level13 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 13;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            3, 3, 3, 3, 3, 3, 3, 3,
            2, 2, 2, 2, 2, 2, 2, 2,
            4, 4, 4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "w:HD", "x:F", "x:F", "x:F", "w:HD", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "w:F", "w:F", "w:F", "w:F", "x:F", "x:F", "x:F", "w:F", "w:F", "w:F", "w:F", "x:F"},
            {"x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "s:F", "x:F"},
            {"x:F", "s:F", "x:F", "w:F", "w:HU", "x:F", "x:F", "x:F", "w:HU", "w:F", "x:F", "w:F", "w:F"},
            {"x:F", "w:F", "x:F", "w:HL", "t:F", "s:HD", "s:F", "s:HD", "t:F", "w:HR", "x:F", "s:F", "w:F"},
            {"x:F", "w:HU", "x:F", "x:F", "t:F", "t:F", "t:F", "t:F", "t:F", "x:F", "x:F", "s:HU", "w:F"},
            {"w:F", "s:HD", "x:F", "x:F", "t:F", "t:F", "t:F", "t:F", "t:F", "x:F", "x:F", "w:HD", "w:F"},
            {"w:F", "s:F", "x:F", "w:HL", "t:F", "s:HU", "s:F", "s:HU", "t:F", "w:HR", "x:F", "w:F", "x:F"},
            {"w:F", "w:F", "x:F", "w:F", "w:HD", "x:F", "x:F", "x:F", "w:HD", "w:F", "x:F", "s:F", "x:F"},
            {"w:F", "s:F", "x:F", "x:F", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "w:F", "x:F"},
            {"w:F", "w:F", "w:F", "w:F", "w:F", "x:F", "x:F", "x:F", "w:F", "w:F", "w:F", "s:F", "s:F"},
            {"w:F", "w:F", "x:F", "x:F", "w:HU", "eW:SBR", "eW:HD", "eW:SBL", "w:HU", "x:F", "x:F", "w:F", "x:F"},
            {"w:F", "w:F", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "x:F", "x:F", "x:F"}};

        return tiles;
    }
}
