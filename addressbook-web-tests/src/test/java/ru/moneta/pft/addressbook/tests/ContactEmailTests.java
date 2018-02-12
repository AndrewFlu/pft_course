package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if(app.db().contacts().size() == 0){
            app.goTo().ContactPage();
            app.contact().create(new ContactData().withFirstName("Avril").withLastName("Lavigne")
                    .withMiddleName("Ramona").withMobilePhone("250-440")
                    .withCompany("RockStar").withAddress("Canada, Ontario")
                    .withEmail("avril-lavigne@mail.com").withEmail2("avril@mail.com"));
        }
    }

    @Test
    public void emailContactTest(){
        app.goTo().ContactPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(mergedEmails(contactFromEditForm)));
    }

    private String mergedEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((c) -> ! c.equals("")).collect(Collectors.joining("\n"));
    }
}
