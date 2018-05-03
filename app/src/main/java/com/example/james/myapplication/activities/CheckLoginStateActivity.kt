package com.example.james.myapplication.activities

/**
 * Created by adrian on 4/2/18.
 */

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
//import com.zapato.zapato.HomeView.Home
import com.example.james.myapplication.Network.FirebaseManager
import com.example.james.myapplication.R

// MARK - MainActivity class simply check user persistent

class CheckLoginStateActivity  : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        oneTimeCode()
    }

    // MARK - One Time Code

    // this method only run once when the app first launch to prompt login or use as guest mode
    fun oneTimeCode() {
        println("inside oneTimeCode 1")

        // PreferenceManager takes care of saving state to the device
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)

        if (!prefs.getBoolean("firstTime", false)) {
            // run your "one time" code here

            //Check User Persistent
            if (FirebaseManager().CurrenUser() != null) { println()
                // user logged in

                ///////val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {
                // user not found
                ///////val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }

            // mark first time has executed.
            val editor = prefs.edit()
            editor.putBoolean("firstTime", true)
            editor.commit()
        } else {
            ///////val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
