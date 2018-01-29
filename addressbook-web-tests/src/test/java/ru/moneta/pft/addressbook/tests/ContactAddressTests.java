package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        if(app.contact().count() == 0){
            app.contact().create(new ContactData().withFirstName("Avril").withLastName("Lavigne")
                    .withAddress("Canada Ontario").withAddress2("USA New-York"));
        }
    }

    @Test
    public void addressContactTest(){
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactFromEditForm.getAddress()));
    }
}
