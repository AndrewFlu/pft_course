package ru.moneta.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;
import ru.moneta.pft.addressbook.model.Contacts;
import ru.moneta.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size() == 0){
            app.goTo().ContactPage();
            app.contact().create(new ContactData().withFirstName("FirstName0").withMiddleName("MiddleName0").withLastName("LastName0").withNickName("NickName0")
                    .withCompany("Company0").withMobilePhone("+79111555000").withEmail("test0@yandex.ru"));
        }
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("GroupForContactRemovingTest"));
        }
    }

    @Test
    public void testContactRemovingFromGroup(){
        GroupData targetGroup = app.db().groups().iterator().next();
        ContactData targetContact = app.db().contacts().iterator().next();
        app.contact().goToTargetGroupPage(targetGroup);
        Contacts before = targetGroup.getContacts();
        app.contact().chooseContact(targetContact);
        app.contact().removeContactFromGroup();
        app.contact().goToChoosenGroupPage(targetGroup);
        Contacts after = getNewContactsInTargetGroup(targetGroup);
        assertThat(after, equalTo(before.without(targetContact)));
    }


}
