import org.specs2.mutable._
import play.api.test.Helpers._
import play.api.test.{WithApplication, _}

class DashboardControllerSpec extends Specification {

  "DashBoard Controller " should {


    "CHECK FOR Rendering of DashBoard page" in new WithApplication {
      val res = route(FakeRequest(GET, "/dashboard").withSession("email"->"john@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }


    " rendering of get Award form render" in new WithApplication {
      val res = route(FakeRequest(GET, "/getlanguages").withSession("email"->"john@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get Assignment form render" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAssignment").withSession("email"->"john@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get Programming language form render" in new WithApplication {
      val res = route(FakeRequest(GET, "/getProgLanguages").withSession("email"->"john@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }


    " rendering of get Award form for Admin" in new WithApplication {
      val res = route(FakeRequest(GET, " /adminDashboard").withSession("email"->"admin@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get Assignment for ADMIN" in new WithApplication {
      val res = route(FakeRequest(GET, " /getAllAssignments ").withSession("email"->"admin@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get Awards for admin" in new WithApplication {
      val res = route(FakeRequest(GET, " /getAllAwards ").withSession("email"->"admin@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get Programming language for Admin" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllProgLanguages  ").withSession("email"->"admin@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }
    " rendering of all interns for Admin" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllInterns").withSession("email"->"admin@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get all languages for admin" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllLanguages  ").withSession("email"->"admin@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }
  }

}
