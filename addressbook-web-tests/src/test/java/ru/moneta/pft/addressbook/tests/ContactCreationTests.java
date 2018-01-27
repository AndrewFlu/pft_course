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
        ContactData contactData = new ContactData()
                .withFirstName("FirstName1").withMiddleName("MiddleName1").withLastName("LastName1").withNickName("NickName1")
                .withCompany("Company 1").withMobilePhone("+79111555522").withEmail("test2@yandex.ru").withGroup("group1");
        app.contact().createContact(contactData);
        List<ContactData> after = app.contact().list();

        before.add(contactData);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
