package com.example.cryptoapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptoapp.di.SharedComponent
import com.example.cryptoapp.ui.HomeActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as BaseApplication).getSharedComponent().inject(this)

        btn_api.setOnClickListener {
            if (edt_txt.text.isBlank()) {
                Snackbar.make(linearLayout, "Enter API KEY", Snackbar.LENGTH_LONG).show()
            } else {
                val editor = sharedPreferences.edit()
                editor.putString("APIKEY", edt_txt.text.toString())
                editor.apply()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)

            }
        }
    }
}
