package com.team3990.techscouting.util

enum class Mobility : BLETransferableEnum {
    BAD, GOOD, REALLY_BAD, REALLY_GOOD;

    /** Methods */

    fun toFriendlyString() : String = when (this) {
        BAD -> "Bad"
        GOOD -> "Good"
        REALLY_BAD -> "Really bad"
        REALLY_GOOD -> "Really good"
    }

    override fun toBLETransferableEnum() : Int = when (this) {
        BAD -> 0
        GOOD -> 1
        REALLY_BAD -> 2
        REALLY_GOOD -> 3
    }

}