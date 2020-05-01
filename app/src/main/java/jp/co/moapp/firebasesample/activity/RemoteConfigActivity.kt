package jp.co.moapp.firebasesample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import jp.co.moapp.firebasesample.R
import kotlinx.android.synthetic.main.activity_remote_config.*
import timber.log.Timber

class RemoteConfigActivity : AppCompatActivity() {

    private lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote_config)

        setup()
        fetchWelcome()
    }

    private fun setup() {
        // firebase-config-ktx により簡潔に書ける
        remoteConfig = Firebase.remoteConfig

        // debug modeの有効化
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600 // fetch間隔 。頻繁に取得できないので、debug時は短く設定する
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        // デフォルト値設定
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        // apply default
        main_first_text.text = remoteConfig[LOADING_PHRASE_CONFIG_KEY].asString()
        main_second_text.text = remoteConfig[LOADING_PHRASE_CONFIG_KEY].asString()
    }

    private fun fetchWelcome() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Timber.d("Config params updated: $updated")
                    Toast.makeText(this, "Fetch and activate succeeded", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Fetch failed", Toast.LENGTH_SHORT).show()
                }
                displayWelcomeMessage()
            }
    }

    private fun displayWelcomeMessage() {
        // 失敗してたらxmlより反映。成功してたら firebase console にて設定した値
        main_first_text.text = remoteConfig.getBoolean(WELCOME_MESSAGE_CAPS_KEY).toString()
        main_second_text.text = remoteConfig.getString(WELCOME_MESSAGE_KEY)
    }

    companion object {
        // Remote Config keys
        private const val LOADING_PHRASE_CONFIG_KEY = "loading_phrase"
        private const val WELCOME_MESSAGE_CAPS_KEY = "welcome_message_caps"
        private const val WELCOME_MESSAGE_KEY = "welcome_message"
    }
}
