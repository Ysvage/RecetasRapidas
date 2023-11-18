package com.savage.recetasrapidas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.savage.recetasrapidas.databinding.RegisterScreenBinding

class RegisterScreen : AppCompatActivity() {

    private lateinit var biding: RegisterScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        biding = RegisterScreenBinding.inflate(layoutInflater)
        setContentView(biding.root)

        //Llamado a las funciones
        BackIntent()
        setup()
        //---------------------

    }
    //Intent Regresar a pantalla Inicio Sesion
    private fun BackIntent(){
        biding.btnrg1.setOnClickListener {
            val Backint: Intent = Intent(this, LoginScreen:: class.java)
            startActivity(Backint)
        }
    }
    private fun setup() {
        var tittle = "Register"
        biding.btnreg.setOnClickListener {
            if (biding.regUsuario.text.toString().isNotEmpty()
                && biding.regContraseA.text.toString().isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    biding.regUsuario.text.toString(),
                    biding.regContraseA.text.toString(),
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }



    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Usuario Existente")
        builder.setMessage("Esta Cuenta de Usuario ya a sido creada")
        builder.setPositiveButton("Aceptar", null)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

}