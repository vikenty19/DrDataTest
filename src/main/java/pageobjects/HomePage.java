package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver givenDriver ;
    public HomePage(WebDriver givenDriver){
        this.givenDriver = givenDriver;
        // support PageFactory
        PageFactory.initElements(givenDriver,this);

    }
    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccount;
    public void myAccountBtnClick(){
        myAccount.click();
    }@FindBy(linkText = "Login")
    private WebElement loginBtn;
    public void  goToLoginPage(){
        loginBtn.click();

    }
}
