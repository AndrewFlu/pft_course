package ru.moneta.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoContactPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contactData = new ContactData("FirstName Contact 3", "MiddleName Contact 3",
                "LastName Contact 3", "Contact3", "Company 3",
                "+79111555522", "test2@yandex.ru", "group2");
        app.getContactHelper().createContact(contactData);
        List<ContactData> after = app.getContactHelper().getContactList();
        before.add(contactData);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
