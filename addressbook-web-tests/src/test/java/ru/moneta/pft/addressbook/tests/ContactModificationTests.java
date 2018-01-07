package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Mod name 1", "Mod midName 1", "Mod lastName 1",
                "Mod nickName 1", "Mod company 1", "89379317777", "mod+email1@gmail.com"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }

}
