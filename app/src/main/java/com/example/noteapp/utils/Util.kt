package com.example.noteapp.utils

import com.example.noteapp.R
import kotlin.math.absoluteValue

fun Int.getDrawables():Int {
    return when (absoluteValue) {
        2 -> R.drawable.color_pick_red
        3 -> R.drawable.color_pick_pink
        4 -> R.drawable.color_pick_orange
        5 -> R.drawable.color_pick_blue
        6 -> R.drawable.color_pick_black
        7 -> R.drawable.color_pick_green
        else -> R.drawable.color_pick_yellow
    }
}
