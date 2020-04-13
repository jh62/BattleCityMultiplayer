package com.battlecity.main.levels;

/**
 *
 * @author Pablis
 */
public class Level01 extends BaseLevel
{

    @Override
    public String[][] getTileArray() {
        String[][] tiles = new String[][]{
            {"x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "s:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "w:HU", "x:F", "w:HU", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"x:F", "w:HU", "x:F", "w:HU", "x:F", "w:HD", "x:F", "w:HD", "x:F", "w:HU", "x:F", "w:HU", "x:F"},
            {"w:HD", "x:F", "w:HD", "w:HD", "x:F", "w:HU", "x:F", "w:HU", "x:F", "w:HD", "w:HD", "x:F", "w:HD"},
            {"s:HU", "x:F", "w:HU", "w:HU", "x:F", "w:HD", "x:F", "w:HD", "x:F", "w:HU", "w:HU", "x:F", "s:HU"},
            {"x:F", "w:HD", "x:F", "w:HD", "x:F", "w:F", "w:F", "w:F", "x:F", "w:HD", "x:F", "w:HD", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "w:HU", "x:F", "w:HU", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"x:F", "w:F", "x:F", "w:F", "x:F", "eW:SBR", "eW:HD", "eW:SBL", "x:F", "w:F", "x:F", "w:F", "x:F"},
            {"x:F", "x:F", "x:F", "x:F", "x:F", "eW:HR", "e:F", "eW:HL", "x:F", "x:F", "x:F", "x:F", "x:F"}};

        return tiles;
    }

    @Override
    public int getLevelID() {
        return 1;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList = new int[]{
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2
        };
        return spawnList;
    }

}
