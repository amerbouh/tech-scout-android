package com.team3990.techscouting.util

import android.content.res.Resources
import com.team3990.techscouting.R

fun Boolean.toFriendlyString(resources: Resources) : String = if (this) resources.getString(R.string.yes) else resources.getString(R.string.no)