package ru.moneta.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;
import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test (enabled = false)
    public void testContactDeletion(){
        app.goTo().gotoContactPage();
        if(! app.getContactHelper().isThereAnContact()){
            app.getContactHelper().createContact(new ContactData("FirstName Contact 6", "MiddleName Contact 6",
                    "LastName Contact 6", "Contact6", "Company 6",
                    "+79111555522", "test6@yandex.ru", "group1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.goTo().gotoContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
    }
}
