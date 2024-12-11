package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver givenDriver = null;
    public HomePage(WebDriver givenDriver){
        this.givenDriver = givenDriver;
        // support PageFactory
        PageFactory.initElements(givenDriver,this);

    }
}
