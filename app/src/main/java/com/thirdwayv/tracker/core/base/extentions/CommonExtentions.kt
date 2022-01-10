package com.thirdwayv.tracker.core.base.extentions

/**
 * an extension function that convert seconds to HH:mm:ss time format
 **/
fun Int.toFormattedTimeString() = String.format(
    "%02d:%02d:%02d",
    this / 3600,
    (this / 60) % 60,
    this % 60
)