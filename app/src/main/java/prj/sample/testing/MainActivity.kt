package prj.sample.testing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    var testVariable : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_open_activity_b).setOnClickListener {
            startActivity(Intent(baseContext, BActivity::class.java))
        }

        testVariable = "바보"
    }

    override fun onPause() {
        super.onPause()

        testVariable = null
    }
}