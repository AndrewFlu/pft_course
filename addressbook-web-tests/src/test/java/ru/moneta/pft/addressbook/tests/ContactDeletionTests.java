package ru.moneta.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().ContactPage();
        if(app.contact().all().size() == 0){
            app.contact().createContact(new ContactData()
                    .withFirstName("FirstName0").withMiddleName("MiddleName0").withLastName("LastName0").withNickName("NickName0")
                    .withCompany("Company0").withMobilePhone("+79111555000").withEmail("test0@yandex.ru").withGroup("group0"));
        }
    }

    @Test
    public void testContactDeletion(){
        Set<ContactData> before = app.contact().all();
        ContactData contact = before.iterator().next();
        app.contact().delete(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(contact);
        Assert.assertEquals(after, before);
    }
}
