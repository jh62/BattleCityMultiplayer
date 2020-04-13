package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level35 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 35;
    }

       @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            3, 3, 3, 3,
            2, 2, 2, 2, 2, 2,
            4, 4, 4, 4, 4, 4, 4, 4, 4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "w:F", "x:F", "w:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"t:F", "x:F", "x:F", "t:F", "w:F", "t:F", "w:F", "t:F", "x:F", "x:F", "t:F", "x:F", "x:F"},
            {"w:F", "t:F", "t:F", "w:F", "w:F", "w:F", "w:F", "w:F", "t:F", "t:F", "w:F", "t:F", "x:F"},
            {"w:F", "w:F", "w:F", "w:F", "s:F", "w:F", "s:F", "w:F", "w:F", "w:F", "w:F", "t:F", "x:F"},
            {"W:F", "W:F", "W:F", "w:F", "w:F", "w:F", "w:F", "w:F", "W:F", "W:F", "W:F", "t:F", "x:F"},
            {"W:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "w:F", "W:F", "W:F", "t:F"},
            {"w:F", "w:F", "w:F", "W:F", "w:F", "w:F", "w:F", "W:F", "w:F", "w:F", "w:F", "t:F", "t:F"},
            {"w:F", "w:F", "W:F", "W:F", "W:F", "w:F", "W:F", "W:F", "W:F", "w:F", "w:F", "W:F", "W:F"},
            {"t:F", "W:F", "W:F", "t:F", "t:F", "t:F", "t:F", "t:F", "W:F", "W:F", "t:F", "W:F", "t:F"},
            {"x:F", "t:F", "t:F", "x:F", "x:F", "x:F", "x:F", "x:F", "t:F", "t:F", "x:F", "t:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "x:F", "x:F", "x:F"}};

        return tiles;
    }
}
