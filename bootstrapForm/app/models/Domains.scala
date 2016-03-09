package models

/**
  * Created by knoldus on 6/3/16.
  */


case class Intern(id:Int,name:String,email: String,password: String)

case class Award(id:Int,name:String,details:String,internId:Int)

case class Language(sno:Int,known:String,fluency:String,internId:Int)

case class Assignment(sno:Int,name:String,date:String,marks:Int,remarks:String,internId:Int)

case class Login(email:String,password:String)

case class ProgLanguage(sno:Int,known:String,fluency:String,internId:Int)



