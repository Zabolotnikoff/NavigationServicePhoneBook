package com.example.navigationservicephonebook.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {
    private var iterationCounter = 0
    private var myHandler = Handler()
    private var myRunnable = Runnable {printIterationNumber()}

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Данный метод не реализован, ибо не требовалось ;)")
    }

    override fun onStartCommand(intent: Intent, p1: Int, p2: Int): Int {
        Toast.makeText(this, "Сервис запущен.", Toast.LENGTH_SHORT).show()
        myHandler.postDelayed(myRunnable, 3000)
        return START_STICKY // возвращает 1
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Сервис остановлен.", Toast.LENGTH_SHORT).show()
        myHandler.removeCallbacks(myRunnable)
    }

    private fun printIterationNumber() {
        Toast.makeText(this, "Работает сервис. Итерация номер: ${++iterationCounter}", Toast.LENGTH_SHORT).show()
        myHandler.postDelayed(myRunnable, 3000)
    }
}