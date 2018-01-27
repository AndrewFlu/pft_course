package ru.moneta.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().ContactPage();
        if (app.contact().list().size() == 0){
            app.contact().createContact(new ContactData()
                    .withFirstName("FirstName0").withMiddleName("MiddleName0").withLastName("LastName0").withNickName("NickName0")
                    .withCompany("Company0").withMobilePhone("+79111555000").withEmail("test0@yandex.ru").withGroup("group0"));
        }
    }

    @Test
    public void testContactModification(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contactData = new ContactData()
                .withFirstName("ModFirstName1").withMiddleName("ModMiddleName1").withLastName("ModLastName1").withNickName("ModNickName1")
                .withCompany("ModCompany1").withMobilePhone("+79111555555").withEmail("test5@yandex.ru").withGroup("group5");
        app.contact().modify(index, contactData);
        List<ContactData> after = app.contact().list();

        before.remove(index);
        before.add(contactData);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
