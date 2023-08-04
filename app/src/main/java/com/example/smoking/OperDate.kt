package com.example.smoking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    fun pokazGodzineZMinutami (godzinaCala: String): String{
        //loguj("Pokaz cała Godzina z Minutami : $localGodzina")
        var localTimeNow: LocalTime = LocalTime.now()
        var localGodzina = localTimeNow.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        return localGodzina
    }
}