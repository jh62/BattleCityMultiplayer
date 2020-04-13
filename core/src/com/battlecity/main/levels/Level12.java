package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level12 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 12;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            3, 3, 3, 3, 3, 3, 3, 3,
            2, 2, 2, 2, 2, 2,
            4, 4, 4, 4, 4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "w:F", "w:F", "w:F", "x:F", "x:F", "x:F"},
            {"x:F", "w:F", "w:F", "w:F", "w:HD", "x:F", "w:HD", "x:F", "x:F", "w:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "w:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "w:F", "w:F"},
            {"x:F", "W:F", "W:F", "W:F", "W:F", "W:F", "x:F", "w:F", "w:HL", "x:F", "x:F", "w:F", "s:HU"},
            {"x:F", "x:F", "s:HD", "s:HD", "s:HD", "W:F", "x:F", "w:F", "x:F", "s:F", "s:HL", "w:F", "x:F"},
            {"w:F", "x:F", "w:F", "w:F", "w:F", "W:F", "W:F", "W:F", "x:F", "W:F", "w:F", "w:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "s:F", "W:F", "x:F", "x:F", "x:F", "W:F", "s:HU", "x:F", "x:F"},
            {"W:F", "W:F", "W:F", "x:F", "W:F", "W:F", "w:F", "w:F", "x:F", "W:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "w:F", "s:HU", "s:HU", "x:F", "W:F", "W:F", "W:F", "x:F"},
            {"w:F", "w:F", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "w:F", "x:F", "s:HU", "s:HU", "x:F", "x:F", "x:F", "w:F", "w:F", "x:F", "w:HR"},
            {"w:F", "x:F", "x:F", "x:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "w:F", "x:F", "x:F", "w:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "x:F", "x:F", "x:F"}};

        return tiles;
    }
}
