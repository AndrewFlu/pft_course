package ru.moneta.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().ContactPage();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstName("FirstName1").withMiddleName("MiddleName1").withLastName("LastName1").withNickName("NickName1")
                .withCompany("Company 1").withMobilePhone("+79111555522").withEmail("test2@yandex.ru").withGroup("group1");
        app.contact().createContact(contact);
        Set<ContactData> after = app.contact().all();

        before.add(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()));
        Assert.assertEquals(after, before);
    }
}
