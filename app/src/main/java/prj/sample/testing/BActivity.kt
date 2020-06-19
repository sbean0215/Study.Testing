package prj.sample.testing

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class BActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_b)

        Log.d("BEAN", "onCREATE!")
    }
}                                                                                                                                                           
