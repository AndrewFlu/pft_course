package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().createContact(new ContactData("FirstName Contact 1", "MiddleName Contact 1",
                "LastName Contact 1", "Contact1", "Company 1",
                "+79111555522", "test1@yandex.ru", "group1"));
    }
}
