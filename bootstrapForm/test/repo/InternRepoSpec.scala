package repo

import models.Login
import org.specs2.mutable.Specification
import play.api.Application
import play.api.test.WithApplication
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import scala.concurrent.Future

/**
  * Created by knoldus on 9/3/16.
  */
class InternRepoSpec extends Specification {


  def internRepo(implicit app: Application) = Application.instanceCache[InternRepo].apply(app)

  "Login" in new WithApplication {
    val result = await(internRepo.loginAuth(Login("admin@gmail.com","admin")))
    result.length === 1
    result.head.name === "admin"
  }

  def await[T](v: Future[T]): T = Await.result(v, Duration.Inf)



}