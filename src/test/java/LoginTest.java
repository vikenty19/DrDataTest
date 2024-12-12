import Base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.AccountPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utils.DataUtil;
import utils.MyXLSReader;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public class LoginTest extends BaseTest {

    MyXLSReader excelReader;


    @Test(dataProvider = "dataSupplier")
    public void testLogin(HashMap<String, String> hMap) {
        // check if file excelReader exist or define as N - not executed
        if (!DataUtil.isRunnable(excelReader, "LoginTest", "TestCases") || hMap.get("Runmode").equals("N")) {
            throw new SkipException("Run mode is set to N, hence not executed");
        }
        driver = openBrowserAndUrl(hMap.get("Browser"));
        HomePage homePage = new HomePage(driver);
        homePage.myAccountBtnClick();
        homePage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(hMap.get("Username"));
        loginPage.enterPassword(hMap.get("Password"));
        loginPage.clickLoginToAccountPage();
        //Assertions that depend on Data page in excel sheet

        String expectedResult = hMap.get("ExpectedResult");
        boolean expectedConvertedResult = false;
        boolean actualResult = false;
        if (expectedResult.equalsIgnoreCase("Success")) {
            expectedConvertedResult = true;
        } else if (expectedResult.equalsIgnoreCase("Failure")) {
            expectedConvertedResult = false;
        }
        // check if actual result true or false
        AccountPage accountPage = new AccountPage(driver);
        actualResult = accountPage.isConfirmTextDisplayed();

        Assert.assertEquals(actualResult, expectedConvertedResult);

    }

    // Checking proper reading from property file
    @Test
    public void checkFileReading() {
        prop = new Properties();
        File propFile = new File(System.getProperty("user.dir") + "/src/test/resources/data.properties");

        try {
            FileInputStream fis = new FileInputStream(propFile);
            prop.load(fis);
            System.out.println(prop);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        driver = openBrowserAndUrl(prop.getProperty("browserName"));

        HomePage homePage = new HomePage(driver);
        homePage.myAccountBtnClick();
        homePage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(prop.getProperty("email"));
        loginPage.enterPassword(prop.getProperty("password"));
        loginPage.clickLoginToAccountPage();

        boolean actualResult = false;
              // check if actual result true or false
        AccountPage accountPage = new AccountPage(driver);
        actualResult = accountPage.isConfirmTextDisplayed();

        Assert.assertEquals(actualResult, true);

    }

    @DataProvider
    public Object[][] dataSupplier() {
        //For try-catch make Object global
        Object[][] data = null;
        //create an Object for MyXLSReader
// String excelFilePath = System.getProperty("user.dir") + "/Files/employee.xlsx";
        try {
            String excelPath = System.getProperty("user.dir") + "\\src\\test\\resources\\TutorialsNinja.xlsx";
            System.out.println(excelPath);
            excelReader =
                    new MyXLSReader(excelPath);

            //Load data to the Reader method getTestData
            data = DataUtil
                    .getTestData(excelReader, "LoginTest", "Data");//object,testName,Data from excel sheet
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
