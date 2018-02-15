package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;
import ru.moneta.pft.addressbook.model.Contacts;
import ru.moneta.pft.addressbook.model.GroupData;
import ru.moneta.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions(){
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        if (groups.size() == 0){
            app.group().create(new GroupData().withName("GroupNameForDbConnection").withHeader("HeaderForDbConnection").withFooter("FooterDbConnection"));
        }
        if (contacts.size() == 0){
            app.contact().create(new ContactData()
                    .withFirstName("FirstName").withMiddleName("MiddleName").withLastName("LastName").withNickName("NickName")
                    .withCompany("Company").withMobilePhone("+79051005555").withEmail("test@yandex.ru"));
        }

    }

    @Test

    public void testContactAddToGroup(){
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        // выбираем объект типа GroupData для дальнейшего использования в тесте
        ContactData targetContact = contacts.iterator().next();
        GroupData targetGroup = groups.iterator().next();

        for (int i = 0; i < contacts.size(); i++){
            if (targetContact.getGroups().equals(groups)){
                targetContact = contacts.iterator().next();
            } else {
                break;
            }
        }

        if (targetContact.getGroups().equals(groups)){
            app.goTo().groupPage();
            targetGroup = new GroupData().withName("NewGroup1");
            app.group().create(targetGroup);
            targetContact.inGroup(targetGroup);
        }

        app.goTo().ContactPage();
      //  app.contact().goToTargetGroupPage(targetGroup);


        Contacts before = targetGroup.getContacts();
        app.goTo().ContactPage();
        app.contact().addContactToGroup(targetGroup, targetContact);

        Contacts after = app.db().contactsInChoosenGroup(targetGroup);
        assertThat(after, equalTo(before.withAdded(targetContact)));


    }

}
