package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;
import ru.moneta.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().ContactPage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/bandit.jpg");
        ContactData contact = new ContactData()
                .withFirstName("Suzuki").withMiddleName("GSF").withLastName("750").withNickName("bandit")
                .withCompany("Suzuki Corp.").withMobilePhone("+79111555522").withEmail("suzukibandit@yandex.ru").withPhoto(photo);
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }

}

