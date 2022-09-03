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

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tvLogin=findViewById<TextView>(R.id.tvLoginActivity)


        val Registerbtn = findViewById<Button>(R.id.Registerbtn)
        val Name = findViewById<EditText>(R.id.edittext11)
        val RegisterEmail = findViewById<EditText>(R.id.edittext22)
        val RegisterPassowrd = findViewById<EditText>(R.id.edittext3)

        tvLogin.setOnClickListener{
            //startActivity(Intent(this@RegisterActivity,RegisterActivity::class.java))
            // alternative we can use onbackpress
            onBackPressed()
        }



        Registerbtn.setOnClickListener {
            when {
                TextUtils.isEmpty(Name.text.toString().trim { it <= ' ' }) -> {

                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter a Name.",
                        Toast.LENGTH_SHORT
                    ).show()

                }

                TextUtils.isEmpty(RegisterEmail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter a email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(RegisterPassowrd.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter a Password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }




                else ->
                {
                    val email: String = RegisterEmail.text.toString().trim { it <= ' ' }
                    val password: String = RegisterPassowrd.text.toString().trim { it <= ' ' }


                    //create a insatnce and register user with email and password
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(
                                this@RegisterActivity,
                                "you are registered successfully.",
                                Toast.LENGTH_SHORT
                            )

                            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user-id", firebaseUser.uid)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(
                                this@RegisterActivity,
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

