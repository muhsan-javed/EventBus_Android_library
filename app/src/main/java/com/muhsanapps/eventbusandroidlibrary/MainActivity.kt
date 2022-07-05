package com.muhsanapps.eventbusandroidlibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.muhsanapps.eventbusandroidlibrary.events.MessageEvent
import com.muhsanapps.eventbusandroidlibrary.events.MessageEvent2
import com.muhsanapps.eventbusandroidlibrary.services.MyIntentService
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private lateinit var txt :TextView
    private lateinit var btnRunCode : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt = findViewById(R.id.tv)
        btnRunCode = findViewById(R.id.button)

        EventBus.getDefault().register(this)

        btnRunCode.setOnClickListener {

            val serviceIntent = Intent(this@MainActivity, MyIntentService::class.java)

            serviceIntent.putExtra("key1", "This is message 1")
            serviceIntent.putExtra("key2", "This is message 2")

            startService(serviceIntent)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onEventReceived(event : MessageEvent){

        Toast.makeText(this, "MessageEvent 1 method is called", Toast.LENGTH_SHORT).show()

        if (txt.text.equals("Ready to learn"))
            txt.text = ""

        txt.append("${event.message} \n")
        txt.append(Thread.currentThread().name + "\n")

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onEventReceived(event : MessageEvent2){

        Toast.makeText(this, "MessageEvent 1 method is called", Toast.LENGTH_SHORT).show()

        if (txt.text.equals("Ready to learn"))
            txt.text = ""

        txt.append("${event.message} \n")
        txt.append(Thread.currentThread().name)

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}