package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level04 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 4;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 1, 1, 4, 4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{{
            "x:F", "t:F", "t:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "t:F", "x:F"},
        {"t:F", "t:F", "x:F", "x:F", "w:HD", "w:F", "w:F", "w:HD", "w:HD", "x:F", "x:F", "x:F", "t:F"},
        {"t:F", "x:F", "x:F", "w:HR", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:HD", "x:F", "s:HU"},
        {"s:HU", "x:F", "x:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:HL", "x:F"},
        {"x:F", "x:F", "w:HR", "w:HU", "x:F", "x:F", "x:F", "w:HU", "w:F", "w:F", "x:F", "w:HL", "x:F"},
        {"W:F", "x:F", "w:HR", "x:F", "s:HL", "x:F", "s:HL", "x:F", "w:F", "w:HL", "x:F", "x:F", "x:F"},
        {"x:F", "x:F", "w:F", "x:F", "w:HD", "w:HD", "x:F", "x:F", "w:F", "w:HL", "x:F", "W:F", "W:F"},
        {"x:F", "x:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "x:F", "x:F", "x:F"},
        {"x:F", "w:HR", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:HL", "x:F", "x:F"},
        {"x:F", "w:HU", "w:HU", "w:F", "w:F", "w:F", "w:F", "w:F", "x:F", "w:HU", "w:HU", "x:F", "x:F"},
        {"x:F", "w:F", "w:F", "w:HD", "w:HU", "w:F", "w:F", "x:F", "w:HD", "w:F", "w:F", "x:F", "t:F"},
        {"t:F", "x:F", "w:HU", "w:HU", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "w:HU", "w:HU", "x:F", "t:F", "t:F"},
        {"s:F", "t:F", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "t:F", "t:F", "s:F"}};

        return tiles;
    }
}
