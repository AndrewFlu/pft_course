package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoContactPage();
        if (! app.getContactHelper().isThereAnContact()){
            app.getContactHelper().createContact(new ContactData("FirstName Contact 6", "MiddleName Contact 6",
                    "LastName Contact 6", "Contact6", "Company 6",
                    "+79111555522", "test6@yandex.ru", "group10"));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Mod name 1", "Mod midName 1", "Mod lastName 1",
                "Mod nickName 1", "Mod company 1", "89379317777", "mod+email1@gmail.com", "group10"), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }

}
