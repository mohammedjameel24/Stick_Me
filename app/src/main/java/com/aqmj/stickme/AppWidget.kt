package com.aqmj.stickme

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class AppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        //super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (appWidgetId in appWidgetIds) {
            val launchIntent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, 0)
            val remoteViews = RemoteViews(context.packageName, R.layout.widget_layout)
            remoteViews.setOnClickPendingIntent(R.id.TVWidget, pendingIntent)
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }
    }
}