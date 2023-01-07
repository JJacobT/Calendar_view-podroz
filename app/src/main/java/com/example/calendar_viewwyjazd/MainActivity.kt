package com.example.calendar_viewwyjazd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextClock
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Własciwy format daty
        fun konwersja(czasowa: Long): List<Int>{
            val date = Date(czasowa)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val formDate = dateFormat.format(date).split("/").map {
                it.toInt()
            }
            return formDate
        }
        //Zmienne
        val wyjazd = findViewById<Button>(R.id.wyjazd_button)
        val powrot = findViewById<Button>(R.id.przyjazd_button)
        val kalendarz = findViewById<CalendarView>(R.id.calendarView)
        val obliczanie = findViewById<Button>(R.id.zaplanuj_button)
        val data = arrayListOf(konwersja(kalendarz.date)[0] ,konwersja(kalendarz.date)[1] ,konwersja(kalendarz.date)[2])
        val czas = findViewById<TextView>(R.id.wynik_txtview)
        val wyjazd_txt = findViewById<TextView>(R.id.wyj_txtview)
        val powrot_txt = findViewById<TextView>(R.id.przyj_txtview)
        val start = mutableListOf(0,0,0)
        val koniec = mutableListOf(0,0,0)


        //Blokada
        kalendarz.minDate = Date().time
        kalendarz.maxDate = Date().time + 109572660000000
        //Lista
        kalendarz.setOnDateChangeListener { _, d, m, y ->
            data[0] = y
            data[1] = m + 1
            data[2] = d
        }

        //Wyliczanie dni
        fun rezerwacja(koniec: MutableList<Int>, poczatek : MutableList<Int>, czas_trwania : TextView) {
            val wyjazd = (poczatek[2] * 360) + (poczatek[1] * 30) + poczatek[0]
            val przyjazd = (koniec[2] * 360) + (koniec[1] * 30) + koniec[0]
            val ilosc_dni = wyjazd.toChar() - przyjazd.toChar()
            czas_trwania.text = "${ilosc_dni.absoluteValue + 1}"
        }
        //Przyciski
        obliczanie.setOnClickListener {
            rezerwacja(start, koniec, czas)
        }
        powrot.setOnClickListener {
            for (i in 0 until 3)
                start[i] = data[i]
            powrot_txt.text =  "${start[0]}-${start[1]}-${start[2]}"
        }

        wyjazd.setOnClickListener {
            for (i in 0 until 3)
                koniec[i] = data[i]
            wyjazd_txt.text = "${koniec[0]}-${koniec[1]}-${koniec[2]}"
        }



    }
}