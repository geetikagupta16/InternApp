package controllers

import com.google.inject.Inject
import models.{Award, Language, Login, Intern}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import repo._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * Created by knoldus on 8/3/16.
  */
class DashboardController @Inject()(assignmentRepo:AssignmentRepo)(internRepo: InternRepo)(langRepo: LanguageRepo)(progLangRepo: ProgLanguageRepo)(awardRepo: AwardRepo) extends Controller {

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


  def getDashboard() = Action{ implicit request =>

    Ok(views.html.dashboard())

  }

  def getAdminDashboard() = Action {
    implicit request =>
      Ok(views.html.adminDashboard(addAwardForm, addLangForm, addProgLangForm))

  }

  def logout = Action { implicit request =>

    Redirect(routes.LoginController.getForm).withNewSession
  }

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


  def addProgLanguage() = Action.async({ implicit request =>

    addProgLangForm.bindFromRequest.fold(
      formError => Future {
        BadRequest("ERROR")
      },
      progData => {
        request.session.get("email").map { user => {
          val res = progLangRepo.insert(1, progData._1, progData._2, user)
          res.map(x => Redirect(routes.DashboardController.getDashboard))
        }
        }.getOrElse {
          Future {
            Unauthorized("Please sign in to see this page...!!!!")
          }
        }

      })
  })

  def getAwards()=Action.async({ implicit request =>
    request.session.get("email").map { user => {
      val res = awardRepo.getAll(user)
      res.map(x => Ok(views.html.award(x, addAwardForm)))
    }
    }.getOrElse {
      Future {
        Unauthorized("Please sign in to see this page...!!!!")
      }

    }
  })

    def getLanguages()=Action.async({implicit request=>
      request.session.get("email").map { user => {
        val res = langRepo.getAll(user)
        res.map(x => Ok(views.html.language(x,addLangForm)))
      }
      }.getOrElse {
        Future {
          Unauthorized("Please sign in to see this page...!!!!")
        }

      }


})


  def getAssignments()=Action.async({ implicit request =>
    request.session.get("email").map { user => {
      val res = assignmentRepo.getAll(user)
      res.map(x => Ok(views.html.assignment(x)))
    }
    }.getOrElse {
      Future {
        Unauthorized("Please sign in to see this page...!!!!")
      }

    }

  }
  )

  def getProgLanguages()=Action.async({implicit request=>
    request.session.get("email").map { user => {
      val res = progLangRepo.getAll(user)
      res.map(x => Ok(views.html.programmingLanguage(x,addProgLangForm)))
    }
    }.getOrElse {
      Future {
        Unauthorized("Please sign in to see this page...!!!!")
      }

    }


  })

}

