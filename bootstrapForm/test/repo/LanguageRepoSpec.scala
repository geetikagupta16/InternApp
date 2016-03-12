package repo

import models.Language

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

  "check for list of languages" in new WithApplication {
    val result = await(langRepo.getAll("john@gmail.com"))
    result == List(Language(1,"french","bad",1))
  }

  "check for list of all languages" in new WithApplication {
    val result = await(langRepo.getAll())
    result == List(Language(1,"french","bad",1),Language(2,"Spanish","good",1))
  }

  "Language delete" in new WithApplication {
    val result = await(langRepo.delete(2,"john@gmail.com"))
    result === 1
  }

  def await[T](v: Future[T]): T = Await.result(v, Duration.Inf)

}
