package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level24 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 24;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            3, 3, 3, 3,
            4, 4,
            2, 2, 2, 2,
            1, 1, 1, 1, 1, 1, 1, 1, 1
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "s:F", "x:F", "w:F", "s:HU", "x:F", "x:F", "x:F", "x:F", "w:HR", "x:F", "x:F"},
            {"x:F", "x:F", "w:F", "x:F", "w:F", "t:F", "x:F", "w:HU", "w:F", "w:F", "w:F", "x:F", "x:F"},
            {"x:F", "t:F", "t:F", "x:F", "w:F", "t:F", "w:HR", "w:HL", "x:F", "x:F", "x:F", "s:F", "s:F"},
            {"t:F", "t:F", "t:F", "t:F", "t:F", "t:F", "w:F", "w:F", "w:F", "x:F", "w:HR", "w:F", "x:F"},
            {"x:F", "x:F", "t:F", "t:F", "w:HD", "w:HD", "s:HU", "w:F", "x:F", "w:HR", "w:F", "w:HU", "w:HR"},
            {"w:F", "s:HU", "x:F", "w:HD", "w:HU", "w:HU", "x:F", "x:F", "x:F", "w:F", "w:HU", "x:F", "w:HR"},
            {"w:HR", "x:F", "w:HD", "w:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F"},
            {"w:HR", "x:F", "w:HU", "x:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F"},
            {"x:F", "x:F", "s:F", "x:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F"},
            {"w:F", "x:F", "w:F", "x:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F"},
            {"w:HR", "x:F", "w:F", "x:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F", "i:F"},
            {"w:HR", "x:F", "w:F", "x:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "i:F", "i:F", "i:F", "i:F", "i:F"},
            {"x:F", "x:F", "w:HU", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "i:F", "i:F", "i:F", "i:F"}};
        
        return tiles;
    }
}
