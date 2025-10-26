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
        UserLombok userLombok = UserLombok.builder().username("mii12@gmail.com").password("Password12345!").build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(userLombok);
        Assert.assertTrue(registrationPage.isLogoutButtonPresent());
        System.out.println("Assert, Logout button should be visible after registration");
    }

    @Test
    public void RegistrationNegativeTest_wrongEmail(){
        UserLombok userLombok = UserLombok.builder().username("@gmail.com").password("Password12345!").build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(userLombok);
        Assert.assertFalse(registrationPage.isLogoutButtonPresent(),
                "Sign Out button should NOT be visible after failed registration");
    }

    @Test
    public void registrationFailsWhenEmailMissingAtSymbol(){
        UserLombok userLombok = UserLombok.builder().username("rutgmail.com").password("Password12345!").build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(userLombok);
        Assert.assertFalse(registrationPage.isLogoutButtonPresent(),
                "Sign Out button should NOT be visible after failed registration");
    }

    @Test
    public void registrationFails_WhenEmailEmptyAfterAt(){
        UserLombok userLombok = UserLombok.builder().username("rut@").password("Password12345!").build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(userLombok);
        Assert.assertFalse(registrationPage.isLogoutButtonPresent(),
                "Sign Out button should NOT be visible after failed registration");
    }
    @Test
    public void negativeTest_EmailWithRussianLetters(){
        UserLombok userLombok = UserLombok.builder().username("Иван@gmail.com").password("Password12345!").build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(userLombok);
        Assert.assertFalse(registrationPage.isLogoutButtonPresent(),
                "Sign Out button should NOT be visible after failed registration");
    }

    @Test
    public void negativeTest_EmailWithSpaces(){
        UserLombok userLombok = UserLombok.builder().username("   bin123@gmail.com").password("Password12345!").build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(userLombok);
        Assert.assertFalse(registrationPage.isLogoutButtonPresent(),
                "Sign Out button should NOT be visible after failed registration");
    }
    }





