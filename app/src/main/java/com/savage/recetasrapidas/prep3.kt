package com.savage.recetasrapidas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.savage.recetasrapidas.databinding.ActivityPrep3Binding

class prep3 : AppCompatActivity() {
    private lateinit var biding: ActivityPrep3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityPrep3Binding.inflate(layoutInflater)
        setContentView(biding.root)
        val toolbar = null
        setSupportActionBar(toolbar)

        //lista
        /* lista()*/
        elab()
        //---------

        // Configurar la BottomNavigationView
        biding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_shoplist -> {
                    val shopintent = Intent(this, ShopList::class.java).apply {
                    }
                    startActivity(shopintent)
                    true
                }

                R.id.nav_home -> {
                    val homeintent = Intent(this, HomeActivity::class.java).apply {
                    }
                    startActivity(homeintent)
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
        }
    }
    private fun elab(){
        biding.regresar.setOnClickListener{
            val elabint: Intent = Intent(this, Rec3:: class.java)
            startActivity(elabint)
        }
    }
}