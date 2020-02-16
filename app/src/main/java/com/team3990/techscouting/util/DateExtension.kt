package com.team3990.techscouting.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.toFileTimestampString() : String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA).format(this)