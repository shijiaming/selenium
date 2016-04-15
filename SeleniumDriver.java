package cn.tjuscs.selenium;

import java.awt.List;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.csvreader.CsvReader;

public class SeleniumDriver {
  private String email = null; 
  private String password = null; 
  private String num = null; 
  //ArrayList<String> list = new ArrayList<String>();
 // Object [][]o = new Object[100][];
  private static  WebDriver driver;
  private static  String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
 public void SeleniumDriver(String num,String password,String email){
	 this.num = num;
	 this.password = password;
	 this.email = email;
 }
  @BeforeClass
  public static  void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.ncfxy.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 //    CsvReader r = new CsvReader("C://Users//SJM//Desktop//info.csv", ',',Charset.forName("GBK"));
    //读取表头
  //    r.readHeaders();
      //逐条读取记录，直至读完
  //    Object[][] ob = new Object[100][100];
 //    while (r.readRecord()) {
  // 	  String num1 = r.get("number");
 //  	  String email1 = r.get("email");
 //  	  String pass = num.substring(4); 
   	//  ob.add(num1,pass,email1);
   	 
     }
 // }
  

  @Test
  public void testSeleniumDriver() throws Exception {
	  
      CsvReader r = new CsvReader("C://Users//SJM//Desktop//info.csv", ',',Charset.forName("GBK"));
    // 读取表头
       r.readHeaders();
       //逐条读取记录，直至读完
      while (r.readRecord()) {
    	  driver.get(baseUrl );
    	  String num = r.get("number");
    	  String email = r.get("email");
    	  String pass = num.substring(4); 
 
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys(num);
    driver.findElement(By.id("pwd")).clear();
    driver.findElement(By.id("pwd")).sendKeys(pass);
    driver.findElement(By.id("submit")).click();
   
    	  WebElement m =  driver.findElement(By.xpath(".//*[@id='table-main']/tr[1]/td[2]"));
    	  assertEquals(email, m.getText());
    	//  System.out.println(m);
    	 
    
      }
  };

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

