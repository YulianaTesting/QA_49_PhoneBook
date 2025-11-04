package utils;

import dto.Contact;
import manager.ApplicationManager;
import org.openqa.selenium.interactions.WheelInput;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.PropertiesReader;
import utils.ContactFactory;
import utils.HeaderMenuItem;

import static pages.BasePage.*;
import static utils.PropertiesReader.*;

public class AddNewContactTests extends ApplicationManager {
    SoftAssert softAssert = new SoftAssert();

    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddPage addPage;
    int numberOfContacts;

    @BeforeMethod
    public void login(){
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm("iluma@gmail.com", "Iluma!12345");
       // loginPage.typeLoginForm(getProperty("ase.properties", "login"),
         //       getProperty("base.properties", "password"));
        contactsPage = new ContactsPage(getDriver());
        numberOfContacts = contactsPage.getNumberOfContacts();
        addPage = clickButtonHeader(HeaderMenuItem.ADD);
    }


    @Test
    public void addNewContactPositive(){
        addPage.typContactForm(ContactFactory.positiveContact());
        int numberOfContactsAfterAdd = contactsPage.getNumberOfContacts();
        Assert.assertEquals(numberOfContactsAfterAdd,numberOfContacts + 1);
    }

    @Test
    public void addNewContactPositiveTestValidateList(){
        Contact contact = ContactFactory.positiveContact();
        addPage.typContactForm(contact);
        contactsPage.clickLastContact();
       Assert.assertTrue(contactsPage.isContactPresent(contact));
    }

    @Test
    public void addNewContactAndValidateByLastClick() {
        Contact contact = ContactFactory.positiveContact();
        addPage.typContactForm(contact);
        contactsPage.clickLastContact();
        Assert.assertTrue(contactsPage.isContactPresent(contact),
                "The contact did not appear after adding and clicking on the last element!");
    }

    @Test
    public void addNewContactPositiveTest_ValidateElementSCROLL(){
        Contact contact = ContactFactory.positiveContact();
        addPage.typContactForm(contact);
      contactsPage.scrollToLastElementList();
      contactsPage.clickLastContact();
       // contactsPage.scrollToLastElementListJS();
        String text = contactsPage.getContactCartTest();
        softAssert.assertTrue(text.contains(contact.getName()));
        softAssert.assertTrue(text.contains(contact.getLastName()));
        softAssert.assertTrue(text.contains(contact.getPhone()));
        softAssert.assertTrue(text.contains(contact.getEmail()));
        softAssert.assertTrue(text.contains(contact.getAddress()));
        softAssert.assertAll();
    }

}
