package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userId=intent.getStringExtra("user-id")
        val emailId=intent.getStringExtra("email_id")
        val tvUser=findViewById<TextView>(R.id.tvUserId)
        val tvEmail=findViewById<TextView>(R.id.tvEmailId)
        val btnLogout=findViewById<Button>(R.id.logoutbtn)


        tvUser.text="User ID :: $userId"
        tvEmail.text="Email ID :: $emailId"



        btnLogout.setOnClickListener {
            //logout from the app
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@MainActivity,LoginActivity::class.java))
            finish()
        }

    }



}