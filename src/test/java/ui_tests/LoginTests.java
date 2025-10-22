package ui_tests;

import dto.User;
import dto.UserLombok;
import manager.ApplicationManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends ApplicationManager {

    @Test
    public void loginPositiveTest(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm("a@mail.ru", "Password123!");
    }

    @Test
    public void loginNegativeTest_wrongPassword(){
        User user = new User("a@mail.ru","password4321");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginFormWithUser(user);
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




}

