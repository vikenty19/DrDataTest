package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
   protected WebDriver driver= null;
   protected String url = "http://tutorialsninja.com/demo/";
    @BeforeMethod
    public WebDriver openBrowser(String browserName){
        switch (browserName){
            case "firefox":
               WebDriverManager.firefoxdriver().setup();
                return driver =new FirefoxDriver();
            case "edge":
              WebDriverManager.edgedriver().setup();
                return driver = new EdgeDriver();
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;

    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
