package com.recepgemalmaz.pazarama_bootcamp_ders_5_1_coroutine_sample_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("ql", "UI ThreadID: ${Thread.currentThread().id}")

        // Coroutine ile calisirken sistem cokmuyor.
        //LIGHWEIGHT THREAD

        //Runblocking UI Threadi bloklar. UI Threadi bloklamak istiyorsak runBlocking kullanmayiz.
        //Ancak CoroutineScope ile calisirken UI Threadi bloklamaz. ayri bir threadde calisir.
        //Not: Coroutineler thred pool kullanir. Thread pool de threadlerin sayisi bellidir.

        runBlocking {
            launch(Dispatchers.Main) {
                 println("Main: ${Thread.currentThread().name}")

            }
            launch(Dispatchers.IO) {
                println("IO: ${Thread.currentThread().name}")

            }
            launch(Dispatchers.Default) {
                println("Default: ${Thread.currentThread().name}")

            }
            launch (Dispatchers.Unconfined){
                println("Unconfined: ${Thread.currentThread().name}")

            }
        }




        Log.e("ql", "UI ThreadID: ${Thread.currentThread().id}")


    }

    fun Test()
    {
        for (i in 1..5)
        {
            Thread.sleep(1000)
            Log.e("ql", Calendar.getInstance().time.toString() + " " +
                    "ThreadID: ${Thread.currentThread().id}")
        }
    }



}


//-------------------------------------------------------------------------------------------------------------------------

/*
Dispatchers Nedir?
Bir corotuine hangi threadde calisacagini belirleriz

Launch Nedir?
Coroutine baslatir.

Suspension Fonksiyon Nedir?
Coroutine icinde kullanilir. Coroutine durdurur. Coroutine durdurulurken coroutine icindeki diger islemler devam eder.

Dispatchers.Main //UI Thread Ui islemleri
Dispatchers.IO //Network, Database Dosya islemleri gibi islemler
Dispatchers.Default //CPU Intensive matematiksel islemler
Dispatchers.Unconfined //UI Threadi bloklamaz. UI Threadi bloklamak istiyorsak runBlocking kullanmayiz. Icinden cagrilan yere gore bir usten miras alir.

GlobalScope ve CoroutineScope Farki
GlobalScope uygulama kapanana kadar calisir. Uygulamanin tumunu kapsar.
CoroutineScope ise bir coroutine icinde calisir. CoroutineScope icindeki coroutine calismadan coroutineScope biter.
 */

//-------------------------------------------------------------------------------------------------------------------------
/*
//Alttaki ornekte tum kod UI thredde calismaz cunku coroutineScope a (Dispatchers.Default).launch dedik boylece  baska worker threads olusturduk ve UI threadi bloklamadik.
     Log.e("ql", "UI ThreadID: ${Thread.currentThread().id}")

        // Coroutine ile calisirken sistem cokmuyor.
        //LIGHWEIGHT THREAD

        //Runblocking UI Threadi bloklar. UI Threadi bloklamak istiyorsak runBlocking kullanmayiz.
        //Ancak CoroutineScope ile calisirken UI Threadi bloklamaz. ayri bir threadde calisir.

        runBlocking {
            delay(6000L)
            Log.e("ql", "RunBlocking ThreadID: ${Thread.currentThread().id}")

            CoroutineScope(Dispatchers.Default).launch {
                delay(7000L)
                Log.e("ql", "Coroutine ThreadID: ${Thread.currentThread().id}")
            }
        }




        Log.e("ql", "UI ThreadID: ${Thread.currentThread().id}")
 */

//-------------------------------------------------------------------------------------------------------------------------

/*
//Alttaki ornekte tum kod UI thredde calisir cunku coroutineScope a (Dispatchers.Default).launch diyerek baska worker threads olusturmadik.
    Log.e("ql", "UI ThreadID: ${Thread.currentThread().id}")

        // Coroutine ile calisirken sistem cokmuyor.
        //LIGHWEIGHT THREAD

        //Runblocking UI Threadi bloklar. UI Threadi bloklamak istiyorsak runBlocking kullanmayiz.
        //Ancak CoroutineScope ile calisirken UI Threadi bloklamaz. ayri bir threadde calisir.

        runBlocking {
            delay(6000L)
            Log.e("ql", "RunBlocking ThreadID: ${Thread.currentThread().id}")

            coroutineScope {
                delay(7000L)
                Log.e("ql", "Coroutine ThreadID: ${Thread.currentThread().id}")
            }
        }




        Log.e("ql", "UI ThreadID: ${Thread.currentThread().id}")
 */

//-------------------------------------------------------------------------------------------------------------------------

//runBlocking: UI Threadi bloklar. UI Threadi bloklamak istiyorsak runBlocking kullanmayiz.
/*
    Log.e("ql", "UI ThreadID: ${Thread.currentThread().id}")

        // Coroutine ile calisirken sistem cokmuyor.
        //LIGHWEIGHT THREAD
        runBlocking {
            launch {
                delay(7000L)
                Log.e("ql", "RunBlocking ThreadID: ${Thread.currentThread().id}")
            }
        }

        Log.e("ql", "UI ThreadID: ${Thread.currentThread().id}")
 */

//-------------------------------------------------------------------------------------------------------------------------

//Runblocking UI Threadi bloklar. UI Threadi bloklamak istiyorsak runBlocking kullanmayiz.
//Ancak CoroutineScope ile calisirken UI Threadi bloklamaz. ayri bir threadde calisir.

/*
CoroutineScope(Dispatchers.Default).launch {
    delay(7000L)
    Log.e("ql", "Coroutine ThreadID: ${Thread.currentThread().id}")
}

Log.e("ql", "UI ThreadID: ${Thread.currentThread().id}")

 */

//-------------------------------------------------------------------------------------------------------------------------

//GLOBAL SCOPE : Uygulama kapanana kadar calisir. Uygulamanin tumunu kapsar.
/*
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Coroutine ile calisirken sistem cokmuyor.
    //LIGHWEIGHT THREAD

    GlobalScope.launch(Dispatchers.Default) {
        repeat(100000000){
            Test()
        }
    }

}*/


//-------------------------------------------------------------------------------------------------------------------------

//Tredler ile calisirken sistem cokuyor ancak coroutine ile calisirken cokmuyor.
/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for (x in 1..100000000) {
            var t = Thread(Runnable {
                Test()
            })

            t.start()
        }

    }

    fun Test()
    {
        for (i in 1..5)
        {
            Thread.sleep(1000)
            Log.e("ql", Calendar.getInstance().time.toString() + " " +
                    "ThreadID: ${Thread.currentThread().id}")
        }
    }



}*/