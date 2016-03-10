package repo

import scala.concurrent.{Await, Future}
import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication
import scala.concurrent.duration.Duration

/**
  * Created by knoldus on 10/3/16.
  */

class LanguageRepoSpec extends Specification{

  def langRepo(implicit app: Application) = Application.instanceCache[LanguageRepo].apply(app)

  "Language form add" in new WithApplication {
    val result = await(langRepo.insert(1,"English","Good","john@gmail.com"))
    result === 1
  }

  def await[T](v: Future[T]): T = Await.result(v, Duration.Inf)

}
