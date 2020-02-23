package com.team3990.techscouting.util

enum class Endgame : BLETransferableEnum {
    CLIMB, PARK, NONE;

    /** Methods */

    fun toFriendlyString() : String = when (this) {
        PARK -> "Parks"
        CLIMB -> "Climbs"
        NONE -> "None"
    }

    override fun toBLETransferableEnum() : Int = when (this) {
        PARK -> 0
        CLIMB -> 1
        NONE -> 2
    }

}