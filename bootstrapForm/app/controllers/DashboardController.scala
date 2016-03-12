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
class DashboardController @Inject()(assignmentRepo: AssignmentRepo)(internRepo: InternRepo)(langRepo: LanguageRepo)(progLangRepo: ProgLanguageRepo)(awardRepo: AwardRepo) extends Controller {

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

  val addAssignmentForm = Form(
    tuple(
      "name" -> nonEmptyText,
      "date" -> nonEmptyText,
      "marks" -> number,
      "remarks" -> nonEmptyText,
      "internId"->number
    )
  )

  def addAssignment()=Action.async({ implicit request =>
    addAssignmentForm.bindFromRequest.fold(
      formError => Future {
        BadRequest("ERROR")
      },
      assignmentData => {
        request.session.get("email").map { user => {
          val res = assignmentRepo.insert(1, assignmentData._1, assignmentData._2,assignmentData._3,assignmentData._4,assignmentData._5)
          res.map(x => Redirect(routes.DashboardController.getDashboard))
        }
        }.getOrElse {
          Future {
            Unauthorized("sign in to see this page...!!!!")
          }
        }

      })
  })


  def getDashboard() = Action.async( { implicit request =>
if(!(request.session.get("email").isEmpty))
  Future{
    Ok(views.html.dashboard())

  }
    else
  Future{
    Unauthorized("sign in to see this page...!!!!")
  }
  })

  def getAdminDashboard() = Action.async( {
    implicit request =>
      if(!(request.session.get("email").isEmpty))
        Future{
          Ok(views.html.adminDashboard())

        }
      else
        Future{
          Unauthorized("sign in to see this page...!!!!")
        }

  })

  def logout() = Action { implicit request =>
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
            Unauthorized("sign in to see this page...!!!!")
          }
        }

      })
  })

  def deleteLanguage(sno:Int)=Action.async({ implicit request=>
    request.session.get("email").map { user => {
      val res = langRepo.delete(sno, user)
      res.map(x => Redirect(routes.DashboardController.getDashboard))
    }
    }.getOrElse {
      Future {
        Unauthorized("sign in to see this page...!!!!")
      }
    }
  })
/*
  def editAward(awardId: Int) = Action.async({ implicit request =>
    request.session.get("email").map { user => {
      val awardList = awardRepo.getAll(user)
      val res = awardList.map(x => x.filter(_.id == awardId))
      res.map(award => {
        addAwardForm.fill((award.head.id, award.head.name, award.head.details))
        Ok(views.html.awardModal(addAwardForm))

      })
    }
    }.getOrElse {
      Future {
        Unauthorized("Please sign in to see this page...!!!!")
      }
    }
  })
*/

  def deleteAward(id:Int)=Action.async({ implicit request=>
    request.session.get("email").map { user => {
      val res = awardRepo.delete(id, user)
      res.map(x => Redirect(routes.DashboardController.getDashboard))
    }
    }.getOrElse {
      Future {
        Unauthorized("Please sign in to see this page...!!!!")
      }
    }



  })

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

  def deleteProgLanguage(sno:Int)=Action.async({implicit request=>

    request.session.get("email").map { user => {
      val res = progLangRepo.delete(sno, user)
      res.map(x => Redirect(routes.DashboardController.getDashboard))
    }
    }.getOrElse {
      Future {
        Unauthorized("Please sign in to see this page...!!!!")
      }
    }
  })

  def getAwards() = Action.async({ implicit request =>
    request.session.get("email").map { user => {
      val res = awardRepo.getAll(user)
      res.map(x => Ok(views.html.award(x,addAwardForm)))
    }
    }.getOrElse {
      Future {
        Unauthorized("Please sign in to see this page...!!!!")
      }

    }
  })

  def getAllAwards()=Action.async({implicit request=>
    request.session.get("email").map { user => {
      val listaward=awardRepo.getAll()
      listaward.map(x=>Ok(views.html.award(x,addAwardForm)))
    }
    }.getOrElse {
      Future {
        Unauthorized("Please sign in to see this page...!!!!")
      }

    }


  })

  def getAllLanguages()=Action.async({implicit request=>
    request.session.get("email").map { user => {
      val listlanguage=langRepo.getAll()
      listlanguage.map(x=>Ok(views.html.language(x,addLangForm)))
    }
    }.getOrElse {
      Future {
        Unauthorized("Please sign in to see this page...!!!!")
      }

    }



  })

  def getAllProgLanguages()=Action.async({implicit request=>
    request.session.get("email").map { user => {
      val listprog=progLangRepo.getAll()
      listprog.map(x=>Ok(views.html.programmingLanguage(x,addProgLangForm)))
    }
    }.getOrElse {
      Future {
        Unauthorized("Please sign in to see this page...!!!!")
      }

    }




  })

  def getAllAssignments()=Action.async({implicit request=>
    request.session.get("email").map { user => {
      val listAssignment=assignmentRepo.getAll()
      listAssignment.map(x=>Ok(views.html.adminAssignment(x,addAssignmentForm)))
    }
    }.getOrElse {
      Future {
        Unauthorized("Please sign in to see this page...!!!!")
      }

    }



  })

  def getAllInterns()=Action.async({implicit request=>
    request.session.get("email").map { user => {
      val listIntern=internRepo.getAll()
      listIntern.map(x=>Ok(views.html.interns(x)))
    }
    }.getOrElse {
      Future {
        Unauthorized("Please sign in to see this page...!!!!")
      }

    }




  })


  def getLanguages() = Action.async({ implicit request =>
    request.session.get("email").map { user => {
      val res = langRepo.getAll(user)
      res.map(x => Ok(views.html.language(x, addLangForm)))
    }
    }.getOrElse {
      Future {
        Unauthorized("Please sign in to see this page...!!!!")
      }

    }


  })


  def getAssignments() = Action.async({ implicit request =>
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

  def getProgLanguages() = Action.async({ implicit request =>
    request.session.get("email").map { user => {
      val res = progLangRepo.getAll(user)
      res.map(x => Ok(views.html.programmingLanguage(x, addProgLangForm)))
    }
    }.getOrElse {
      Future {
        Unauthorized("Please sign in to see this page...!!!!")
      }

    }


  })

}

