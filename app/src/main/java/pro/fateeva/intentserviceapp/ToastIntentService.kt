package pro.fateeva.intentserviceapp

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast

private const val TAG = "@@@"
class ToastIntentService : IntentService("ToastIntentService") {
    companion object {
        private const val TEXT_EXTRA_KEY = "TEXT_EXTRA_KEY"

        fun launchIntent(context: Context, message: String) {
            val serviceIntent = Intent(context, ToastIntentService::class.java)
            serviceIntent.putExtra(TEXT_EXTRA_KEY, message)
            context.startService(serviceIntent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "on create")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "on destroy")
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "on handle intent")
        val message = intent?.getStringExtra(TEXT_EXTRA_KEY) ?: "EMPTY"
        Handler(Looper.getMainLooper()).post {
            showToast(message)
        }
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}