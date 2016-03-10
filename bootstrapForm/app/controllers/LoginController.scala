package controllers

import com.google.inject.Inject
import models.{Intern, Login}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import repo.InternRepo
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import scala.concurrent.ExecutionContext.Implicits.global


import scala.concurrent.Future

/**
  * Created by knoldus on 9/3/16.
  */
class LoginController @Inject() (internRepo:InternRepo)extends Controller{

  val loginForm = Form(
    mapping(
      "email" -> email,
      "password" -> nonEmptyText(minLength = 5)
    )(Login.apply)(Login.unapply)
  )

  def getForm()=Action{ implicit request=>
    Ok(views.html.login(loginForm))
  }

  def auth() = Action.async({implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => {
        Future {
          BadRequest(views.html.login(formWithErrors))
        }
      },
      userData => {
        val res: Future[List[Intern]] = internRepo.loginAuth(userData)
        res.map(x =>if(x.length==1 && x.head.password=="admin" && x.head.email=="admin@gmail.com") Redirect(routes.DashboardController.getAdminDashboard).withSession("email"->x.head.email)

        else if(x.length==1)
          Redirect(routes.DashboardController.getDashboard).withSession("email"->x.head.email)
        else
          Redirect(routes.LoginController.getForm).flashing("error"->"Log in to continue")


        )})


  })

}
