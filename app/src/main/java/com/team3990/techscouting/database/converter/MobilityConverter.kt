package com.team3990.techscouting.database.converter

import androidx.room.TypeConverter
import com.team3990.techscouting.util.Mobility

class MobilityConverter {

    @TypeConverter
    fun fromMobility(mobility: Mobility?) : String? = if (mobility != null) mobility.toString() else null

    @TypeConverter
    fun toMobility(string: String?) : Mobility? = if (string != null) Mobility.valueOf(string) else null

}