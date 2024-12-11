import Base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DataUtil;
import utils.MyXLSReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

public class LoginTest extends BaseTest {

    MyXLSReader  excelReader;


    @Test (dataProvider = "dataSupplier")
    public void testLogin(HashMap<String,String>hMap) {
        // check if file excelReader exist or define as N - not executed
        if (!DataUtil.isRunnable(excelReader,"LoginTest","TestCases")|| hMap.get("Runmode").equals("N")){
            throw new SkipException("Run mode is set to N, hence not executed");
        }
        driver = openBrowserAndUrl(hMap.get("Browser"));


        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys(hMap.get("Username"));
        driver.findElement(By.id("input-password")).sendKeys(hMap.get("Password"));
        driver.findElement(By.xpath("//input[@type ='submit']")).click();
        String expectedResult = hMap.get("ExpectedResult");
        boolean expectedConvertedResult = false;
        boolean actualResult = false;
        if(expectedResult.equalsIgnoreCase("Success")){
            expectedConvertedResult = true;
        }else if(expectedResult.equalsIgnoreCase("Failure")){
            expectedConvertedResult = false;
        }
        // check if actual result true or false
        try {
             actualResult = driver.findElement(By.linkText("Edit your account information")).isDisplayed();
        }catch (Throwable e){
            actualResult = false;
        }
        Assert.assertEquals(actualResult,expectedConvertedResult);

    }
    // Checking proper reading from property file
    @Test
    public void checkFileReading(){
        prop = new Properties();
        File propFile=new File(System.getProperty("user.dir")+"/src/test/resources/data.properties");

        try {
            FileInputStream fis = new FileInputStream(propFile);
            prop.load(fis);
            System.out.println(prop);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(prop.getProperty("url"));

    }
    @DataProvider
    public Object[][] dataSupplier()  {
        //For try-catch make Object global
        Object[][]data = null;
        //create an Object for MyXLSReader
// String excelFilePath = System.getProperty("user.dir") + "/Files/employee.xlsx";
        try {
            String excelPath = System.getProperty("user.dir") + "\\src\\test\\resources\\TutorialsNinja.xlsx";
            System.out.println(excelPath);
         excelReader =
                  new MyXLSReader(excelPath);

        //Load data to the Reader method getTestData
         data =   DataUtil
              .getTestData(excelReader,"LoginTest","Data");//object,testName,Data from excel sheet
        } catch (Exception e) {
           e.printStackTrace();
        }
        return data;
    }
}
