package com.leapi.forceoom

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Debug
import android.util.Log
import androidx.annotation.RequiresApi
import java.lang.Exception

class MemoryTracking(val context: Context) {

    private val memoryState: ActivityManager.MemoryInfo = ActivityManager.MemoryInfo()

    private val TAG = "FragmentTracker"

    fun getActivityManagerMemoryInfo() {
        try {
            val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityManager.getMemoryInfo(this.memoryState)
            val availMem = this.memoryState.availMem
            val lowMemory = this.memoryState.lowMemory
            val threshold = this.memoryState.threshold
            val totalMem = this.memoryState.totalMem
            //Heap Size
            val maxHeapSize = activityManager.memoryClass
            val largestHeapSize = activityManager.largeMemoryClass


            Log.d(TAG, "ActivityManager: availMem: $availMem, lowMemory: $lowMemory, threshold: $threshold, totalMem: $totalMem, maxHeapSize: $maxHeapSize, largestHeapSize: $largestHeapSize")
        } catch (e: Exception) {
            Log.d(TAG, "$e")
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getDebugMemoryInfo() {
        try {
            val memInfo = Debug.MemoryInfo()
            Debug.getMemoryInfo(memInfo)
            val memoryStats = memInfo.memoryStats
            val totalPrivateClean = memInfo.totalPrivateClean
            val totalPrivateDirty = memInfo.totalPrivateDirty
            val totalPss = memInfo.totalPss
            val totalSharedClean = memInfo.totalSharedClean
            val totalSharedDirty = memInfo.totalSharedDirty
            val totalSwappablePss = memInfo.totalSwappablePss


            val dalvikPrivateDirty = memInfo.dalvikPrivateDirty
            val dalvikPss = memInfo.dalvikPss
            val dalvikSharedDirty = memInfo.dalvikSharedDirty
            val nativePrivateDirty = memInfo.nativePrivateDirty
            val nativePss = memInfo.nativePss
            val nativeSharedDirty = memInfo.nativeSharedDirty
            val otherPrivateDirty = memInfo.otherPrivateDirty
            val otherPss = memInfo.otherPss
            val otherSharedDirty = memInfo.otherSharedDirty

            Log.d(TAG, "Debug.memoryInfo: memoryStats: $memoryStats, totalPrivateClean: $totalPrivateClean, totalPrivateDirty: $totalPrivateDirty, totalPss: $totalPss, totalSharedClean: $totalSharedClean, totalSharedDirty: $totalSharedDirty, totalSwappablePss: $totalSwappablePss, dalvikPrivateDirty: $dalvikPrivateDirty, dalvikPss: $dalvikPss, dalvikSharedDirty: $dalvikSharedDirty, nativePrivateDirty: $nativePrivateDirty, nativePss: $nativePss, nativeSharedDirty: $nativeSharedDirty, otherPrivateDirty: $otherPrivateDirty, otherPss: $otherPss, otherSharedDirty: $otherSharedDirty ")
        } catch (e: Exception) {
            Log.d(TAG, "$e")
        }
    }

    fun debugMem() {
        try {
            val nativeHeapAllocatedSize = Debug.getNativeHeapAllocatedSize()
            val nativeHeapFreeSize = Debug.getNativeHeapFreeSize()
            val nativeHeapSize = Debug.getNativeHeapSize()
            val pss = Debug.getPss()

            Log.d(TAG, "Debug: nativeHeapAllocatedSize: $nativeHeapAllocatedSize, nativeHeapFreesize: $nativeHeapFreeSize, nativeHeapSize: $nativeHeapSize, pss: $pss")
        } catch (e: Exception) {
            Log.d(TAG, "$e")
        }
    }

    fun runtimeMem() {
        //Heap Size
        try {
            val runtime: Runtime = Runtime.getRuntime()
            val freeMemory = runtime.freeMemory()
            val maxMemory = runtime.maxMemory()
            val totalMemory = runtime.totalMemory()

            Log.d(TAG, "Runtime: freeMemory: $freeMemory, maxMemory: $maxMemory, totalMemory: $totalMemory")
        } catch (e: Exception) {
            Log.d(TAG, "$e")
        }
    }

}
