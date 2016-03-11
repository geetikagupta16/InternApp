import org.specs2.mutable._
import play.api.test.Helpers._
import play.api.test.{WithApplication, _}

class DashboardControllerSpec extends Specification {

  "DashBoard Controller " should {


    "CHECK FOR Rendering of DashBoard page" in new WithApplication {
      val res = route(FakeRequest(GET, "/getForm")).get
      contentType(res) must beSome.which(_ == "text/html")
    }



    " rendering of get Award form render" in new WithApplication {
      val res = route(FakeRequest(GET, "/getlanguages").withSession("email"->"john@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get Award form render" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAssignment").withSession("email"->"john@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get Programming language form render" in new WithApplication {
      val res = route(FakeRequest(GET, "/getProgLanguages").withSession("email"->"john@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }
  }



}
