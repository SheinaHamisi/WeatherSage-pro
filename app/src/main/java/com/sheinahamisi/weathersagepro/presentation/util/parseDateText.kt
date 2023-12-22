package com.sheinahamisi.weathersagepro.presentation.util

import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun parseDateText(
    date: LocalDate,
    pattern: String = "MMM d"
): String {
    val dtf = DateTimeFormatter.ofPattern(pattern)
    return when(date) {
        LocalDate.now() -> "Today, ${dtf.format(date)}"
        LocalDate.now().plusDays(1) -> "Tomorrow, ${dtf.format(date)}"
        LocalDate.now().minusDays(1) -> "Yesterday, ${dtf.format(date)}"
        else -> "${date.dayOfWeek.name}, ${dtf.format(date)}"
    }
}