package com.savage.recetasrapidas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.savage.recetasrapidas.databinding.ShopListBinding

class ShopList : AppCompatActivity() {
    private lateinit var biding: ShopListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ShopListBinding.inflate(layoutInflater)
        setContentView(biding.root)


        biding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_shoplist -> {
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
}