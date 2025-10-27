package utils;

import manager.ApplicationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import static pages.BasePage.*;

public class AddNewContactTests extends ApplicationManager {
    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddPage addPage;

    @BeforeMethod
    public void login(){
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm("iluma@gmail.com", "Iluma!12345");
        contactsPage = new ContactsPage(getDriver());
        addPage = clickButtonHeader(HeaderMenuItem.ADD);

    }

    @Test
    public void addNewContactPositive(){
    }
}
