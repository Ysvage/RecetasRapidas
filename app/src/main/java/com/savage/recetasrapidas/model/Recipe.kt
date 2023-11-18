package com.savage.recetasrapidas.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recipe(
    @StringRes val titleResId: Int,
    @DrawableRes val imageUrl: Int
)
