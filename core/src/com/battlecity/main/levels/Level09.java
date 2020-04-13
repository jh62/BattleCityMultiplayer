package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level09 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 9;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            1, 1, 1, 1, 1, 1,
            2, 2, 2, 2,
            3, 3, 3, 3, 3, 3, 3,
            4, 4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{{
            "x:F", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:HD", "t:F", "x:F", "x:F"},
        {"w:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:HD", "t:F", "s:HR", "s:F", "s:HL", "x:F", "w:F"},
        {"x:F", "x:F", "x:F", "s:HD", "t:F", "s:HR", "s:F", "s:HL", "x:F", "s:HU", "t:F", "x:F", "x:F"},
        {"x:F", "x:F", "s:HR", "s:F", "s:HL", "x:F", "s:HU", "t:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
        {"x:F", "x:F", "x:F", "s:HU", "t:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
        {"x:F", "x:F", "x:F", "t:F", "s:HD", "t:F", "x:F", "t:F", "s:HD", "t:F", "x:F", "x:F", "x:F"},
        {"s:F", "w:F", "x:F", "s:HR", "s:F", "s:HL", "x:F", "s:HR", "s:F", "s:HL", "x:F", "w:F", "s:F"},
        {"x:F", "x:F", "x:F", "t:F", "s:HU", "t:F", "x:F", "t:F", "s:HU", "t:F", "x:F", "x:F", "x:F"},
        {"x:F", "x:F", "x:F", "x:F", "s:HD", "x:F", "x:F", "x:F", "s:HD", "x:F", "x:F", "x:F", "x:F"},
        {"w:F", "x:F", "x:F", "s:HR", "s:F", "s:HL", "x:F", "s:HR", "s:F", "s:HL", "x:F", "x:F", "w:F"},
        {"w:F", "x:F", "x:F", "t:F", "s:HU", "t:F", "x:F", "t:F", "s:HU", "t:F", "x:F", "x:F", "w:F"},
        {"x:F", "x:F", "w:HD", "x:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "x:F", "w:HD", "x:F", "x:F"},
        {"x:F", "x:F", "w:F", "w:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "w:F", "w:F", "x:F", "x:F"}};

        return tiles;
    }
}
