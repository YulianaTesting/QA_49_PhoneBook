package pages;

import dto.UserLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

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
    WebElement btnRegistrationForm;

    @FindBy(xpath = "//button[text()='Sign Out']")
    WebElement bntSignOut;

    public void typeRegistrationForm(UserLombok userLombok){
        inputEmail.sendKeys(userLombok.getUsername());
        inputPassword.sendKeys(userLombok.getPassword());
        btnRegistrationForm.click();
    }

    public boolean isLogoutButtonPresent(){

        return elementIsDisplayed(bntSignOut);
    }

}
