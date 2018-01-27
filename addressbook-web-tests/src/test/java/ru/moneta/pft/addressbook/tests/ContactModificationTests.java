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
            app.contact().createContact(new ContactData("FirstName Contact 6", "MiddleName Contact 6",
                    "LastName Contact 6", "Contact6", "Company 6",
                    "+79111555522", "test6@yandex.ru", "group10"));
        }
    }

    @Test
    public void testContactModification(){
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contactData = new ContactData(before.get(index).getId(),"Mod FirstName 1", "Mod midName 1", "Mod LastName 1",
                "Mod nickName 1", "Mod company 1", "89379317777",
                "mod+email1@gmail.com", "group10");
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
