package prj.sample.testing

import android.os.Build

class MyUtils {


    fun getMyString() : String =
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { "Oreo cookies!" }
        else { "Just cookies!"}


}