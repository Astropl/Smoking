package com.example.smoking

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.smoking.databinding.ActivityFirstBinding


var operDB = OperDB()
var operDate = OperDate()
var ustawienia = Ustawienia()
var dzisiajDzien = ""
var dzisiajGodzina = ""
var wczytajIloscFajekDzis = 0


class First : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //**********
//        val myDB = openOrCreateDatabase("myDB3.db", 0, null)
//        val row = ContentValues()
//        row.put("data", "45")
//        row.put("godzina", "45")
//        row.put("cena", "445")
//        row.put("dlugoscFajki", "45")
//        row.put("nazwaFajki", "45")
//        myDB.insert("dbDay", null, row)
//        myDB.close()
        var zamie ="0"
        val myDB = openOrCreateDatabase("myDB3.db", 0, null)
        zamie = operDB.cosSprawdzam(zamie , myDB )
        //*********test

        createDBFirst()
        createUstawieniaFirst()
        pokazDate()
        pokazGodzine()
        wczytajIloscFajekDzis()

        binding.firstTxtDzisiaj11.setText(dzisiajDzien)

        binding.firstBtnSettings.setOnClickListener()
        {
            ustawienia()
        }
        binding.imgBt.setOnClickListener()
        {
            addDay()

        }
        binding.firstBtnStatsy.setOnClickListener()
        {
            startActivity(Intent(this, Statystyki()::class.java))
        }
    }

    fun ustawienia() {
        startActivity(Intent(this, Ustawienia()::class.java))

    }

    fun createUstawieniaFirst() {
        val myDB = openOrCreateDatabase("myDB3.db", 0, null)
        operDB.createUstawienia(myDB)
    }

    fun wczytajIloscFajekDzis() {
        val myDB = openOrCreateDatabase("myDB3.db", 0, null)
        wczytajIloscFajekDzis = operDB.wczytajIloscFajekDzis(wczytajIloscFajekDzis, myDB)
        //l("Ilosc fajek dzis to: $wczytajIloscFajekDzis")
        binding.firstTxtIloscSztuk.setText(wczytajIloscFajekDzis.toString())
    }

    fun pokazDate() {
        dzisiajDzien = operDate.pokazDate(dzisiajDzien)
        //l("PokazDate: Dzisiaj jest : $dzisiajDzien")
    }

    fun pokazGodzine() {
        dzisiajGodzina = operDate.pokazGodzineZMinutami(dzisiajGodzina)
        //l("PokazDate: Godzina jest : $dzisiajGodzina")
    }

    fun addDay() {
        //l("ddDay: First")
        val myDB = openOrCreateDatabase("myDB3.db", 0, null)
        //l("AddDAY: Ilosc fajek dzis to: $wczytajIloscFajekDzis")

        wczytajIloscFajekDzis++
        //teraz zapisać to razem z datą i godziną itd
        //pobierz datę - ok
        //pobierz godzinę- ok
        //pobierz cenę- ok
        //pobierz dlugosc fajki
        //pobierz nazwę fajki
        dzisiajDzien = operDate.pokazDate(dzisiajDzien)
        dzisiajGodzina = operDate.pokazGodzineZMinutami(dzisiajGodzina)
        //l("AddDAY: data dzis to: $dzisiajDzien")
        //l("AddDAY: data dzis to: $dzisiajGodzina")
        cenaZaPaczke = operDB.wczytajCeneZaPaczke(cenaZaPaczke, myDB)
        //l("AddDAY: Cena zapaczke to: $cenaZaPaczke zł")
        dlugoscFajki = operDB.wczytajDlugoscFajki(dlugoscFajki, myDB)
        //l("AddDAY:dlugoscFajki to: $dlugoscFajki cm")
        nazwaPaczki = operDB.wczytajNazwePaczki(nazwaPaczki, myDB)
        //l("AddDAY:nazwa paczki to: $nazwaPaczki")
            //zapisz to do bazy dbDay
        var pomocnicza =""
pomocnicza =
    operDB.zapiszDzisDzien(dzisiajDzien,dzisiajGodzina,cenaZaPaczke,dlugoscFajki,nazwaPaczki,myDB).toString()
        //l("AddDAY: Ilosc fajek po dodoaniu dzis to: $wczytajIloscFajekDzis")
        binding.firstTxtIloscSztuk.setText(wczytajIloscFajekDzis.toString())
        //l("ddDay: end")
    }

    fun createDBFirst() {


        val myDB = openOrCreateDatabase("myDB3.db", 0, null)

        operDB.createDB(myDB)
        //l("Baza otwarta")
    }

    fun l(s: String) {
        Log.d("First", "Astro: +  $s")
    }

}
