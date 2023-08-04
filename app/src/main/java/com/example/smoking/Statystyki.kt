package com.example.smoking

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.smoking.databinding.ActivityStatystykiBinding

class Statystyki : AppCompatActivity() {
    private lateinit var binding: ActivityStatystykiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatystykiBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        l("Construktor - Start")
        binding.statystykiBtnBack.setOnClickListener() {
            startActivity(Intent(this, First::class.java))
        }
        binding.statystykiBtnKasa.setOnClickListener() {
            l(" button Kasa")
            buttonKasa()
        }
        binding.statystykiBtnSpalone.setOnClickListener() {
            l(" button splone")
            buttonspalone()
        }
        binding.statystykiBtnNIEWIEM.setOnClickListener() {
            l(" button nie wiem")
            buttonNieWiem()
        }

        btnSploneInvisible()
        l("Construktor - End")



    }

    fun buttonspalone() {
        btnSploneVisible()
    }


    fun buttonKasa() {
        btnSploneInvisible()
        btnSploneVisible()
    }


    fun buttonNieWiem() {
        btnSploneInvisible()
    }

    fun btnSploneVisible() {

        var statystyki_txt_dzisiajInfo = findViewById<TextView>(R.id.statystyki_txt_dzisiajInfo)
        var statystyki_txt_tydzienInfo = findViewById<TextView>(R.id.statystyki_txt_tydzienInfo)
        var statystyki_txt_miesiacInfo = findViewById<TextView>(R.id.statystyki_txt_miesiacInfo)
        var statystyki_txt_wszystkieInfo = findViewById<TextView>(R.id.statystyki_txt_wszystkieInfo)
        var statystyki_txt_dzisiajInfo1 = findViewById<TextView>(R.id.statystyki_txt_dzisiajInfo1)
        var statystyki_txt_tydzienInfo1 = findViewById<TextView>(R.id.statystyki_txt_tydzienInfo1)
        var statystyki_txt_miesiacInfo1 = findViewById<TextView>(R.id.statystyki_txt_miesiacInfo1)
        var statystyki_txt_wszystkieInfo1 =
            findViewById<TextView>(R.id.statystyki_txt_wszystkieInfo1)
        statystyki_txt_dzisiajInfo.visibility = View.VISIBLE
        statystyki_txt_tydzienInfo.visibility = View.VISIBLE
        statystyki_txt_miesiacInfo.visibility = View.VISIBLE
        statystyki_txt_wszystkieInfo.visibility = View.VISIBLE
        statystyki_txt_dzisiajInfo1.visibility = View.VISIBLE
        statystyki_txt_tydzienInfo1.visibility = View.VISIBLE
        statystyki_txt_miesiacInfo1.visibility = View.VISIBLE
        statystyki_txt_wszystkieInfo1.visibility = View.VISIBLE
    }

    fun btnSploneInvisible() {
        var statystyki_txt_dzisiajInfo = findViewById<TextView>(R.id.statystyki_txt_dzisiajInfo)
        var statystyki_txt_tydzienInfo = findViewById<TextView>(R.id.statystyki_txt_tydzienInfo)
        var statystyki_txt_miesiacInfo = findViewById<TextView>(R.id.statystyki_txt_miesiacInfo)
        var statystyki_txt_wszystkieInfo = findViewById<TextView>(R.id.statystyki_txt_wszystkieInfo)
        var statystyki_txt_dzisiajInfo1 = findViewById<TextView>(R.id.statystyki_txt_dzisiajInfo1)
        var statystyki_txt_tydzienInfo1 = findViewById<TextView>(R.id.statystyki_txt_tydzienInfo1)
        var statystyki_txt_miesiacInfo1 = findViewById<TextView>(R.id.statystyki_txt_miesiacInfo1)
        var statystyki_txt_wszystkieInfo1 =
            findViewById<TextView>(R.id.statystyki_txt_wszystkieInfo1)
        statystyki_txt_dzisiajInfo.visibility = View.INVISIBLE
        statystyki_txt_tydzienInfo.visibility = View.INVISIBLE
        statystyki_txt_miesiacInfo.visibility = View.INVISIBLE
        statystyki_txt_wszystkieInfo.visibility = View.INVISIBLE
        statystyki_txt_dzisiajInfo1.visibility = View.INVISIBLE
        statystyki_txt_tydzienInfo1.visibility = View.INVISIBLE
        statystyki_txt_miesiacInfo1.visibility = View.INVISIBLE
        statystyki_txt_wszystkieInfo1.visibility = View.INVISIBLE
    }

    fun l(s: String) {
        Log.d("Statystyki", "Astro: +  $s")
    }
}