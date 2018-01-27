package ru.moneta.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().ContactPage();
        List<ContactData> before = app.contact().list();
        ContactData contactData = new ContactData("FirstName1", "MiddleName 2",
                "LastName1", "Contact2", "Company 1",
                "+79111555522", "test2@yandex.ru", "group2");
        app.contact().createContact(contactData);
        List<ContactData> after = app.contact().list();

        before.add(contactData);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
