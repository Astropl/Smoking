package com.example.smoking

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.smoking.databinding.ActivityUstawieniaBinding

var cenaZaPaczke = "0"
var iloscSztuk = "0"
var nazwaPaczki = "0"
var dlugoscFajki = "0"


class Ustawienia : AppCompatActivity() {
    private lateinit var binding: ActivityUstawieniaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUstawieniaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cenaZaPaczke = binding.ustawieniaTxtCenaZaPaczke.text.toString()
        iloscSztuk = binding.ustawieniaTxtIloscWPaczce.text.toString()
        nazwaPaczki = binding.ustawieniaTxtNazwaPaczki.text.toString()
        dlugoscFajki = binding.ustawieniaTxtDlugoscFajki.text.toString()

        pobierzUstawienia()


        binding.ustawieniaBtnBack.setOnClickListener()
        {
            finish()
            l("finisch")
        }
        binding.ustawieniaBtnSave.setOnClickListener()
        {
            l("ZAPISZ")
            zapisz()
            l("ZAPISZ: End")
        }
    }
fun zapisz()
{val myDB = openOrCreateDatabase("myDB3.db", 0, null)
    cenaZaPaczke = binding.ustawieniaTxtCenaZaPaczke.text.toString()
    iloscSztuk = binding.ustawieniaTxtIloscWPaczce.text.toString()
    nazwaPaczki = binding.ustawieniaTxtNazwaPaczki.text.toString()
    dlugoscFajki = binding.ustawieniaTxtDlugoscFajki.text.toString()
    //TODO: Ustawiam długosc fajki na 100 mm
    dlugoscFajki = 100.toString()
    operDB.zapiszUstawienia ( cenaZaPaczke, iloscSztuk, nazwaPaczki, dlugoscFajki, myDB )
}
    fun pobierzUstawienia() {
        val myDB = openOrCreateDatabase("myDB3.db", 0, null)
        //cenaZaPaczke = operDB.pobierzUstawienia(myDB).toString()
        operDB.pobierzUstawienia(myDB).toString()
        binding.ustawieniaTxtCenaZaPaczke.setText(cenaZaPaczke)
        binding.ustawieniaTxtIloscWPaczce.setText(iloscSztuk)
        binding.ustawieniaTxtNazwaPaczki.setText(nazwaPaczki)
        binding.ustawieniaTxtDlugoscFajki.setText(dlugoscFajki)
    }

    fun start() {
        l("start: first")
    }


    fun l(s: String) {
        Log.d("OperDB", "Astro: +  $s")
    }

}