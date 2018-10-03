import utest._

object HelloSpec extends TestSuite {

 val tests = Tests {
    'example_test - {
      assert(Hello.world() == "hello world")
    }
 }

}
      