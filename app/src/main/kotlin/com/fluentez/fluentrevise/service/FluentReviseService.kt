package com.fluentez.fluentrevise

import android.app.Service
import android.content.Intent
import android.os.IBinder

class FluentReviseService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
