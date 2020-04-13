package com.battlecity.main.global.enums;

/**
 *
 * @author JH62
 */
public enum ResponseCode
{
    NOREASON,
    CLIENT_DISCONNECTED,
    SERVER_DISCONNECTED,
    CONNECTION_REFUSED,
    SERVER_FULL,
    SERVER_UNAVAILABLE,
    GAME_OVER,
    INVALID_LEVEL,
    ADDRESS_ALREADY_IN_USE;

    @Override
    public String toString() {
        return name().replaceAll("_", " ");
    }

}
