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

public class ContactAddToGroupTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions(){
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        if (groups.size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("GroupNameForDbConnection").withHeader("HeaderForDbConnection").withFooter("FooterDbConnection"));
        }
        if (contacts.size() == 0){
            app.contact().create(new ContactData()
                    .withFirstName("FirstName").withMiddleName("MiddleName").withLastName("LastName").withNickName("NickName")
                    .withCompany("Company").withMobilePhone("+79051005555").withEmail("test@yandex.ru"));
        }
    }

    @Test
    public void testContactAddToGroup() {
        Object [] testData = checkProperPreconditions();
        ContactData testContact = (ContactData) testData[0];
        GroupData testGroup = (GroupData) testData[1];
        app.goTo().ContactPage();
        app.contact().goToTargetGroupPage(testGroup);

        Contacts before = testGroup.getContacts();
        app.goTo().ContactPage();
        app.contact().addContactToGroup(testGroup, testContact);
        Contacts after = getNewContactsInTargetGroup(testGroup);
        assertThat(after, equalTo(before.withAdded(testContact)));
     }


    public Object [] checkProperPreconditions(){
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        GroupData testGroup = groups.iterator().next();
        Iterator<ContactData> iterContact = contacts.iterator();
        ContactData testContact = new ContactData();

        while (iterContact.hasNext()) {
            testContact = iterContact.next();
            for (ContactData c : contacts) {
                if (! iterContact.hasNext() || c.getGroups().equals(groups)) {
                    if (! iterContact.hasNext()) {
                        app.goTo().groupPage();
                        app.group().create(new GroupData().withName("ToAddContactGroup"));
                        testGroup = app.db().getGroupById(app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt());
                    } else {
                        testContact = iterContact.next();
                        continue;
                    }
                } else if (c.getGroups().equals(groups)) {
                    continue;
                }
                else {
                    testContact = c;
                    Groups contactGroups = testContact.getGroups();
                    Iterator<GroupData> iterGroupFromContact = contactGroups.iterator();
                    while (iterGroupFromContact.hasNext()) {
                        GroupData contactGroup = iterGroupFromContact.next();
                        for (GroupData g : contactGroups) {
                            if (! g.equals(contactGroup)) {
                                testGroup = g;
                                break;
                            } else {
                                continue;
                            }
                        }
                        break;
                    }
                }
                break;
            }
            break;
        }
        return new Object[]{testContact, testGroup};
    }

}
