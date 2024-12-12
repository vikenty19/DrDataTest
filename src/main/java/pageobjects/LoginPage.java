package pageobjects;

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
    @FindBy(id = "input-password")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@type ='submit']")
    private WebElement submitBtn;

    public void enterEmail(String email){
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
    }
    public void enterPassword(String password){
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void clickLoginToAccountPage(){
        submitBtn.click();
    }
}