package com.muhsanapps.eventbusandroidlibrary.services

import android.app.IntentService
import android.content.Intent
import android.content.Context
import com.muhsanapps.eventbusandroidlibrary.events.MessageEvent
import com.muhsanapps.eventbusandroidlibrary.events.MessageEvent2
import org.greenrobot.eventbus.EventBus

class MyIntentService : IntentService("MyIntentService") {

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {

        if (intent != null){
            val message1: String? = intent.getStringExtra("key1")
            val message2: String? = intent.getStringExtra("key2")

            // do some work here

            var messageEvent = MessageEvent("$message1 from Service")

            EventBus.getDefault().post(messageEvent)

            Thread.sleep(3000)

            var messageEvent2 = MessageEvent2("$message2 from Service")

            EventBus.getDefault().post(messageEvent2)

        }

    }
}