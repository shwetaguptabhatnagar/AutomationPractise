/*
 * @author Naveen Khunteta
 * 
 */

package com.crm.qa.testcases;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FreshWorksTest
{
WebDriver driver;

@BeforeMethod
@Parameters("browser")
public void setup(String browser)
{

if(browser.equalsIgnoreCase("chrome"))
{//WebDriverManager.chromedriver().setup(); 
	//driver= new ChromeDriver();
DesiredCapabilities cap=new DesiredCapabilities();
cap.setCapability("browserName","chrome");
try{
driver=new RemoteWebDriver(new URL("http://13.235.254.142:4444/wd/hub"),cap);
}catch(Exception e)
{
e.printStackTrace();
}
}

else if(browser.equalsIgnoreCase("firefox"))
{//WebDriverManager.firefoxdriver().setup();
//driver= new FirefoxDriver();

DesiredCapabilities cap=new DesiredCapabilities();
cap.setCapability("browserName","firefox");
try{
driver=new RemoteWebDriver(new URL("http://13.235.254.142:4444/wd/hub"),cap);
}catch(Exception e)
{
e.printStackTrace();
}
}

driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
driver.get("https://www.freshwoks.com/");
}

@Test
public void freshWorksTitleTest()
{System.out.println(driver.getTitle());}

@Test
public void getFooterLinksTest()
{
List<WebElement> footerlinksLList=driver.findElements(By.cssSelector("ul.footer-nav li a"));
footerlinksLList.forEach(ele->System.out.println(ele.getText()));
//assertEquals(footerlinksLList.size(),35);
}

@AfterMethod
public void tearDown()
{driver.quit();}

}
