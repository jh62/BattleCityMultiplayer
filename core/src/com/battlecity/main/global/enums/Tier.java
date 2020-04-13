package com.battlecity.main.global.enums;

/**
 * @author JH62
 */
public enum Tier {
    FIRST(100),
    SECOND(200),
    THIRD(300),
    FOUR(400),
    PLAYER_TANK(1);

    transient public final int score;

    Tier(int score) {
        this.score = score;
    }

    /**
     * Get the next Tier from the this.
     *
     * @return
     */
    public Tier nextTIER() {
        switch (this) {
            case FIRST: {
                return SECOND;
            }
            case SECOND: {
                return THIRD;
            }
            case THIRD: {
                return FOUR;
            }
            default: {
                return this;
            }
        }
    }
}
