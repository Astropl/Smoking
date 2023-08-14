package com.example.smoking

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

var localDateNow: LocalDate = LocalDate.now()
var dzisiajDzienTygodnia = localDateNow.dayOfWeek.toString()

var dayOfWeeks = ""

class OperDate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oper_date)
    }

    fun pokazDate(data: String): String {
        var localDateNow: LocalDate = LocalDate.now()
        //loguj("Pokaz Date: $localDateNow")
        var data1 = localDateNow.toString()
        return data1
    }

    fun pokazGodzineZMinutami(godzinaCala: String): String {
        //loguj("Pokaz cała Godzina z Minutami : $localGodzina")
        var localTimeNow: LocalTime = LocalTime.now()
        var localGodzina = localTimeNow.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        return localGodzina
    }

    fun pokazDzienTygodnia(dzienTygodnia: String): String {
        var localDateNow: LocalDate = LocalDate.now()
        var dzisiajDzienTygodnia = localDateNow.dayOfWeek.toString()
        var dzisiajDzienTygodniaPl = changeDaysOfWeek(dzisiajDzienTygodnia)
        l("Pokaz Dzien tygodnia: $dzisiajDzienTygodniaPl")

        return dzisiajDzienTygodniaPl
        //return "Wtorek"
    }

    fun changeDaysOfWeek(dayOfWeek: String): String {
        when (dayOfWeek) {
            "MONDAY" -> {
                //loguj("Poniedziałek")
                dayOfWeeks = "Poniedziałek"
            }
            "TUESDAY" -> {
                //loguj("Wtorek")
                dayOfWeeks = "Wtorek"
            }
            "WEDNESDAY" -> {
                //loguj("Środa")
                dayOfWeeks = "Środa"
            }
            "THURSDAY" -> {
                //loguj("Czwartek")
                dayOfWeeks = "Czwartek"
            }
            "FRIDAY" -> {
                //loguj("Piątek")
                dayOfWeeks = "Piątek"
            }
            "SATURDAY" -> {
                //loguj("Sobota")
                dayOfWeeks = "Sobota"
            }
            "SUNDAY" -> {
                //loguj("Niedziela")
                dayOfWeeks = "Niedziela"
            }
            else ->
                l("Zadne")
        }
        dzisiajDzienTygodnia = dayOfWeeks
        return dzisiajDzienTygodnia
    }

    fun pokazDateMinusPrzesuniecie(przesuniecie: Int): String {
        var localDateNow: LocalDate = LocalDate.now()
        //loguj("Pokaz Date: $localDateNow")
        var data1 = localDateNow.minusDays(przesuniecie.toLong())

        return data1.toString()
    }
//                                        2023-08-07 - pon / 3 - dni przesuniecia
    fun pokazDatePlusPrzesuniecie(dzienDoPrzesuniecia: LocalDate, ilosc: Int): LocalDate? {

    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val text = dzienDoPrzesuniecia.format(formatter)
    var dzienDoPrzesuniecia1 = LocalDate.parse(text, formatter)
    dzienDoPrzesuniecia1=dzienDoPrzesuniecia1.minusDays(ilosc.toLong())


l("Na czysto w pokazdateplusprzesuniecie Dziendo Przesuniecia : $dzienDoPrzesuniecia1")
        return dzienDoPrzesuniecia1
    }
    fun pokazDniOdPierwszegoDoDzisiaj(pierwszaData: String, drugaData: String): LocalDate?
    {//var iledni ="0"
        l("pierwzsza data :$pierwszaData")
        l("Drugadata $drugaData")
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val text1 = pierwszaData.format(formatter)
        var pierwszaData1 = LocalDate.parse(text1,formatter)
        val text2 = drugaData.format(formatter)
var drugaData1 = LocalDate.parse(text2,formatter)
        l("W operDate: Pierwsza to $pierwszaData1 a druga to: $drugaData1")
        //var pierwszaData2 = LocalDate.parse(pierwszaData1.formatter)
        //var drugaData2 = LocalDate.parse(drugaData1.formatter)

        var iledni = drugaData1.minusDays(pierwszaData1.toEpochDay())
            //zparsowac dwie daty na locla date
            //var ileDnie = drugaData.minus(pierwszaData)
        return iledni

    }

    fun pokazDzienInt(dzien: Int): Int {
        var localDateNow: LocalDate = LocalDate.now()
        var localDzien = localDateNow.format(DateTimeFormatter.ofPattern("dd"))
        //loguj("Pokaz Dzien : $localDzien")
        return localDzien.toInt()
    }
    fun pokazMiesiacInt(miesiac: Int): Int {
        var localDateNow: LocalDate = LocalDate.now()
        var localMiesiac = localDateNow.format(DateTimeFormatter.ofPattern("MM"))
        //loguj("Pokaz Miesiac: $localMiesiac")
        return localMiesiac.toInt()
    }
    fun pokazRokInt(rok:Int): Int {
        var localDateNow: LocalDate = LocalDate.now()
        var localRok = localDateNow.format(DateTimeFormatter.ofPattern("yyyy"))
        //l("Pokaz Rok: $localRok")
        return localRok.toInt()
    }
    fun l(s: String) {
        Log.d("Statystyki", "Astro: +  $s")
    }
}