package com.team3990.techscouting.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import com.team3990.techscouting.common.interfaces.DataSheet
import com.team3990.techscouting.util.toFileTimestampString

@Entity
data class MatchData(
    @PrimaryKey
    override val id: Int,
    override val name: String,
    override val timestamp: Date,
    override val scouterName: String,
    override val regionalName: String
) : DataSheet {

    override val fileName: String
        get() = "$regionalName - $name"

    override val timestampString: String
        get() = timestamp.toFileTimestampString()

}