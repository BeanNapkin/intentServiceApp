package pro.fateeva.intentserviceapp

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class ToastService : Service() {

    companion object {
        private const val MESSAGE_KEY = "KEY"

        fun launchIntent(context: Context, message: String): Intent {
            val serviceIntent = Intent(context, ToastService::class.java)
            serviceIntent.putExtra(MESSAGE_KEY, message)
            return serviceIntent
        }
    }

    override fun onBind(intent: Intent): IBinder = ToastServiceBinder(this)

    override fun onCreate() {
        super.onCreate()
        Log.d("@@@", "On create")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("@@@", "On destroy")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val message = intent.extras?.getString(MESSAGE_KEY) ?: "EMPTY"
        showToast(message)
        Log.d("@@@", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}

class ToastServiceBinder(val service: ToastService) : Binder() {

}

