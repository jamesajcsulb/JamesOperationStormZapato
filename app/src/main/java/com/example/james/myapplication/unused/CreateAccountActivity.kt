package com.example.james.myapplication.unused;

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.support.v7.app.AppCompatActivity
import com.example.james.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.stripe.android.model.Card
import com.stripe.android.Stripe
import com.stripe.android.TokenCallback
import com.stripe.android.model.Token
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.android.synthetic.main.create_account.*

class CreateAccountActivity : AppCompatActivity() {

    var isloading: Boolean? = false
    var tokenIdExtract: String = ""
    var bulkToken: String = ""
    var fba : FirebaseUser? = FirebaseAuth.getInstance().getCurrentUser()
    var db : DatabaseReference? = FirebaseDatabase.getInstance().reference
    var stripetestvar: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_account)

        // Read data from firebase for user on this account for stripe charge
        val stripetestvarfirebasecustom = db!!.child("users").child(fba!!.uid)
                .addListenerForSingleValueEvent( object: ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val children = dataSnapshot!!.child("stripecus")
                        stripetestvar = children.value.toString()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // This block neede to override object: ValueEventListener
                    }
                })

        // Stripe card input widget
        val card = Card("4242424242424242", 1, 2020, "111")
        val stripe = Stripe(getApplicationContext(), "")
        stripe.createToken(
                card,
                object : TokenCallback {
                    override fun onSuccess(token: Token) {
                        tokenIdExtract = token.id.toString()
                        bulkToken = token.toString()
                    }

                    override fun onError(error: Exception) {
                        // Error message may be added here
                    }
                })

        // Add confirm purchase button listener
        val btn_click_me = findViewById(R.id.button3) as Button
        btn_click_me.setOnClickListener {
            // Toast to android user what action was taken by the user
            Toast.makeText(this@CreateAccountActivity, "Confirm purchase button clicked.", Toast.LENGTH_SHORT).show()

            // Execute HTTPS POST task
            if(isloading != true){
                someTask().execute()
            }
        }
    }

    // Inner class for handling HTTPS POST to server app
    inner class someTask() : AsyncTask<String, String, String>() {

        // Pre execution actions for click listener
        override fun onPreExecute() {
            super.onPreExecute()

            isloading = true
        }

        // Post execution actions for click listener
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            isloading = false
        }

        override fun doInBackground(vararg p0: String?): String {
            //val API_URL = "https://us-central1-fir-stripecheckpoint.cloudfunctions.net/testPost"
            val API_URL = "https://us-central1-fir-stripefullrebuildtest.cloudfunctions.net/testPost"

            try {
                val URL = URL(API_URL)
                val connect = URL.openConnection() as HttpURLConnection

                connect.readTimeout = 8000
                connect.connectTimeout = 8000
                connect.requestMethod = "POST"
                connect.doOutput = true

                val urlParameters = "loggedinuser=" + stripetestvar + "&message=" + tokenIdExtract + "&amountt=" + editText4.text + "&tokenbulkstring=" + bulkToken

                connect.doOutput = true
                val wr = DataOutputStream(connect.outputStream)
                wr.writeBytes(urlParameters)
                //wr.writeBytes(urlParameters2)
                wr.flush()
                wr.close()

                val ResponseCode: Int = connect.responseCode
                //Log.d(Tag, "ResponseCode" + ResponseCode)
                if (ResponseCode == 200) {
                    val tempStream: InputStream = connect.inputStream
                    if (tempStream != null) {
                        //Result = ConvertToString(tempStream)
                    }
                }
            } catch(Ex: Exception) {
                Log.d("", "Error in doInBackground " + Ex.message)
            }

            return ""
        }
    }
}
