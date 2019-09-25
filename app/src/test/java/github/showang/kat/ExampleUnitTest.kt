package github.showang.kat

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testMeasure() {
        val printCost = { cost: Long -> print("Cost time: $cost ms") }
        val measureDoubleSum = { max: Int ->
            measure {
                (1..max).map { 2 * it }
                    .reduce { acc, i -> acc + i }
                    .also { print("total: $it\n") }
            }
        }
        perform(30000 bind measureDoubleSum andThen printCost)
    }
}
