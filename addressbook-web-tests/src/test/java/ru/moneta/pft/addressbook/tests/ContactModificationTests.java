package ru.moneta.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().ContactPage();
        if (app.contact().all().size() == 0){
            app.contact().createContact(new ContactData()
                    .withFirstName("FirstName0").withMiddleName("MiddleName0").withLastName("LastName0").withNickName("NickName0")
                    .withCompany("Company0").withMobilePhone("+79111555000").withEmail("test0@yandex.ru").withGroup("group0"));
        }
    }

    @Test
    public void testContactModification(){
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("ModFirstName2").withMiddleName("ModMiddleName2")
                .withLastName("ModLastName2").withNickName("ModNickName2").withCompany("ModCompany2")
                .withMobilePhone("+79111555555").withEmail("test5@yandex.ru").withGroup("group5");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(after, before);
    }
}
