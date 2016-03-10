package repo


import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
  * Created by knoldus on 10/3/16.
  */

class ProgLanguageRepoSpec extends Specification{

  def proglangRepo(implicit app: Application) = Application.instanceCache[ProgLanguageRepo].apply(app)

  " Progamming Language form add" in new WithApplication {
    val result = await(proglangRepo.insert(1,"JAVA","Good","john@gmail.com"))
    result === 1
  }
  def await[T](v: Future[T]): T = Await.result(v, Duration.Inf)



}
