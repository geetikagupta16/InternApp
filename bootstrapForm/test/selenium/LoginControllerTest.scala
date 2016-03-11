package selenium


import java.util.concurrent.TimeUnit

import org.openqa.selenium.firefox.FirefoxDriver
import org.scalatest.FlatSpec

/**
  * Created by knoldus on 11/3/16.
  */

class LoginControllerTest extends FlatSpec {

  val port=9000
  val baseUrl="localhost:"+port+"/getForm"

  "intern" should "successfully hit the url" in {

    val driver=new FirefoxDriver()
    driver.get(baseUrl)
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS)
    driver.findElementById("password").sendKeys("abcdef")
    driver.findElementById("email").sendKeys("john@gmail.com")
    driver.findElementByClassName("btn").click()
    driver.findElementById("award").click()
    driver.findElementById("language").click()
    driver.findElementById("assignments").click()
    driver.
  }


}
