package com.savage.recetasrapidas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.savage.recetasrapidas.databinding.ActivityAccountBinding

enum class ProviderType {
    BASIC,
    GOOGLE,
    FACEBOOK
}

class AccountActivity : AppCompatActivity() {

    private lateinit var biding: ActivityAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(biding.root)

        intents()

        //Setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider?: "")

        //Guardado de datos
        val prefs = getSharedPreferences(getString(R.string.app_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("provider", provider)
        prefs.apply()
    }
    private fun setup(email: String, provider: String){

        biding.tvEmail.text = email
        biding.tvProvider.text = provider

        //-------------------
        biding.btnLogout.setOnClickListener {

            //Borrado de datos
            val prefs = getSharedPreferences(getString(R.string.app_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
           val logintent = Intent(this, LoginScreen::class.java).apply {
            }
            startActivity(logintent)

           /*FirebaseAuth.getInstance().signOut()
            onBackPressed()*/
        }
    }
    private fun intents(){
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
                    true
                }
                else -> false
            } } } }