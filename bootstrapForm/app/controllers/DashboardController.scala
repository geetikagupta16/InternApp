package controllers

import com.google.inject.Inject
import models.{Language, Login, Intern}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import repo.{AwardRepo, ProgLanguageRepo, LanguageRepo, InternRepo}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * Created by knoldus on 8/3/16.
  */
class DashboardController  @Inject()(internRepo:InternRepo)(langRepo:LanguageRepo)(progLangRepo:ProgLanguageRepo)(awardRepo:AwardRepo) extends Controller {

  val addAwardForm = Form(
    tuple(
      "id" -> number,
      "name" -> nonEmptyText,
      "details" -> nonEmptyText
    )
  )

  val addLangForm = Form(
    tuple(
      "lang" -> nonEmptyText,
      "fluency" -> nonEmptyText
    )
  )

  val addProgLangForm = Form(
    tuple(
      "langName" -> nonEmptyText,
      "fluency" -> nonEmptyText
    )
  )


  def getDashboard() = Action { implicit request =>
    Ok(views.html.dashboard(addAwardForm, addLangForm, addProgLangForm))
  }

  def getAdminDashboard()=Action{
    implicit request =>
      Ok(views.html.adminDashboard(addAwardForm, addLangForm, addProgLangForm))

  }

  def logout=Action{implicit request=>

    Redirect(routes.LoginController.getForm).withNewSession
  }

  def languageDetails() = Action.async({ implicit request =>

    val futurelang: Future[List[Language]] = langRepo.getAll()

    val res = futurelang.map(x => Ok(views.html.dashboard(addAwardForm, addLangForm, addProgLangForm)))
    res
  })

  def addLanguage() = Action.async({ implicit request =>
    addLangForm.bindFromRequest.fold(
      formError => Future {
        BadRequest("ERROR")
      },
      langData => {
        request.session.get("email").map { user => {
          val res = langRepo.insert(1, langData._1, langData._2, user)
          res.map(x => Redirect(routes.DashboardController.getDashboard))
        }
        }.getOrElse {
          Future {
            Unauthorized("Please sign in to see this page...!!!!")
          }
        }

      })
  })

  //def addAward()=Action{Ok("In award")}

def addAward() = Action.async({ implicit request =>
    addAwardForm.bindFromRequest.fold(
      formError => Future {
        BadRequest("ERROR")
      },
      awardData => {
        request.session.get("email").map { user => {
          val res = awardRepo.insert(awardData._1, awardData._2, awardData._3, user)
          res.map(x => Redirect(routes.DashboardController.getDashboard))
        }
        }.getOrElse {
          Future {
            Unauthorized("Please sign in to see this page...!!!!")
          }
        }

      })
  })



  def addProgLanguage() = Action { implicit request =>
    Ok("In Prog Lang")

  }




}
