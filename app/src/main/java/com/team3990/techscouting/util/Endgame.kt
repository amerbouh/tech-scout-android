package com.team3990.techscouting.util

enum class Endgame {
    CLIMB, PARK, NONE;

    /** Methods */

    fun toFriendlyString() : String = when (this) {
        PARK -> "Parks"
        CLIMB -> "Climbs"
        NONE -> "None"
    }

}