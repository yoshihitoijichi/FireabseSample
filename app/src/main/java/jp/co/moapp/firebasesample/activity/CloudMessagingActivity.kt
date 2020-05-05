package jp.co.moapp.firebasesample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import jp.co.moapp.firebasesample.R
import timber.log.Timber

class CloudMessagingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cloud_messaging)

        // インスタンスIDの自動生成を有効化する場合、true
        // AndroidManifestにて自動生成を禁止にしていない場合、不要
//        FirebaseMessaging.getInstance().isAutoInitEnabled = true

        // Current Notificationトークンの取得
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    // token取得失敗
                    Timber.d("getInstanceId failed ${task.exception}")
                    return@OnCompleteListener
                }

                // new Instance ID token
                val token = task.result?.token

                val msg = "InstanceID Token: $token"
                Timber.d(msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })
    }
}
