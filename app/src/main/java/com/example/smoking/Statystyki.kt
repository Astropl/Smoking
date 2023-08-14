package com.example.smoking

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.smoking.databinding.ActivityStatystykiBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

var iloscSpalonychwTygodniu = 0
var iloscSpalonychwTygodniuRazem = 0
var kasaIloscSpalonychwTygodniuRazem = "0"
var iloscspalonych = "0"
var kasaIloscspalonych = "0"
var iloscSpalonychwMiesiacuRazem = 0
var kasaIloscSpalonychwMiesiacuRazem = 0.0
var kasaIloscSpalonychwMiesiacuRazem1 = 0.0
var wszystkie = 0
var kasaWszystkie = 0.0
var dzienWMiesiacu = 0
var miesiacWMiesiacu = 0
var dzienWMiesiacu2 = "0"
var miesiacWMiesiacu2 = "0"
var rokWMiesiacu = 0
var dzienWMiesiacu1 = 0
var miesiacWMiesiacu1 = 0
var rokWMiesiacu1 = 0
var dzienBiezacy = ""
var dzienTygodnia = ""
var przesuniecieTygodnia = 0
var dzienPoniedziałek = ""
var wczesniejszyDzien = ""

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
        dzisiajDzien = operDate.pokazDate(dzisiajDzien)
        binding.statystykiTxtSpalone2.setText(dzisiajDzien)
        btnSploneInvisible()
        l("Construktor - End")


    }

    fun buttonspalone() {
        iloscspalonych = "0"
        kasaIloscspalonych = "0.0"
        iloscSpalonychwTygodniuRazem = 0
        iloscSpalonychwMiesiacuRazem = 0
        kasaIloscSpalonychwMiesiacuRazem1 = 0.0
        kasaIloscSpalonychwMiesiacuRazem = 0.0
        wszystkie = 0
        val myDB = openOrCreateDatabase("myDB3.db", 0, null)
        btnSploneVisible()
//******** DZIENNE
        l("buttonspaloen Start")
        // obliczyc ile spaliłem dzisiaj.


        iloscspalonych = operDB.cosSprawdzam(iloscspalonych, myDB)
        kasaIloscspalonych = operDB.kasaCosSprawdzam(kasaIloscspalonych, myDB)
        l("KasaSpalonedzis to : $kasaIloscspalonych")
        binding.statystykiTxtDzisiajInfo1.setText(iloscspalonych)
//******** TYGODNIOWE
        //Zlapac pierwszy dzien tyogdnia???

        dzienTygodnia = operDate.pokazDzienTygodnia(dzienTygodnia)
        //loguj("Dzien tygodnia to: $dzienTygodnia")

        if (dzienTygodnia == "Poniedziałek") {
            //l("Poniedziałek")
            przesuniecieTygodnia = 0
        } else if (dzienTygodnia == "Wtorek") {
            //l("Wtorek")
            przesuniecieTygodnia = 1
        } else if (dzienTygodnia == "Środa") {
            //l("Środa")
            przesuniecieTygodnia = 2
        } else if (dzienTygodnia == "Czwartek") {
            //l("Czwartek")
            przesuniecieTygodnia = 3
        } else if (dzienTygodnia == "Piątek") {
            //l("Piątek")
            przesuniecieTygodnia = 4
        } else if (dzienTygodnia == "Sobota") {
            //l("Sobota")
            przesuniecieTygodnia = 5
        } else if (dzienTygodnia == "Niedziela") {
            //l("Niedziela")
            przesuniecieTygodnia = 6
        }
        //l("PrzesuniecieTogodnia to: $przesuniecieTygodnia")
        //oD DZIEN TYGODNIA OIDEJMIJ PRZESUNIECIE WYJDZIE KTOREGO BYŁW PONIEDZIEŁK
        //l("TYGODNIOWOW!!!!!!!!!!!1")
        var dzisiajDzien = ""

        dzisiajDzien = operDate.pokazDate(dzisiajDzien)
        //l("Tygodniowo: Dzisiaj dziewn :$dzisiajDzien")
// poniedziałek to:
        dzienPoniedziałek =
            operDate.pokazDateMinusPrzesuniecie(przesuniecieTygodnia);l(" Tygodniowo: poniedzialek to: $dzienPoniedziałek")


// wczytac/obliczyc baze od poniedzialku do dzisija

//TODO: Zmieniełiem z 0 na 1. zle obliczenia
        for (i in przesuniecieTygodnia downTo 1) // było 0 Cos to zmieniea?
                 {
            var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val text = dzisiajDzien.format(formatter)
            val parsedDate = LocalDate.parse(text, formatter); l("111i równa sie : $i")
            wczesniejszyDzien = operDate.pokazDatePlusPrzesuniecie(parsedDate, i)
                .toString(); //l("Wczesniejszy dzien to: $wczesniejszyDzien")
            iloscSpalonychwTygodniu = operDB.wczytajIloscFajekTydzien(
                myDB,
                wczesniejszyDzien
            ); //l("IloscSpalonych wtygodniu: $iloscSpalonychwTygodniu")
            // kasaIloscspalonych =operDB.kasaCosSprawdzam (kasaIloscspalonych, myDB)
            kasaIloscSpalonychwTygodniuRazem =
                operDB.kasaWczytajIloscFajekTydzien(myDB, wczesniejszyDzien)
            iloscSpalonychwTygodniuRazem = iloscSpalonychwTygodniuRazem + iloscSpalonychwTygodniu
        }

        //l("Ilosc W tygodniu spalonych to: $iloscSpalonychwTygodniuRazem")
        binding.statystykiTxtTydzienInfo1.setText(iloscSpalonychwTygodniuRazem.toString())

//******** MIESIECZNE
        //l("MIESIECZNIE!!!!")

//oblicz pierwszy dzien w miesiacu.
        //var pierwszyDzienMiesiaca = "01"


        dzienWMiesiacu1 = operDate.pokazDzienInt(dzienWMiesiacu)
        miesiacWMiesiacu1 = operDate.pokazMiesiacInt(miesiacWMiesiacu)
        rokWMiesiacu1 = operDate.pokazRokInt(rokWMiesiacu)

        for (i in dzienWMiesiacu1 downTo 1) {

            if (miesiacWMiesiacu1 < 10) {
                miesiacWMiesiacu2 = "0" + miesiacWMiesiacu1
            } else {
                miesiacWMiesiacu2 = miesiacWMiesiacu1.toString()
            }
            dzienWMiesiacu1 = i
            if (dzienWMiesiacu1 < 10) {
                dzienWMiesiacu2 = "0" + dzienWMiesiacu1
            } else {
                dzienWMiesiacu2 = dzienWMiesiacu1.toString()
            }

            dzienBiezacy =
                rokWMiesiacu1.toString() + "-" + miesiacWMiesiacu2 + "-" + dzienWMiesiacu2
            //l("Dzien biezacy z intow to: $dzienBiezacy")
            //var ileDni = operDate.pokazDniOdPierwszegoDoDzisiaj(dzienBiezacy, dzisiajDzien)
            var ileDni = operDB.wczytajIloscFajekMiesiac(myDB, dzienBiezacy)
            kasaIloscSpalonychwMiesiacuRazem1 =
                operDB.kasaWczytajIloscFajekTMiesiac(myDB, dzienBiezacy).toDouble()
            // operData z przesuniecierm
            //l("To ile dni: $ileDni")
            //TODO: MAMy bład  w obliczeniach
            iloscSpalonychwMiesiacuRazem = iloscSpalonychwMiesiacuRazem + ileDni
            kasaIloscSpalonychwMiesiacuRazem =
                kasaIloscSpalonychwMiesiacuRazem + kasaIloscSpalonychwMiesiacuRazem1
        }
//todo" dALEJK


//mam pierwsz ydzien i dzisisjszy. Przelec sie po bazie.
        //oblicz ile dni od pierwszego do dzisiaj


        binding.statystykiTxtMiesiacInfo1.setText(iloscSpalonychwMiesiacuRazem.toString())
//******** WSZYSTKIE


        wszystkie = operDB.wczytajWszystkieSpalone(myDB)
        kasaWszystkie = operDB.kasaWczytajWszystkieSpalone(myDB).toDouble()

        //l("Pobrane wszytskie to : $wszystkie")

        binding.statystykiTxtWszystkieInfo1.setText(wszystkie.toString())
        l("buttonSpalone End")


    }


    fun buttonKasa() {
        btnSploneInvisible()
//        iloscspalonych= "0"
//        //kasaIloscspalonych ="0.0"
//        iloscSpalonychwTygodniuRazem =0
//        iloscSpalonychwMiesiacuRazem =0
//        kasaIloscSpalonychwMiesiacuRazem1 = 0.0
//        wszystkie =0
        binding.statystykiTxtDzisiajInfo1.setText(kasaIloscspalonych + " zł")
        binding.statystykiTxtTydzienInfo1.setText(kasaIloscSpalonychwTygodniuRazem + " zł")
        binding.statystykiTxtMiesiacInfo1.setText(kasaIloscSpalonychwMiesiacuRazem.toString() + " zł")
        binding.statystykiTxtWszystkieInfo1.setText(kasaWszystkie.toString() + " zł")
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