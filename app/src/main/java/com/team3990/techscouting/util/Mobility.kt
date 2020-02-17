package com.team3990.techscouting.util

enum class Mobility {
    BAD, GOOD, REALLY_BAD, REALLY_GOOD;

    /** Methods */

    fun toFriendlyString() : String = when (this) {
        BAD -> "Bad"
        GOOD -> "Good"
        REALLY_BAD -> "Really bad"
        REALLY_GOOD -> "Really good"
    }

}