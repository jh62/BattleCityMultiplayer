package com.battlecity.main.global.enums;

/**
 *
 * @author JH62
 */
public enum Power
{

    NONE(0),
    /**
     * Gives a short duration force field that shields from enemy shots.
     *
     */
    FORCE_FIELD(2500),
    /**
     * Gives a temporary force field that shields from enemy shots, like the one
     * at the beginning of every stage.
     *
     */
    HELMET(5000), // 5000
    /**
     * *
     * Destroys every enemy currently on the screen. No credit given for
     * destroying the tanks wiped out by this powerup during the end-stage bonus
     * points tally.
     */
    GRENADE(0),
    /**
     * Turns the brick walls around the fortress to stone, which gives temporary
     * invulnerability to the walls, preventing enemies from destroying it.
     * Repairs all the damage previously done to the wall.
     */
    SHOVEL(20000),
    /**
     * Increases your offensive power by one tier (tiers are: default, second,
     * third, and fourth). Power level only resets to default when you die.
     * First star (second tier): fired bullets are as fast as Power Tanks'
     * bullets. Second star (third tier): two bullets can be fired on the screen
     * at a time. Third star (fourth tier): fired bullets can destroy steel
     * walls and are twice as effective against brick walls.
     */
    STAR(0),
    /**
     * Gives an extra life. The only other way to earn an extra life is to score
     * 20,000 points.
     */
    TANK(0),
    /**
     * The clock power-up temporarily freezes time, stopping all enemy tanks
     * movement.
     */
    CLOCK(5000),
    /*
     * The cloak power-up makes the player invisible (only available in deathmatch).
     */
    CLOAK(Integer.MAX_VALUE),
    /**
     * Restores health to the player.
     */
    HEALTH(0),
    /**
     * Makes bullets bounce off the player.
     */
    BOUNCE(Integer.MAX_VALUE); //10000

    /**
     * The duration in MS of the power.
     */
    transient public final int duration;

    Power(int duration) {
        this.duration = duration;
    }

}
