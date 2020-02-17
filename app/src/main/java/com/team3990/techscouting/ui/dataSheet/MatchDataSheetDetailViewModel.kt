package com.team3990.techscouting.ui.dataSheet

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.team3990.techscouting.R
import com.team3990.techscouting.model.MatchData
import com.team3990.techscouting.util.toFriendlyString
import com.team3990.techscouting.util.toPowerCellCountString

class MatchDataSheetDetailViewModel(
    private val resources: Resources,
    private val matchDataSheet: MatchData
) : ViewModel() {

    val climbs: String
        get() = matchDataSheet.climbs.toFriendlyString(resources)

    val endgame: String
        get() = matchDataSheet.endgame.toFriendlyString()

    val mobility: String
        get() = if (matchDataSheet.mobility != null) matchDataSheet.mobility!!.toFriendlyString() else resources.getString(R.string.not_specified)

    val comments: String
        get() = if (matchDataSheet.comments !=  null) matchDataSheet.comments!! else resources.getString(R.string.no_further_comments_provider)

    val scouterName: String
        get() = matchDataSheet.scouterName

    val climbDuration: String
        get() = if (matchDataSheet.climbDuration != null) "${matchDataSheet.climbDuration} sec." else resources.getString(R.string.not_applicable)

    val fullTimestampString: String
        get() = matchDataSheet.fullTimestampString

    val movesToInitiationLine: String
        get() = matchDataSheet.movesToInitiationLine.toFriendlyString(resources)

    val rotationControl: String
        get() = matchDataSheet.rotationControl.toFriendlyString(resources)

    val positionControl: String
        get() = matchDataSheet.positionControl.toFriendlyString(resources)

    val scoredInnerPortCells: String
        get() = matchDataSheet.scoredInnerPortCells.toPowerCellCountString(resources)

    val scoredOuterPortCells: String
        get() = matchDataSheet.scoredOuterPortCells.toPowerCellCountString(resources)

    val scoredBottomPortCells: String
        get() =  matchDataSheet.scoredBottomPortCells.toPowerCellCountString(resources)

}