package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{
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
    public void addressContactTest(){
        app.goTo().ContactPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactFromEditForm.getAddress()));
    }
}
