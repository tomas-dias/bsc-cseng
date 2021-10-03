package com.example.kicksser

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val user = FirebaseAuth.getInstance().currentUser


        if (user != null) {
            tv_profile_email.text = user.email
            tv_profile_name.text = user.displayName
        }

        findViewById<Button>(R.id.button_back_to_main_menu).setOnClickListener {
            openMainActivity()
        }

    }

    private fun openMainActivity()
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}