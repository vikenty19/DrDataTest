import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DataUtil;
import utils.MyXLSReader;

import java.io.IOException;
import java.time.Duration;

public class LoginTest {
    WebDriver driver = null;

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test (dataProvider = "dataSupplier")
    public void testLogin(String email,String password) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type ='submit']")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
        driver.quit();
    }
    @DataProvider
    public Object[][] dataSupplier()  {
        //For try-catch make Object global
        Object[][]data = null;
        //create an Object for MyXLSReader

        try {
          MyXLSReader  excelReader =
                  new MyXLSReader(("user.dir")+"/src/test/resources/TutorialsNinja.xlsx");

        //Load data to the Reader method getTestData
         data =   DataUtil
              .getTestData(excelReader,"LoginTest","Data");//object,testName,Data from excel sheet
        } catch (Exception e) {
           e.printStackTrace();
        }
        return data;
    }
}
