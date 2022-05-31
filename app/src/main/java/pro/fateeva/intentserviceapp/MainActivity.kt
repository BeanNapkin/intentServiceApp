package pro.fateeva.intentserviceapp

import android.content.ComponentName
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import pro.fateeva.intentserviceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, binder: IBinder?) {
            val service = (binder as ToastServiceBinder).service
            service.showToast("I'm connected")
            Log.d("@@@", "onServiceConnected")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.d("@@@", "onServiceDisconnected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val service = ToastService.launchIntent(this, "Start custom service")

        binding.toastServiceButton.setOnClickListener {
            startService(service)
        }

        binding.bindToastServiceButton.setOnClickListener {
            bindService(service, connection, BIND_AUTO_CREATE)
        }

        binding.toastIntentServiceButton.setOnClickListener{
            ToastIntentService.launchIntent(this, "Start intent service")
        }
    }
}