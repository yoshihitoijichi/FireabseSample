package jp.co.moapp.firebasesample

import android.app.Application
import timber.log.Timber

class FirebaseSampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setupTimber()
        setupFIAM()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(MyCrashTree())
        }
    }

    private fun setupFIAM(){
//        FirebaseInAppMessaging.getInstance().setMessagesSuppressed(true)    // 抑制
    }
}