package repo

import models.Award
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Application
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

import scala.concurrent.{Future, Await}
import scala.concurrent.duration._

/**
  * Created by knoldus on 10/3/16.
  */
@RunWith(classOf[JUnitRunner])
class AwardRepoSpec extends Specification{

  def awardRepo(implicit app: Application) = Application.instanceCache[AwardRepo].apply(app)

  "Award form add" in new WithApplication {
    val result = await(awardRepo.insert(1,"Codage","Programming contest","john@gmail.com"))
    result === 1
  }

  "check for list of awards" in new WithApplication {
    val result = await(awardRepo.getAll("john@gmail.com"))
    result == List(Award(1,"IT Quiz","Quiz",1))
  }

  "check for list of all awards" in new WithApplication {
    val result = await(awardRepo.getAll())
    result == List(Award(1,"IT Quiz","Quiz",1),Award(2,"IT tambola","tambola",1))
  }

  "Award delete" in new WithApplication {
    val result = await(awardRepo.delete(2,"john@gmail.com"))
    result === 1
  }



  def await[T](v: Future[T]): T = Await.result(v, Duration.Inf)

}

