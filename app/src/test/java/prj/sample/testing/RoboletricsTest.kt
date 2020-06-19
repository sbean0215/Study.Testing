package prj.sample.testing

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import androidx.test.ext.junit.rules.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers

import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule

import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.CoreMatchers.`is` as IS


//@RunWith(RobolectricTestRunner::class)
@RunWith(AndroidJUnit4::class) //동일
class RoboletricsTest {

    @Before
    fun setUp () {
        val app = ApplicationProvider.getApplicationContext<Application>()
            .apply {

            }
    }


    @Test
    fun `장치구성변경 테스트 - 디바이스 회전` () {

        val activityController = Robolectric.buildActivity(BActivity::class.java)
        activityController.setup()

//        val activityController = ActivityScenario.launch(BActivity::class.java)

        RuntimeEnvironment.setQualifiers("+land")

        activityController.configurationChange()

        assertThat(RuntimeEnvironment.getQualifiers().contains("land"), IS(true))
        assertThat(RuntimeEnvironment.getQualifiers().contains("port"), IS(false))
    }


    @Test
    fun  `액티비티 오픈 테스트_레보로틱스 기본 api 및 가이드`() {

        val mainActivity = Robolectric.setupActivity(MainActivity::class.java) //setUpActiviti -> 생명주기 등의 이슈가 있음
        mainActivity.findViewById<Button>(R.id.btn_open_activity_b).performClick()

        val expectedIntent = Intent(mainActivity, BActivity::class.java)
        val actual: Intent = shadowOf(RuntimeEnvironment.application).getNextStartedActivity()

        assertEquals(expectedIntent.component, actual.component)

        Robolectric.buildActivity(MainActivity::class.java).setup()
    }



    @get:Rule var mainActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `액티비티 오픈 테스트_androidX 사용`() {

        //레보로틱스 4.0 androidX 와의 호환성
        //일부 기능 androidX 사용 권장

        lateinit var expectedIntent: Intent

        mainActivityScenarioRule.scenario.onActivity {

            expectedIntent = Intent(it, BActivity::class.java)
            it.findViewById<Button>(R.id.btn_open_activity_b).performClick()
        }

        val actual: Intent = shadowOf(ApplicationProvider.getApplicationContext<Application>()).nextStartedActivity
        assertEquals(expectedIntent.component, actual.component)


        ActivityScenario.launch(MainActivity::class.java)

    }


    @Test
    fun `생명주기 테스트 _ 레보로틱스 기본`() {
        val activityController = Robolectric.buildActivity(MainActivity::class.java).setup()

        activityController
            .pause()
            .stop()

//        assertThat(activityController.get().testVariable, IsNull())
        assertThat(activityController.get().testVariable, nullValue())
//        assertThat(activityController.get().testVariable, notNullValue()) // 실패

    }

    @Test
    fun `생명주기 테스트 _ androidX`() {

        val scenario =  mainActivityScenarioRule.scenario

        scenario.moveToState(Lifecycle.State.CREATED)

        scenario.onActivity {
            assertThat(it.testVariable, nullValue())
        }

    }





}