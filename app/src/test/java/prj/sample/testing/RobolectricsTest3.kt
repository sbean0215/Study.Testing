package prj.sample.testing

import android.os.Build
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

import org.junit.Rule
import org.junit.Test
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class) //동일
class RobolectricsTest3 {

    @get:Rule val myTestRule = MyCustomJUnitRule()


    @Test
    @Config (sdk = intArrayOf(Build.VERSION_CODES.M))
    fun `config 를 사용한 버전별 아웃풋 테스트 _ M`() {

        val myUtils = MyUtils()
        assertEquals(myUtils.getMyString(), "Just cookies!")
    }

    @Test
    @Config (sdk = intArrayOf(Build.VERSION_CODES.O))
    fun `config 를 사용한 버전별 아웃풋 테스트 _ O`() {

        val myUtils = MyUtils()
        assertEquals(myUtils.getMyString(), "Oreo cookies!")
    }


}
