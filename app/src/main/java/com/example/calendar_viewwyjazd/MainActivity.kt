package com.example.calendar_viewwyjazd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextClock
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

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
        //zmienne
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
        kalendarz.maxDate = Date().time + 88888888888
        //Lista
        kalendarz.setOnDateChangeListener{_, d, m, y ->
            data[0] = y
            data[1] = m+1
            data[2] = d
    }
}