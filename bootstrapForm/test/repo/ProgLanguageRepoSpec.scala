package repo


import models.ProgLanguage
import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
  * Created by knoldus on 10/3/16.
  */

class ProgLanguageRepoSpec extends Specification{

  def progLangRepo(implicit app: Application) = Application.instanceCache[ProgLanguageRepo].apply(app)

  " Progamming Language form add" in new WithApplication {
    val result = await(progLangRepo.insert(1,"JAVA","Good","john@gmail.com"))
    result === 1
  }

  "check for list of programming languages" in new WithApplication {
    val result = await(progLangRepo.getAll("john@gmail.com"))
    result == List(ProgLanguage(1,"java","bad",1))
  }

  "check for list of all programming languages" in new WithApplication {
    val result = await(progLangRepo.getAll())
    result == List(ProgLanguage(1,"java","bad",1))
  }

  " Progamming Language delete" in new WithApplication {
    val result = await(progLangRepo.delete(2,"john@gmail.com"))
    result === 1
  }


  def await[T](v: Future[T]): T = Await.result(v, Duration.Inf)



}
