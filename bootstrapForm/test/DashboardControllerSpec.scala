import org.specs2.mutable._
import play.api.test.Helpers._
import play.api.test.{WithApplication, _}

class DashboardControllerSpec extends Specification {

  "DashBoard Controller " should {

    "CHECK FOR Rendering of DashBoard page" in new WithApplication {
      val res = route(FakeRequest(GET, "/dashboard").withSession("email"->"john@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " Check for dashboard page by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, " /dashboard")).get
      status(res) must equalTo(UNAUTHORIZED)
    }

    " rendering of languages page" in new WithApplication {
      val res = route(FakeRequest(GET, "/getlanguages").withSession("email"->"john@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of languages page by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/getlanguages")).get
      status(res) must equalTo(UNAUTHORIZED)
    }

    " rendering of get Award page" in new WithApplication {
      val res = route(FakeRequest(GET, "/getawards").withSession("email"->"john@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of awards page by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/getawards")).get
      status(res) must equalTo(UNAUTHORIZED)
    }

    "add Assignment by admin" in new WithApplication {
      val res = route(FakeRequest(POST, "/addAssignment").withFormUrlEncodedBody("name"->"Angular assignment","date"->"2016-02-14","marks"->"6","remarks"->"OK").withSession("email"->"admin@gmail.com")).get
      status(res) must equalTo(SEE_OTHER)
    }

    "add language by user" in new WithApplication {
      val res = route(FakeRequest(POST, "/addLanguage").withFormUrlEncodedBody("lang"->"German","fluency"->"Bad").withSession("email"->"john@gmail.com")).get
      status(res) must equalTo(SEE_OTHER)    }

    "add award by user" in new WithApplication {
      val res = route(FakeRequest(POST, "/addAward").withFormUrlEncodedBody("id"->"3","name"->"Project Presentation","details"->"Project").withSession("email"->"john@gmail.com")).get
      status(res) must equalTo(SEE_OTHER)
    }

    "add programming language by user" in new WithApplication {
      val res = route(FakeRequest(POST, "/addProgLanguage").withFormUrlEncodedBody("langName"->"Python","fluency"->"Bad").withSession("email"->"john@gmail.com")).get
      status(res) must equalTo(SEE_OTHER)
    }


    " test for logout action" in new WithApplication {
      val res = route(FakeRequest(GET, "/logout").withSession("email"->"john@gmail.com")).get
      status(res) must equalTo(SEE_OTHER)
    }

    " rendering of get Assignment form render" in new WithApplication {
      val res = route(FakeRequest(GET, "/getassignments").withSession("email"->"john@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of assignments page by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/getlanguages")).get
      status(res) must equalTo(UNAUTHORIZED)
    }

    " rendering of get Programming language form render" in new WithApplication {
      val res = route(FakeRequest(GET, "/getproglanguages").withSession("email"->"john@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of programming languages page by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/getproglanguages")).get
      status(res) must equalTo(UNAUTHORIZED)
    }


    " rendering adminDashboard for Admin" in new WithApplication {
      val res = route(FakeRequest(GET, " /adminDashboard").withSession("email"->"admin@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of adminDashboard by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, " /adminDashboard")).get
      status(res) must equalTo(UNAUTHORIZED)
    }

    " rendering of get Assignment for ADMIN" in new WithApplication {
      val res = route(FakeRequest(GET, " /getAllAssignments ").withSession("email"->"admin@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get all Assignments form by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, " /getAllAssignments")).get
      status(res) must equalTo(UNAUTHORIZED)
    }


    " rendering of get Awards for admin" in new WithApplication {
      val res = route(FakeRequest(GET, " /getAllAwards ").withSession("email"->"admin@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get all Awards form by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, " /getAllAwards")).get
      status(res) must equalTo(UNAUTHORIZED)
    }


    " rendering of get Programming language for Admin" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllProgLanguages  ").withSession("email"->"admin@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get all Programming Languages page by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, " /getAllProgLanguages")).get
      status(res) must equalTo(UNAUTHORIZED)
    }
    " rendering of all interns for Admin" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllInterns").withSession("email"->"admin@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get all Interns form by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, " /getAllInterns")).get
      status(res) must equalTo(UNAUTHORIZED)
    }

    " rendering of get all languages for admin" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllLanguages  ").withSession("email"->"admin@gmail.com")).get
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get all languages form by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, " /getAllLanguages")).get
      status(res) must equalTo(UNAUTHORIZED)
    }
  }

}
