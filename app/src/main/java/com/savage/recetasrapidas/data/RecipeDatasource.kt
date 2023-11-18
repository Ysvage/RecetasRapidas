package com.savage.recetasrapidas.data

import com.savage.recetasrapidas.R
import com.savage.recetasrapidas.model.Recipe

class RecipeDataSource() {
    fun loadRecetas(): List<Recipe> {
        return listOf<Recipe>(
            Recipe(R.string.receta1, R.drawable.imagen1),
            Recipe(R.string.receta2, R.drawable.imagen2),
            Recipe(R.string.receta3, R.drawable.imagen3),
            Recipe(R.string.receta4, R.drawable.imagen4))
    }
}