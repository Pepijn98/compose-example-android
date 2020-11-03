package dev.vdbroek.pepijn98.models

import dev.vdbroek.pepijn98.R

data class Nature(
    val id: Int,
    val image: Int
)

val natureList1 = listOf(
    Nature(1, R.drawable.nature_1),
    Nature(2, R.drawable.nature_2),
    Nature(3, R.drawable.nature_3)
)

val natureList2 = listOf(
    Nature(4, R.drawable.nature_4),
    Nature(5, R.drawable.nature_5),
    Nature(6, R.drawable.nature_6),
    Nature(7, R.drawable.nature_7),
    Nature(8, R.drawable.nature_8),
    Nature(9, R.drawable.nature_9)
)
