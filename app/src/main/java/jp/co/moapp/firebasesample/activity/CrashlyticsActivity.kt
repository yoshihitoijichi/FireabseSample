package jp.co.moapp.firebasesample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import jp.co.moapp.firebasesample.R
import java.lang.Exception

class CrashlyticsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crashlytics)

        // イベントを送ると詳細な情報がとれる
        val firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("param1", "value")
        firebaseAnalytics.logEvent("test_event", bundle)

        throw Exception("test exception")
    }
}
