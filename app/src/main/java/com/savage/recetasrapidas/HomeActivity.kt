package com.savage.recetasrapidas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.savage.recetasrapidas.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.savage.recetasrapidas.data.RecipeDataSource


class HomeActivity : AppCompatActivity() {

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var biding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(biding.root)

        /*biding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_shoplist -> {
                    val shopintent = Intent(this, ShopList::class.java).apply {

                    }
                    startActivity(shopintent)
                    true
                }
                R.id.nav_home -> {
                    true
                }
                R.id.nav_account -> {
                    val accountintent = Intent(this, AccountActivity::class.java).apply {
                    }
                    startActivity(accountintent)
                    true
                }
                else -> false
            }
        }*/
        vista()

    }

    private fun vista(){
        val recipeDataSource = RecipeDataSource()
        val recipes = recipeDataSource.loadRecetas()

        // Configurar el RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recipeAdapter = RecipeAdapter(recipes)
        recyclerView.adapter = recipeAdapter
    }
}