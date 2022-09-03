package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.w3c.dom.Text
import java.util.jar.Attributes

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tvResgisterid=findViewById<TextView>(R.id.tvRegisterid)
        val loginbtn=findViewById<Button>(R.id.Loginbtn)
        val email=findViewById<EditText>(R.id.edittext1)
        val password=findViewById<EditText>(R.id.edittext2)
        tvResgisterid.setOnClickListener{
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
        }





        loginbtn.setOnClickListener {
            when {


                TextUtils.isEmpty(email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter a email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter a Password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }




                else ->
                {
                    val email: String = email.text.toString().trim { it <= ' ' }
                    val password: String = password.text.toString().trim { it <= ' ' }


                    //create a insatnce and login user with email and password
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(
                                this@LoginActivity,
                                "you are registered successfully.",
                                Toast.LENGTH_SHORT
                            )

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user-id", FirebaseAuth.getInstance().currentUser!!.uid)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }

                }



            }





        }

















    }
}






