package com.savage.recetasrapidas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.savage.recetasrapidas.databinding.ActivityRec3Binding

class Rec3 : AppCompatActivity() {
    private lateinit var biding: ActivityRec3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityRec3Binding.inflate(layoutInflater)
        setContentView(biding.root)
        val toolbar = null
        setSupportActionBar(toolbar)

        reg()
        elab()
        // Configurar la BottomNavigationView
        biding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
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
        biding.pasos.setOnClickListener{
            val elabint: Intent = Intent(this, prep3:: class.java)
            startActivity(elabint)
        }
    }
    private fun reg(){
        biding.regresar.setOnClickListener {
            val regint: Intent = Intent (this, HomeActivity::class.java)
            startActivity(regint)
        }
    }
}