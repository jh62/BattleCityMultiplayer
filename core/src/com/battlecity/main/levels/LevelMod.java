package com.battlecity.main.levels;

import java.util.*;



/**
 *
 * @author Pablo Da Costa Leite
 */
public class LevelMod extends BaseLevel
{

    private final int id;
    private final String[][] tiles;
    private final String name;

    public LevelMod(int id, String name, String[][] data) {
        this.id = id;
        this.name = name;
        this.tiles = data;
    }

    @Override
    public int getLevelID() {
        return id;
    }

    @Override
    public int[] getLevelSpawnlistData() {
        int[] spawnList;
        int random = 1 + new Random().nextInt(34);
        BaseLevel l = BaseLevel.getLevel(random);
        spawnList = l.getLevelSpawnlistData();
        l.dispose();
        return spawnList;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    String[][] getTileArray() {
        return tiles;
    }

}
