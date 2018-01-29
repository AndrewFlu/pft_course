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

        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }
}
