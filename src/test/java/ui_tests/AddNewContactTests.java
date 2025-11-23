package ui_tests;

import data_providers.ContactDP;
import dto.Contact;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.ContactFactory;
import utils.HeaderMenuItem;
import utils.TestNGListener;

import static pages.BasePage.*;
import static utils.PropertiesReader.*;


@Listeners(TestNGListener.class)
public class AddNewContactTests extends ApplicationManager {

    SoftAssert softAssert = new SoftAssert();

    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddPage addPage;
    int numberOfContacts;

    @BeforeMethod(alwaysRun = true)
    public void login(){
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm("iluma@gmail.com", "Iluma!12345");
       // loginPage.typeLoginForm(getProperty("base.properties", "username"),
        //      getProperty("base.properties", "password"));
        contactsPage = new ContactsPage(getDriver());
        numberOfContacts = contactsPage.getNumberOfContacts();
        addPage = clickButtonHeader(HeaderMenuItem.ADD);
    }


    @Test(groups = {"smoke", "contact"})
    public void addNewContactPositive(){
        addPage.typContactForm(ContactFactory.positiveContact());
        int numberOfContactsAfterAdd = contactsPage.getNumberOfContacts();
        Assert.assertEquals(numberOfContactsAfterAdd,numberOfContacts + 1);
    }

    @Test(dataProvider = "dataProviderContactFile", dataProviderClass = ContactDP.class)
    public void addNewContactPositiveTest_withDataProvider(Contact contact){
        addPage.typContactForm(contact);
        int numberOfContactsAfterAdd = contactsPage.getNumberOfContacts();
        Assert.assertEquals(numberOfContactsAfterAdd, numberOfContacts + 1);
    }

    @Test
    public void addNewContactPositiveTestValidateList(){
        Contact contact = ContactFactory.positiveContact();
        addPage.typContactForm(contact);
       // contactsPage.clickLastContact();
       Assert.assertTrue(contactsPage.isContactPresent(contact),"message");
    }

    @Test
    public void addNewContactAndValidateByLastClick() {
        Contact contact = ContactFactory.positiveContact();
        addPage.typContactForm(contact);
        contactsPage.clickLastContact();
        Assert.assertTrue(contactsPage.isContactPresent(contact),
                "The contact did not appear after adding and clicking on the last element!");
    }

  /*  @Test
    public void addNewContactPositiveTest_ValidateElementSCROLL(){
        Contact contact = ContactFactory.positiveContact();
        addPage.typContactForm(contact);
      contactsPage.scrollToLastElementList();
      contactsPage.clickLastContact();
       // contactsPage.scrollToLastElementListJS();
        String text = contactsPage.getContactCardTest();
        softAssert.assertTrue(text.contains(contact.getName()));
        softAssert.assertTrue(text.contains(contact.getLastName()));
        softAssert.assertTrue(text.contains(contact.getPhone()));
        softAssert.assertTrue(text.contains(contact.getEmail()));
        softAssert.assertTrue(text.contains(contact.getAddress()));
        softAssert.assertAll();
    }

    @Test(dataProvider = "dataProviderContactFile", dataProviderClass = ContactDP.class)
    public void addNewContactPositive_withDataProvider(Contact contact){
        addPage.typContactForm(contact);
        int numberOfContactsAfterAdd = contactsPage.getNumberOfContacts();
        Assert.assertEquals(numberOfContactsAfterAdd,numberOfContacts + 1);
    } */

    @Test(groups = "negative")
    public void addNewContactPositiveTest_validateElementSCROLL1(){
        Contact contact = ContactFactory.positiveContact();
        addPage.typContactForm(contact);
        contactsPage.scrollToLastElementList();
        contactsPage.clickLastContact();
        //contactsPage.scrollToLastElementListJS();
        String text = contactsPage.getContactCardTest();
        softAssert.assertTrue(text.contains(contact.getName()));
        softAssert.assertTrue(text.contains(contact.getLastName()));
        softAssert.assertTrue(text.contains("zzzzzzzzzzzzzzzzzzzzzzzzzzzz"),
                "message contains Phone");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        softAssert.assertTrue(text.contains(contact.getEmail()));
        softAssert.assertTrue(text.contains(contact.getAddress()));
        softAssert.assertAll();
    }


}
