package com.savage.recetasrapidas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.savage.recetasrapidas.databinding.LoginScreenBinding
import com.google.android.gms.common.api.ApiException as ApiException1


class LoginScreen : AppCompatActivity() {

    private val GOOGLESIGNIN = 100
    private lateinit var biding: LoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        biding = LoginScreenBinding.inflate(layoutInflater)
        setContentView(biding.root)

        //Llamado a las funciones
        ForgotIntent()
        RegisterIntent()
        setup()
        session()
        //-------------------------

    }
    override fun onStart(){
        super.onStart()

        biding.authlayout.visibility = View.VISIBLE
    }

    private fun session(){
        val prefs = getSharedPreferences(getString(R.string.app_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if(email != null && provider != null){

            biding.authlayout.visibility = View.INVISIBLE
            showHome(email, ProviderType.valueOf(provider))
        }
    }


    //Intent para ir a pantalla ForgotPassword
    private fun ForgotIntent(){
        biding.TxtViewolvi.setOnClickListener{
            val ForgotInt: Intent = Intent(this, ForgotPassword:: class.java)
            startActivity(ForgotInt)
        }
    }

    //Intent para ir a pantalla RegisterScreen
    private fun RegisterIntent(){
        biding.btnrg.setOnClickListener {
            val Registerint: Intent = Intent(this, RegisterScreen:: class.java)
            startActivity(Registerint)
        }
    }
    private fun setup() {
        var tittle = "Login"

        biding.login.setOnClickListener {
            if (biding.Usuario.text.toString()
                    .isNotEmpty() && biding.Contra.text.toString().isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    biding.Usuario.text.toString(),
                    biding.Contra.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }

        biding.google.setOnClickListener {

            val googleconf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this,googleconf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLESIGNIN)
        }
    }
    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Usuario No Existente")
        builder.setMessage("Cuenta de Usuario No Registrada")
        builder.setPositiveButton("Aceptar", null)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showAlert1() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("ERROR CON LA AUTENTICACION")
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLESIGNIN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try{
            val account = task.getResult(ApiException1::class.java)

            if(account != null){
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                if (it.isSuccessful) {
                    showHome(account.email?: "", ProviderType.GOOGLE)
                } else {
                    showAlert()
                     }
                 }
             }
          } catch (e: ApiException1){
              showAlert1()
          }
        }
    }

}



