package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;
import ru.moneta.pft.addressbook.model.Contacts;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size() == 0){
            app.goTo().ContactPage();
            app.contact().create(new ContactData()
                    .withFirstName("FirstName0").withMiddleName("MiddleName0").withLastName("LastName0").withNickName("NickName0")
                    .withCompany("Company0").withMobilePhone("+79111555000").withEmail("test0@yandex.ru").withGroup("group0"));
        }
    }

    @Test
    public void testContactModification(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("ModFirstName").withMiddleName("ModMiddleName")
                .withLastName("ModLastName").withNickName("ModNickName").withCompany("ModCompany")
                .withMobilePhone("+79111555555").withEmail("test@yandex.ru").withGroup("group");

        app.goTo().ContactPage();
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        ContactData contactDb = app.db().contactInfoFromDb(contact);
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactDb)));
        //assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
