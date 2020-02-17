package com.team3990.techscouting.database.converter

import androidx.room.TypeConverter
import com.team3990.techscouting.util.Endgame

class EndgameConverter {

    @TypeConverter
    fun fromEndgame(endgame: Endgame) : String = endgame.toString()

    @TypeConverter
    fun toEndgame(string: String) : Endgame = Endgame.valueOf(string)

}