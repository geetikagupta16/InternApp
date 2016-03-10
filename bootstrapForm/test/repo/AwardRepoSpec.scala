package repo

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

  def await[T](v: Future[T]): T = Await.result(v, Duration.Inf)

}

