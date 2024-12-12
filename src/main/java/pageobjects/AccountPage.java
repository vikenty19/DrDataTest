package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends HomePage {
    public AccountPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(linkText = "Edit your account information")
    private WebElement confirmText;

    public Boolean isConfirmTextDisplayed() {
        boolean displayStatus= false;

        try {
            displayStatus =  confirmText.isDisplayed();

        } catch (Throwable e) {
           displayStatus =false;
        }
       return displayStatus;
    }
}