package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoContactPage();
        if(! app.getContactHelper().isThereAnContact()){
            app.getContactHelper().createContact(new ContactData("New name 1", "New midName 1", "New lastName 1",
                    "New nickName 1", "New company 1", "89379317777", "new+email1@gmail.com", "group5"));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Mod name 1", "Mod midName 1", "Mod lastName 1",
                "Mod nickName 1", "Mod company 1", "89379317777", "mod+email1@gmail.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }

}
