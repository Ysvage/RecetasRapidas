package com.savage.recetasrapidas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.FirebaseAuth
import com.savage.recetasrapidas.databinding.ActivityForgotPasswordBinding


class ForgotPassword : AppCompatActivity() {

    private lateinit var biding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        biding= ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(biding.root)

        //LLamado a la funcion
        InicioIntent()
        setup()
    }

    private fun InicioIntent(){
        biding.login2.setOnClickListener{
            val InicioInten: Intent = Intent(this, LoginScreen:: class.java)
            startActivity(InicioInten)
        }
    }
    private fun setup(){
        var tittle = "Forgot"

        biding.enviar.setOnClickListener {
            if (biding.correo.text.toString().isNotEmpty()) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(
                    biding.correo.text.toString()
                    )
                    .addOnCompleteListener {
                    if (it.isSuccessful) {
                        bck()
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }
    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sin destinatario")
        builder.setMessage("Cuenta de usuario no asiganada a un correo")
        builder.setPositiveButton("Aceptar", null)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun bck(){
        val RgcIntent: Intent = Intent(this, LoginScreen:: class.java).apply {  }
        startActivity(RgcIntent)
        Toast.makeText(this, "Revise su correo", Toast.LENGTH_LONG).show()
        Thread.sleep(1000)
    }
}