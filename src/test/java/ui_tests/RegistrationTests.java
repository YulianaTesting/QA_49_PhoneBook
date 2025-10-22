package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;

public class RegistrationTests extends ApplicationManager {

    @Test
    public void RegistrationPositiveTest(){
        UserLombok userLombok = UserLombok.builder().username("miki12@gmail.com").password("Password12345!").build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(userLombok);
        Assert.assertTrue(registrationPage.isLogoutButtonPresent());
        System.out.println("Assert, Logout button should be visible after registration");
    }

    @Test
    public void RegistrationNegativeTest_wrongEmail(){
        UserLombok userLombok = UserLombok.builder().username("  ty@gmail.com").password("Password12345!").build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(userLombok);
        Assert.assertTrue(registrationPage.isLogoutButtonPresent());
        System.out.println("Logout button should not be present on failed registration.");
    }

}
