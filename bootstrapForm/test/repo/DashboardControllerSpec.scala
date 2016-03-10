package repo

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.Application
import play.api.test.WithApplication
import play.api.test._
import play.api.test.Helpers._

import scala.concurrent.Await
import org.scalatest  .time._
import scala.concurrent.duration._

class DashboardControllerSpec extends Specification {

  "DashBoard Controller " should {


    "CHECK FOR Rendering of DashBoard page" in new WithApplication {
      val res = route(FakeRequest(GET, "/getForm")).get
      contentType(res) must beSome.which(_ == "text/html")
    }
  }

  "DashBoard Controller " should {


    "CHECK FOR " in new WithApplication {
      val res = route(FakeRequest(GET, "/getForm")).get
      contentType(res) must beSome.which(_ == "text/html")
    }
  }

}
