import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.HomePage;

public class LoginPage extends HomePage {

    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(id = "input-email")
    private WebElement emailField;

    public void enterEmail(String email){
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
    }
}