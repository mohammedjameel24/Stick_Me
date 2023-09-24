package com.aqmj.stickme

import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException

class StickMeNote {
    fun getStick(context: Context): String {
        val file = File(context.filesDir.path + "/todo.txt")
        val text = StringBuilder()
        try {
            val br = BufferedReader(FileReader(file))
            var line: String?
            while (br.readLine().also { line = it } != null) {
                text.append(line)
                text.append("\n")
            }
            br.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return text.toString()
    }

    fun setStick(textToBeSaved: String, context: Context) {
        var fos: FileOutputStream? = null
        try {
            fos = context.applicationContext.openFileOutput("todo.txt", Context.MODE_PRIVATE)
            fos.write(textToBeSaved.toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}