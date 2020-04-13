package com.battlecity.main.levels;

/**
 *
 * @author JH62
 */
public class LevelEnd extends BaseLevel {

    public static final int LEVEL_END_ID = 0;
    
    @Override
    public int getLevelID() {
        return LEVEL_END_ID;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        return new int[0];
    }

    @Override
    String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F",
             "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F",
             "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F",
             "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F",
             "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F",
             "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F",
             "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F",
             "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F",
             "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F",
             "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F",
             "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F",
             "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL",
             "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F",
             "x:F", "x:F", "x:F", "x:F"}};

        return tiles;
    }
}
