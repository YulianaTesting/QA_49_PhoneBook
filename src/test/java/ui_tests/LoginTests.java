package ui_tests;

import dto.User;
import dto.UserLombok;
import manager.ApplicationManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyzer;
import utils.TestNGListener;


@Listeners(TestNGListener.class)
public class LoginTests extends ApplicationManager {

    @Test(groups = {"smoke", "user"}) //(retryAnalyzer = RetryAnalyzer.class)
    public void loginPositiveTest(){
        logger.info("start test ");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm("a@mail.ru", "Password123!");
        //logger.error("Example error");
        Assert.assertTrue(new ContactsPage(getDriver()).isTextContactsPresent("CONTACTS"));
    }

    @Test(groups = "negative")
    public void loginNegativeTest_wrongPassword(){
        User user = new User("a@mail.ru", "password123!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginFormWithUser(user);
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test
    public void LoginSingOupPositiveTest(){
        UserLombok userLombok = UserLombok.builder().username("iluma@gmail.com").password("Iluma!12345").build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginFormWithUserLombok(userLombok);
        Assert.assertTrue(loginPage.isLogoutButtonPresent());
        System.out.println("Assert, Logout button should be visible after login");
    }

    @Test
    public void LoginNegativeTest_AlreadyRegisteredUser() {
        User user = new User("wrongemail@mail.com", "wrongpassword123!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginFormWithUser(user);
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

}



