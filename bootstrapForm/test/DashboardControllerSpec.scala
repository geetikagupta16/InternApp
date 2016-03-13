import org.specs2.mutable._
import play.api.test.Helpers._
import play.api.test.{WithApplication, _}

import scala.concurrent.Await
import scala.concurrent.duration._

class DashboardControllerSpec extends Specification {

  "DashBoard Controller " should {

    "CHECK FOR Rendering of DashBoard page" in new WithApplication {
      val res = route(FakeRequest(GET, "/dashboard").withSession("email"->"john@gmail.com")).get

      contentType(res) must beSome.which(_ == "text/html")
    }

    " Check for dashboard page by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/dashboard")).get

      status(res) must equalTo(UNAUTHORIZED)
    }

    " rendering of languages page" in new WithApplication {
      val res = route(FakeRequest(GET, "/getlanguages").withSession("email"->"john@gmail.com")).get
      val result = Await.result(res, 2 seconds)

      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of languages page by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/getlanguages")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(UNAUTHORIZED)
    }

    " rendering of get Award page" in new WithApplication {
      val res = route(FakeRequest(GET, "/getawards").withSession("email"->"john@gmail.com")).get
      val result = Await.result(res, 2 seconds)

      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of edit Award page" in new WithApplication {
      val res = route(FakeRequest(GET, "/editAward?id=1").withSession("email"->"john@gmail.com")).get
      val result = Await.result(res, 2 seconds)

      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of edit Award page by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/editAward?id=1")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(UNAUTHORIZED)
    }

    "update award by user" in new WithApplication {
      val res = route(FakeRequest(POST, "/updateAward").withFormUrlEncodedBody("id"->"1","name"->"IT QUIZ","details"->"Quiz").withSession("email"->"john@gmail.com")).get
      val result = Await.result(res, 2 seconds)
      status(res) must equalTo(SEE_OTHER)
    }


    "update award by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(POST, "/updateAward").withFormUrlEncodedBody("id"->"1","name"->"IT QUIZ","details"->"Quiz")).get
      val result = Await.result(res, 2 seconds)
      status(res) must equalTo(UNAUTHORIZED)
    }

    " rendering of awards page by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/getawards")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(UNAUTHORIZED)
    }

    "delete award by user" in new WithApplication {
      val res = route(FakeRequest(GET, "/deleteAward?id=2").withSession("email"->"john@gmail.com")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(SEE_OTHER)
    }

    "delete award by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/deleteAward?id=2")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(UNAUTHORIZED)
    }


    "add Assignment by admin" in new WithApplication {
      val res = route(FakeRequest(POST, "/addAssignment").withFormUrlEncodedBody("name"->"Angular assignment","date"->"2016-02-14","marks"->"6","remarks"->"OK","internId"->"1").withSession("email"->"admin@gmail.com")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(SEE_OTHER)
    }

    "add Assignment by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(POST, "/addAssignment").withFormUrlEncodedBody("name"->"Angular assignment","date"->"2016-02-14","marks"->"6","remarks"->"OK","internId"->"1")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(UNAUTHORIZED)
    }

    "add language by user" in new WithApplication {
      val res = route(FakeRequest(POST, "/addLanguage").withFormUrlEncodedBody("lang"->"German","fluency"->"Bad").withSession("email"->"john@gmail.com")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(SEE_OTHER)
    }

    " rendering of edit Language page" in new WithApplication {
      val res = route(FakeRequest(GET, "/editLanguage?id=1").withSession("email"->"john@gmail.com")).get
      val result = Await.result(res, 2 seconds)

      contentType(res) must beSome.which(_ == "text/html")
    }


    " rendering of edit Language page by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/editLanguage?id=1")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(UNAUTHORIZED)
    }

    "update language by user" in new WithApplication {
      val res = route(FakeRequest(POST, "/updateLanguage").withFormUrlEncodedBody("sno"->"1","lang"->"ABC","fluency"->"xyz").withSession("email"->"john@gmail.com")).get
      val result = Await.result(res, 2 seconds)
      status(res) must equalTo(SEE_OTHER)
    }

    "update language by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(POST, "/updateLanguage").withFormUrlEncodedBody("sno"->"1","lang"->"ABC","fluency"->"xyz")).get
      val result = Await.result(res, 2 seconds)
      status(res) must equalTo(UNAUTHORIZED)
    }
    "add language by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(POST, "/addLanguage").withFormUrlEncodedBody("lang"->"German","fluency"->"Bad")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(UNAUTHORIZED)
    }

    "delete language by user" in new WithApplication {
      val res = route(FakeRequest(GET, "/deleteLanguage?sno=2").withSession("email"->"john@gmail.com")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(SEE_OTHER)
    }

    "delete language by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/deleteLanguage?sno=2")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(UNAUTHORIZED)
    }

    "add award by user" in new WithApplication {
      val res = route(FakeRequest(POST, "/addAward").withFormUrlEncodedBody("id"->"3","name"->"Project Presentation","details"->"Project").withSession("email"->"john@gmail.com")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(SEE_OTHER)
    }

    "add award by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(POST, "/addAward").withFormUrlEncodedBody("id"->"3","name"->"Project Presentation","details"->"Project")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(UNAUTHORIZED)
    }

    "add programming language by user" in new WithApplication {
      val res = route(FakeRequest(POST, "/addProgLanguage").withFormUrlEncodedBody("langName"->"Python","fluency"->"Bad").withSession("email"->"john@gmail.com")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(SEE_OTHER)
    }

    "add programming language by  unauthorized user" in new WithApplication {
      val res = route(FakeRequest(POST, "/addProgLanguage").withFormUrlEncodedBody("langName"->"Python","fluency"->"Bad")).get
      val result = Await.result(res, 2 seconds)

      status(res) must equalTo(UNAUTHORIZED)
    }

    "delete Programming language by user" in new WithApplication {
      val res = route(FakeRequest(GET, "/deleteProgLanguage?sno=2").withSession("email"->"john@gmail.com")).get
      val result = Await.result(res, 2 seconds)
      status(res) must equalTo(SEE_OTHER)
    }

    "delete programming language by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/deleteProgLanguage?sno=2")).get
      val result = Await.result(res, 2 seconds)
      status(res) must equalTo(UNAUTHORIZED)
    }


    " test for logout action" in new WithApplication {
      val res = route(FakeRequest(GET, "/logout").withSession("email"->"john@gmail.com")).get
      redirectLocation(res) must beSome("/login")
    }

    " rendering of get Assignment form render" in new WithApplication {
      val res = route(FakeRequest(GET, "/getassignments").withSession("email"->"john@gmail.com")).get
      val result = Await.result(res, 2 seconds)
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of assignments page by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/getassignments")).get
      val result = Await.result(res, 2 seconds)
      status(res) must equalTo(UNAUTHORIZED)
    }

    " rendering of get Programming language form render" in new WithApplication {
      val res = route(FakeRequest(GET, "/getproglanguages").withSession("email"->"john@gmail.com")).get
      val result = Await.result(res, 2 seconds)
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of programming languages page by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/getproglanguages")).get
      val result = Await.result(res, 2 seconds)
      status(res) must equalTo(UNAUTHORIZED)
    }


    " rendering adminDashboard for Admin" in new WithApplication {
      val res = route(FakeRequest(GET, "/adminDashboard").withSession("email"->"admin@gmail.com")).get
      val result = Await.result(res, 2 seconds)
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of adminDashboard by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/adminDashboard")).get
      status(res) must equalTo(UNAUTHORIZED)
    }

    " rendering of get Assignment for ADMIN" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllAssignments ").withSession("email"->"admin@gmail.com")).get
      val result = Await.result(res, 2 seconds)
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get all Assignments form by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllAssignments")).get
      val result = Await.result(res, 2 seconds)
      status(res) must equalTo(UNAUTHORIZED)
    }


    " rendering of get Awards for admin" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllAwards ").withSession("email"->"admin@gmail.com")).get
      val result = Await.result(res, 2 seconds)
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get all Awards form by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllAwards")).get
      val result = Await.result(res, 2 seconds)
      status(res) must equalTo(UNAUTHORIZED)
    }


    " rendering of get Programming language for Admin" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllProgLanguages  ").withSession("email"->"admin@gmail.com")).get
      val result = Await.result(res, 2 seconds)
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get all Programming Languages page by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllProgLanguages")).get
      val result = Await.result(res, 2 seconds)
      status(res) must equalTo(UNAUTHORIZED)
    }
    " rendering of all interns for Admin" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllInterns").withSession("email"->"admin@gmail.com")).get
      val result = Await.result(res, 2 seconds)
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get all Interns form by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllInterns")).get
      val result = Await.result(res, 2 seconds)
      status(res) must equalTo(UNAUTHORIZED)
    }

    " rendering of get all languages for admin" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllLanguages  ").withSession("email"->"admin@gmail.com")).get
      val result = Await.result(res, 2 seconds)
      contentType(res) must beSome.which(_ == "text/html")
    }

    " rendering of get all languages form by unauthorized user" in new WithApplication {
      val res = route(FakeRequest(GET, "/getAllLanguages")).get
      val result = Await.result(res, 2 seconds)
      status(res) must equalTo(UNAUTHORIZED)
    }
  }

}
