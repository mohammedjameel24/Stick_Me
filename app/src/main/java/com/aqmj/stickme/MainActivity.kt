package com.aqmj.stickme

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RemoteViews
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var noteEdit: EditText? = null
    private var TVSize: TextView? = null
    var currentSize = 15f
    var note = StickMeNote()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteEdit = findViewById(R.id.Edit)
        val fontSizeIncrease = findViewById<Button>(R.id.fontSizeIncrease)
        val fontSizeReducer = findViewById<Button>(R.id.fontSizeReducer)
        TVSize = findViewById(R.id.TVSize)
        currentSize = noteEdit?.textSize ?: 15f
        TVSize?.text = currentSize.toString()
        noteEdit?.setText(note.getStick(this))
    }

    fun fontSizeReducer(view: View?) {
        TVSize?.text = currentSize.toString()
        currentSize--
        noteEdit?.textSize = currentSize
    }

    fun fontSizeIncrease(view: View?) {
        TVSize?.text = currentSize.toString()
        currentSize++
        noteEdit?.textSize = currentSize
    }

    fun saveButton(view: View?) {
        note.setStick(noteEdit?.text.toString(), this)
        updateWidget()
        Toast.makeText(this, "note has been updated", Toast.LENGTH_SHORT).show()
    }

    private fun updateWidget() {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val remoteViews = RemoteViews(this.packageName, R.layout.widget_layout)
        val thsWidget = ComponentName(this, AppWidget::class.java)
        remoteViews.setTextViewText(R.id.TVWidget, noteEdit?.text.toString())
        appWidgetManager.updateAppWidget(thsWidget, remoteViews)
    }
}