package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().createContact(new ContactData("FirstName Contact 6", "MiddleName Contact 6",
                "LastName Contact 6", "Contact6", "Company 6",
                "+79111555522", "test6@yandex.ru", "group1"));
    }
}
