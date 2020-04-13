package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class Level29 extends BaseLevel {

    @Override
    public int getLevelID() {
        return 29;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
            2, 2, 2, 2,
            4, 4, 4, 4, 4, 4
        };
        return spawnList;
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "W:F", "x:F", "x:F"},
            {"x:F", "W:F", "W:F", "W:F", "x:F", "W:F", "x:F", "W:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "W:F", "W:F", "W:F", "W:F", "W:F", "W:F", "W:F", "W:F", "x:F", "W:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "W:F", "W:F", "W:F", "W:F", "W:F", "W:F", "x:F", "x:F"},
            {"x:F", "W:F", "x:F", "x:F", "W:F", "W:F", "x:F", "W:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"W:F", "W:F", "W:F", "x:F", "W:F", "W:F", "W:F", "x:F", "x:F", "x:F", "x:F", "W:F", "x:F"},
            {"W:F", "W:F", "W:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "W:F", "x:F", "x:F", "W:F"},
            {"x:F", "W:F", "W:F", "W:F", "x:F", "W:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"W:F", "x:F", "W:F", "W:F", "W:F", "W:F", "W:F", "W:F", "W:F", "W:F", "x:F", "W:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "W:F", "x:F", "W:F", "W:F", "W:F", "W:F", "W:F", "W:F", "x:F"},
            {"x:F", "x:F", "x:F", "W:F", "W:F", "x:F", "x:F", "x:F", "W:F", "W:F", "W:F", "W:F", "x:F"},
            {"x:F", "x:F", "W:F", "x:F", "W:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"W:F", "x:F", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "W:F", "W:F", "x:F", "x:F"}};

        return tiles;
    }
}
