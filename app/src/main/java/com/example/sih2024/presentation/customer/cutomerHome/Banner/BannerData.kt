package com.example.sih2024.presentation.customerHome.Banner

import androidx.annotation.DrawableRes
import com.example.sih2024.R

data class Banner(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val banners = listOf(
    Banner(
        title = "Fresh Vegetables",
        description = "Get Up To 40% OFF",
        image = R.drawable.banner
    ),
    Banner(
        title = "Fresh Vegetables",
        description = "Get Up To 40% OFF",
        image = R.drawable.banner
    ),
    Banner(
        title = "Fresh Vegetables",
        description = "Get Up To 40% OFF",
        image = R.drawable.banner
    )
)
