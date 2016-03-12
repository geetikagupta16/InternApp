import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test.{WithApplication, _}

import scala.concurrent.Await
import scala.concurrent.duration._


/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  * For more information, consult the wiki.
  */

@RunWith(classOf[JUnitRunner])
class LoginControllerSpec extends Specification {

  "Login Controller " should {


    "CHECK FOR Rendering of form" in new WithApplication {
      val res = route(FakeRequest(GET, "/getForm")).get
      contentType(res) must beSome.which(_ == "text/html")
    }
  

    "CHECK FOR LOGIN" in new WithApplication {
      val res = route(FakeRequest(POST, "/auth").withFormUrlEncodedBody
      ("email" -> "john@gmail.com", "password" -> "abcdef")).get
      val result = Await.result(res, 2 seconds)
      redirectLocation(res) must beSome("/dashboard")
    }

    "CHECK FOR ADMIN LOGIN" in new WithApplication {
      val res = route(FakeRequest(POST, "/auth").withFormUrlEncodedBody
      ("email" -> "admin@gmail.com", "password" -> "admin")).get
      val result = Await.result(res, 2 seconds)
      redirectLocation(res) must beSome("/adminDashboard")
    }

    "CHECK FOR unsucessfull LOGIN" in new WithApplication {
      val res = route(FakeRequest(POST, "/auth").withFormUrlEncodedBody
      ("email" -> "jon@gmail.com", "password" -> "abcdef")).get
      val result = Await.result(res, 2 seconds)
      status(res) must equalTo(SEE_OTHER)

    }
  }
}