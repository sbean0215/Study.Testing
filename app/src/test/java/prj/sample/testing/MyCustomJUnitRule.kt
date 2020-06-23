package prj.sample.testing

import android.util.Log
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class MyCustomJUnitRule : TestRule {

    lateinit var base: Statement
    lateinit var description: Description

    override fun apply(base: Statement?, description: Description?): Statement {
        this.base = base!!
        this.description = description!!
        return MyStatement(this.base, this.description)
    }

    class MyStatement(val base:Statement, val description: Description) : Statement() {

        @Throws(Throwable::class)
        override fun evaluate() {
            //테스트 전에 할 작업
            System.out.println("${description.methodName} Started")
            try {
                base.evaluate() // 테스트 실행
            } finally {
                //테스트 후에 할 작업
                System.out.println("${description.methodName} Finished")
            }
        }

    }
}