package pages;

import dto.UserLombok;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static java.sql.DriverManager.getDriver;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(css = "input[name='email']")
    WebElement inputEmail;

    @FindBy(css = "input[name='password']")
    WebElement inputPassword;

    @FindBy(xpath = "//button[@name='registration']")
    public WebElement btnRegistrationForm;

    @FindBy(xpath = "//button[text()='Sign Out']")
    WebElement bntSignOut;

  //  @FindBy(xpath = "//div[contains(text(), 'Registration failed')]")
 //   WebElement errorMessage;



    public void typeRegistrationForm(UserLombok userLombok){
        inputEmail.sendKeys(userLombok.getUsername());
        inputPassword.sendKeys(userLombok.getPassword());
        btnRegistrationForm.click();
    }

    /*public boolean isLogoutButtonPresent(){
        return elementIsDisplayed(bntSignOut);
    }*/

    public boolean isLogoutButtonPresent() {
        try {
            return bntSignOut.isDisplayed();
        } catch (Exception e) {

            return false;
        }
    }


}
