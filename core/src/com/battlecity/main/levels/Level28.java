package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level28 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 28;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            2, 2,
            4,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            3, 3
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:HR", "s:HL", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "s:HD", "x:F", "x:F", "x:F", "s:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "w:HD", "t:F", "w:HD", "x:F", "w:F", "w:HL", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "s:HD", "t:F", "t:F", "t:F", "s:HD", "w:F", "w:HL", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "w:HD", "t:F", "t:F", "i:F", "t:F", "t:F", "w:F", "w:HL", "x:F", "x:F"},
            {"x:F", "x:F", "s:HD", "t:F", "t:F", "i:F", "i:F", "i:F", "t:F", "t:F", "w:HL", "x:F", "x:F"},
            {"x:F", "w:HD", "t:F", "t:F", "i:F", "i:F", "i:F", "i:F", "i:F", "t:F", "t:F", "w:HD", "x:F"},
            {"s:HD", "t:F", "t:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "t:F", "t:F", "s:HD"},
            {"t:F", "t:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "t:F", "t:F"},
            {"x:F", "t:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "t:F", "x:F"},
            {"x:F", "t:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "t:F", "x:F"},
            {"x:F", "t:F", "i:F", "i:F", "i:F", "eW:SBR", "eW:HD", "eW:SBL", "i:F", "i:F", "i:F", "t:F", "x:F"},
            {"x:F", "t:F", "i:F", "i:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "i:F", "i:F", "t:F", "x:F"}};

        return tiles;
    }
}
