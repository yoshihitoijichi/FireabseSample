package jp.co.moapp.firebasesample

import android.util.Log
import timber.log.Timber

/**
 * Created by ijichiyoshihito on 2019-07-08.
 */
class MyCrashTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

        if (priority == Log.ERROR) {
        }

        if (priority == Log.DEBUG) {
        }

        if (priority == Log.WARN) {
        }
    }
}