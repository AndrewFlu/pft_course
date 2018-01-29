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
        // т.к. на главной странице не отображается информация об адресе 2 (tag.name="address2"),
        // достаточно будет одной проверки отображения значений в поле address
        assertThat(contact.getAddress(), equalTo(contactFromEditForm.getAddress()));
    }
}
