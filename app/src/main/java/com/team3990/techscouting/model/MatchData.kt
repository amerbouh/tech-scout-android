package com.team3990.techscouting.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import com.team3990.techscouting.common.interfaces.DataSheet
import com.team3990.techscouting.util.Endgame
import com.team3990.techscouting.util.Mobility
import com.team3990.techscouting.util.toFileTimestampString
import com.team3990.techscouting.util.toFullTimestampString
import java.io.Serializable

@Entity
data class MatchData(
    @PrimaryKey
    override val id: Int,
    override val name: String,
    override val timestamp: Date,
    override val scouterName: String,
    override val regionalName: String
) : DataSheet, Serializable {

    /**
     * An integer representing the amount of Power Cells
     * the robot scored in the inner port.
     * */
    var scoredInnerPortCells: Int = 0

    /**
     * An integer representing the amount of Power Cells
     * the robot scored in the outer port.
     * */
    var scoredOuterPortCells: Int = 0

    /**
     * An integer representing the amount of Power Cells
     * the robot scored in the bottom port.
     * */
    var scoredBottomPortCells: Int = 0

    /**
     * A boolean representing whether or not the robot
     * moves to the initiation line.
     * */
    var movesToInitiationLine: Boolean = false

    /**
     * A boolean representing whether or not the robot
     * climbs at the end of the match.
     * */
    var climbs: Boolean = false

    /**
     * A boolean representing whether or not the robot
     * used the rotation control during the match.
     * */
    var rotationControl: Boolean = false

    /**
     * A boolean representing whether or not the robot
     * used the position control during the match.
     * */
    var positionControl: Boolean = false

    /**
     * A floating-point number representing the amount of time
     * it took for the robot to climb, if applicable.
     * */
    var climbDuration: Float? = null

    /**
     * An Endgame enum case representing the action the robot performs
     * at the end of the match.
     * */
    var endgame: Endgame = Endgame.NONE

    /**
     * A Mobility enum case representing the mobility of the robot
     * during the match.
     * */
    var mobility: Mobility? = null

    /**
     * A string representing additional comments provided
     * by the scouter.
     * */
    var comments: String? = null

    override val fileName: String
        get() = "$regionalName - $name"

    override val timestampString: String
        get() = timestamp.toFileTimestampString()

    override val fullTimestampString: String
        get() = timestamp.toFullTimestampString()

}