package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;
import ru.moneta.pft.addressbook.model.Contacts;
import ru.moneta.pft.addressbook.model.GroupData;
import ru.moneta.pft.addressbook.model.Groups;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTest extends TestBase {

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
        Object [] testData = checkProperPreconditions();
        GroupData targetGroup = (GroupData) testData[1];
        ContactData targetContact = (ContactData) testData[0];
        app.contact().goToTargetGroupPage(targetGroup);
        Contacts before = targetGroup.getContacts();
        app.contact().chooseContact(targetContact);
        app.contact().removeContactFromGroup();
        app.contact().goToChoosenGroupPage(targetGroup);
        Contacts after = getNewContactsInTargetGroup(targetGroup);
        assertThat(after, equalTo(before.without(targetContact)));
    }



    public Object [] checkProperPreconditions() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        GroupData testGroup = groups.iterator().next();
        Iterator<ContactData> iterContact = contacts.iterator();
        ContactData testContact = new ContactData();
        app.goTo().ContactPage();

        while (iterContact.hasNext()) {
            testContact = iterContact.next();
            for (ContactData c : contacts) {
                if (c.getGroups().size() > 0) {
                    testContact = c;
                    testGroup = testContact.getGroups().iterator().next();
                } else if (c.getGroups().size() == 0){
                    app.contact().addContactToGroup(testGroup, testContact);
                } else {
                    continue;
                }
                break;
            }
            break;
        }
        return new Object[]{testContact, testGroup};
    }

}
