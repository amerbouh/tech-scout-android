package com.team3990.techscouting.util

import android.content.res.Resources
import com.team3990.techscouting.R

fun Int.toPowerCellCountString(resources: Resources) : String = if (this == 0) resources.getString(R.string.none) else resources.getQuantityString(R.plurals.power_cell_count, this, this)