package com.savage.recetasrapidas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.savage.recetasrapidas.model.Recipe

class RecipeAdapter(private val recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById(R.id.ivRecipeImage)
        val recipeTitle: TextView = itemView.findViewById(R.id.tvRecipeTitle)
        val btnVerMas: Button = itemView.findViewById(R.id.btnVerMas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]

        holder.recipeImage.setImageResource(recipe.imageUrl)
        val title = holder.itemView.context.getString(recipe.titleResId)
        holder.recipeTitle.text = title

        holder.btnVerMas.setOnClickListener {
            val intent = Intent(it.context, Rec3::class.java)
            it.context.startActivity(intent)
            Toast.makeText(it.context, "Ver Más: $title", Toast.LENGTH_SHORT).show()
        }
        /*holder.btnVerMas.setOnClickListener {
            val intent = Intent(it.context, Rec4::class.java)
            it.context.startActivity(intent)
            Toast.makeText(it.context, "Ver Más: $title", Toast.LENGTH_SHORT).show()
        }*/holder.btnVerMas.setOnClickListener {
            val intent: Intent = when (position) {
                0 -> Intent(it.context, Rec1::class.java)
                1 -> Intent(it.context, Rec2::class.java)
                2 -> Intent(it.context, Rec3::class.java)
                3 -> Intent(it.context, Rec4::class.java)
                else -> Intent(it.context, HomeActivity::class.java)
            }
            it.context.startActivity(intent)
            Toast.makeText(it.context, "Ver Más: $title", Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int {
        return recipes.size
    }
}