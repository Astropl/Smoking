package com.example.smoking

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class OperDB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oper_db)

        //l("createDB: konstruktor first")
        //createDB("dfwrtreye")
        //l("createDB: konstruktor end")

    }


    @SuppressLint("Range")
    fun cosSprawdzam(zamie: String, myDB: SQLiteDatabase): String
    {


       val cursor: Cursor = myDB.rawQuery("select count(*) from dbDay where data <> '1' ", null)
        cursor.moveToFirst()
        val count: String = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)))
        l("Liczba wierszy w tabeli to: $count")
        return zamie
    }


    fun createUstawienia(myDB: SQLiteDatabase) {
        //myDB
        //TODO: Test wywalilem myDBugygyu
        //l("createUstawienia: first")
        try {
            val createTable =
                "CREATE TABLE IF NOT EXISTS dbUstawienia (Id INTEGER primary key autoincrement,cena DOUBLE, iloscWpaczce INT, nazwaPaczki VARCHAR(200),  dlugoscFajki INT)"
            myDB.execSQL(createTable) // change here

            //l("createDB: Baza stworzona")
            val row = ContentValues()
            var dataZDB = ""
            val myCursor: Cursor = myDB.rawQuery("select cena from dbUstawienia", null)
            while (myCursor.moveToNext()) {

                //l("czytam baze")
                dataZDB = myCursor.getString(0)
                //l("Data pobrana to: $dataZDB")
                myCursor.close()
            }

            //cena , iloscWpaczce , nazwaPaczki ,  dlugoscFajki
            if (dataZDB == "") {
                //jest zero
                //l("nie ma nic. DODAJE")
                row.put("cena", "0")
                row.put("iloscWpaczce", "0")
                row.put("nazwaPaczki", "0")
                row.put("dlugoscFajki", "0")
                myDB.insert("dbUstawienia", null, row)
                //l("createDB: dodane pierwsze")
            } else {
                //JCos jest wiec nic nie dodoaje
                //l("createDB: Cos jest wiec nic nie dodoaje")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            //l("Brak Bazy: ")
        }
        //l("createUstawienia: end")

        myDB.close()
    }

    fun zapiszDzisDzien(dzisiajDzien: String,dzisiajGodzina: String,cenaZaPaczke: String,dlugoscFajki: String,nazwaPaczki: String,myDB: SQLiteDatabase)
    { //data , godzina , cena , dlugoscFajki , nazwaFajki
        //jezeli data równa się zero to update. Inaczej insert
        var dataZDB = ""
        //row1.put("Id", 17)  data , godzina , cena , dlugoscFajki , nazwaFajki
        val myCursor: Cursor = myDB.rawQuery("select data from dbDay", null)
        while (myCursor.moveToNext()) {

            //l("czytam baze")
            dataZDB = myCursor.getString(0)
           // l("Data pobrana to: $dataZDB")

        }
        myCursor.close()
        var row = ContentValues()
        row.put("data", dzisiajDzien)
        row.put("godzina", dzisiajGodzina)
        row.put("cena", cenaZaPaczke)
        row.put("dlugoscFajki", dlugoscFajki)
        row.put("nazwaFajki", nazwaPaczki)
        if (dataZDB=="0") {
            //l("Dodam Update")
            myDB.update("dbDay", row,"id = 1",null)
        }
            else
            {
                //l("Dodam inserte")
                myDB.insert("dbDay", null, row)
            }




    }
    fun createDB(myDB: SQLiteDatabase) {
        //l("createDB: first")
       // l("Z przeniesienia $myDB")
        myDB
        try {
            val createTable =
                "CREATE TABLE IF NOT EXISTS dbDay (Id INTEGER primary key autoincrement, data VARCHAR(200), godzina VARCHAR(200), cena DOUBLE, dlugoscFajki INT, nazwaFajki VARCHAR(200))"
            myDB.execSQL(createTable) // change here

           // l("createDB: Baza stworzona")
            val row = ContentValues()
            var dataZDB = ""
            //row1.put("Id", 17)  data , godzina , cena , dlugoscFajki , nazwaFajki
            val myCursor: Cursor = myDB.rawQuery("select data from dbDay", null)
            while (myCursor.moveToNext()) {

               //l("czytam baze")
                dataZDB = myCursor.getString(0)
                //l("Data pobrana to: $dataZDB")

            }
            myCursor.close()

            if (dataZDB == "") {
                //jest zero
               // l("nie ma nic. DODAJE")
                row.put("data", "0")
                row.put("godzina", "0")
                row.put("cena", "0")
                row.put("dlugoscFajki", "0")
                row.put("nazwaFajki", "0")
                myDB.insert("dbDay", null, row)
                //l("createDB: dodane pierwsze")
            } else {
                //JCos jest wiec nic nie dodoaje
               // l("createDB: Cos jest wiec nic nie dodoaje")
            }
            myDB.close()

        } catch (e: SQLException) {
            e.printStackTrace()
           // l("Brak Bazy: ")
        }
        //l("createDB: end")
    }
fun wczytajCeneZaPaczke(cenaZaPaczke: String, myDB: SQLiteDatabase): String {
    //l("wczytajCeneZaPaczke: first")
    myDB
    var dataZDB =""
    //val openTable =
        //"CREATE TABLE IF NOT EXISTS dbUstawienia (Id INTEGER primary key autoincrement,cena DOUBLE, iloscWpaczce INT, nazwaPaczki VARCHAR(200),  dlugoscFajki INT)"

    val myCursor: Cursor = myDB.rawQuery(
        "select cena,iloscWpaczce,nazwaPaczki,dlugoscFajki   from dbUstawienia",
        null
    )
    while (myCursor.moveToNext()) {

       // l("wczytajCeneZaPaczke:czytam baze. Ustawienia. Szukam ceny za paczke")
        dataZDB = myCursor.getString(0)
        //l("wczytajCeneZaPaczke:Dana pobrana to: $dataZDB")

    }
    myCursor.close()
    //myDB.close()
    return dataZDB
}
    fun wczytajDlugoscFajki(dlugoscFajki: String, myDB: SQLiteDatabase): String {
       // l("wczytajDlugoscFajki: first")
        myDB
        var dataZDB = ""
               val myCursor: Cursor = myDB.rawQuery(
            "select cena,iloscWpaczce,nazwaPaczki,dlugoscFajki   from dbUstawienia",
            null
        )
        while (myCursor.moveToNext()) {
            //l("wczytajDlugoscFajki:czytam baze. Ustawienia. Szukam DlugoscFajki")
            dataZDB = myCursor.getString(3)
           // l("DlugoscFajki:Dana pobrana to: $dataZDB")
        }
        myCursor.close()
        //myDB.close()
        return dataZDB
    }
    //wczytajNazwePaczki
    fun wczytajNazwePaczki(nazwaPaczki: String, myDB: SQLiteDatabase): String {
        //l("wczytajNazwePaczki: first")
        myDB
        var dataZDB = ""
        val myCursor: Cursor = myDB.rawQuery(
            "select cena,iloscWpaczce,nazwaPaczki,dlugoscFajki   from dbUstawienia",
            null
        )
        while (myCursor.moveToNext()) {
           // l("wczytajNazwePaczki:czytam baze. Ustawienia. Szukam DlugoscFajki")
            dataZDB = myCursor.getString(2)
            //l("wczytajNazwePaczki: nazwa paczki to: $dataZDB")
        }
        myCursor.close()
        //myDB.close()
        return dataZDB
    }
    fun wczytajIloscFajekDzis(wczytajIloscFajekDzis: Int, myDB: SQLiteDatabase): Int {
        var a = 0
        myDB
        var dataZDB =""
//        val openTable =
//            "CREATE TABLE IF NOT EXISTS dbDay (Id INTEGER primary key autoincrement, data VARCHAR(200), godzina VARCHAR(200), cena DOUBLE, dlugoscFajki INT, nazwaFajki VARCHAR(200))"
        //myDB.execSQL(openTable) // change here
        val myCursor: Cursor = myDB.rawQuery("select data from dbDay", null)
        while (myCursor.moveToNext()) {

           // l("czytam baze")
            dataZDB = myCursor.getString(0)
           // l("Dana pobrana to: $dataZDB")
            a++
        }
        if (dataZDB=="0")
        {
            a=0
        }
       // l("Ilosc fajek to $a")

        myCursor.close()
        myDB.close()
        return a

    }

    fun zapiszUstawienia(
            cenaZaPaczke: String,
            iloscSztuk: String,
            nazwaPaczki: String,
            dlugoscFajki: String, myDB: SQLiteDatabase
    ) {
        myDB
        //
        val row1 = ContentValues()
        row1.put("cena", cenaZaPaczke)
        row1.put("iloscWPaczce", iloscSztuk)
        row1.put("nazwaPaczki", nazwaPaczki)
        row1.put("dlugoscFajki", dlugoscFajki)
        myDB.update("dbUstawienia", row1, "id = 1",null)
        myDB.close()
    }

    fun pobierzUstawienia(myDB: SQLiteDatabase) {

        var dataZDB0 = ""
        var dataZDB1 = ""
        var dataZDB2 = ""
        var dataZDB3 = ""
//cena DOUBLE, iloscWpaczce INT, nazwaPaczki VARCHAR(200),  dlugoscFajki
        val myCursor: Cursor = myDB.rawQuery(
            "select cena,iloscWpaczce,nazwaPaczki,dlugoscFajki   from dbUstawienia",
            null
        )
        while (myCursor.moveToNext()) {

           // l("czytam baze")
            dataZDB0 = myCursor.getString(0)
            dataZDB1 = myCursor.getString(1)
            dataZDB2 = myCursor.getString(2)
            dataZDB3 = myCursor.getString(3)
            cenaZaPaczke = dataZDB0

            iloscSztuk = dataZDB1
            nazwaPaczki = dataZDB2
            dlugoscFajki = dataZDB3

           // l("Data pobrana to: $dataZDB1")

        }
        myCursor.close()
        myDB.close()
    }

//    fun addDay() {
//       // l("addDay: First")
//
//
//
//
//        //l("addDay: end")
//    }

    fun l(s: String) {
        Log.d("OperDB", "Astro: +  $s")
    }

}