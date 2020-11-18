package com.leapi.forceoom

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

@RequiresApi(Build.VERSION_CODES.M)
class ForceOOM(private val application: Application) {

    private val memoryState = MemoryTracking(application)

    private val TAG = "FragmentTracker"


    init {

        val myTimer = Timer()
        myTimer.scheduleAtFixedRate(object: TimerTask() {
            override fun run() {
                val checkMemoryRunnable = LogMemoryRunnable()
                checkMemoryRunnable.run()
            }
        }, 0, 500)

        Timer().scheduleAtFixedRate(object: TimerTask() {
            override fun run() {
                val breakItRunnable =  BreakIt()
                breakItRunnable.run()
            }
        },1000,100)
    }

    inner class BreakIt(): Runnable {

        private val mLists = mutableListOf<Array<Byte>>()

        override fun run() {
            while(true) {
                val myByte: Byte = (1024L*1024L*20).toByte()

                val twn = arrayOf(myByte)

                mLists.add(twn)
            }
        }
    }

    inner class LogMemoryRunnable() : Runnable {


        @RequiresApi(Build.VERSION_CODES.M)
        override fun run() {
            logCurrentVisibleFragmentsAndMemoryFootprint()
        }


        @RequiresApi(Build.VERSION_CODES.M)
        private fun logCurrentVisibleFragmentsAndMemoryFootprint() {

            memoryState.getActivityManagerMemoryInfo()
            memoryState.getDebugMemoryInfo()
            memoryState.debugMem()
            memoryState.runtimeMem()

        }

    }

}
