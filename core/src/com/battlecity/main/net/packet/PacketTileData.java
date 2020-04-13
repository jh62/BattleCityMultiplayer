package com.battlecity.main.net.packet;

import com.battlecity.main.entity.tiles.*;

/**
 *
 * @author Pablis
 */
public class PacketTileData extends Packet {

    public int tileID;
    public TileRegion tileRegion;

    protected PacketTileData() {

    }
}
