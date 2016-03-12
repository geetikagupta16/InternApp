package repo

import models.Assignment
import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
  * Created by knoldus on 10/3/16.
  */
class AssignmentRepoSpec extends Specification{
  def assignmentRepo(implicit app: Application) = Application.instanceCache[AssignmentRepo].apply(app)

"check for list of assignments" in new WithApplication {
    val result = await(assignmentRepo.getAll("john@gmail.com"))
    result == List(Assignment(1,"Play assignment","2016-03-03",6,"average",1),Assignment(2,"Jquery assignment","2016-01-02",7,"good",1))
  }

  "Assignment form add" in new WithApplication {
    val result = await(assignmentRepo.insert(1,"Scala Assignment","2016-02-06",7,"Good",1))
    result === 1
  }


  "check for list of all assignments" in new WithApplication {
    val result = await(assignmentRepo.getAll())
    result == List(Assignment(1,"Play assignment","2016-03-03",6,"average",1),Assignment(2,"Jquery assignment","2016-01-02",7,"good",1))
  }




  def await[T](v: Future[T]): T = Await.result(v, Duration.Inf)
}
