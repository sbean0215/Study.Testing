package prj.sample.testing

import android.app.Application
import android.util.Log
import org.robolectric.TestLifecycleApplication
import org.robolectric.shadows.ShadowLog
import java.lang.reflect.Method

class CustomizingRobolectricTestRunner : MyApplication(), TestLifecycleApplication {

    override fun onCreate() {
        super.onCreate()
        setLog(enabled = false)

        //inject mock(mockito) or shadow(roboletric)
    }

    override fun beforeTest(method: Method?) {
        Log.d("BEAN", "-------------------------------------TEST START------------------------------------")
        System.out.println("------------------------------------- START ----------------------------------")
    }

    override fun prepareTest(test: Any?) {
    }

    override fun afterTest(method: Method?) {
        Log.d("BEAN", "-------------------------------------TEST END---------------------------------------\n\n")
        System.out.println("------------------------------------- END ------------------------------------\n\n")

    }

    fun setLog(enabled: Boolean) {
        if (enabled)
            ShadowLog.stream = System.out
    }
}