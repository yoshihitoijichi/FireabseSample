package jp.co.moapp.firebasesample.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.co.moapp.firebasesample.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_remote_config_button.setOnClickListener {
            startActivity(Intent(this, RemoteConfigActivity::class.java))
        }

        main_crashlytics_button.setOnClickListener {
            startActivity(Intent(this, CrashlyticsActivity::class.java))
        }

    }
}
