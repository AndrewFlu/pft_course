package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

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
    public void phoneContactTest(){
        app.goTo().ContactPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

    }


    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s)-> ! s.equals("")).map(ContactPhoneTests::cleanedPhone)
                .collect(Collectors.joining("\n"));
    }

    private static String cleanedPhone(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
