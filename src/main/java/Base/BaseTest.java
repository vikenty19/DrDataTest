package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
   protected WebDriver driver= null;
 //  protected String url = "http://tutorialsninja.com/demo/";
    public Properties prop;
//    @BeforeMethod
    public WebDriver openBrowserAndUrl(String browserName){
         prop = new Properties();
        File propFile=new File(System.getProperty("user.dir")+"/src/test/resources/data.properties");

        try {
            FileInputStream fis = new FileInputStream(propFile);
            prop.load(fis);
            System.out.println(prop);
        } catch (Throwable e) {
            e.printStackTrace();
        }

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
        // take it from data.properties
        driver.get(prop.getProperty("url"));
        return driver;

    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
