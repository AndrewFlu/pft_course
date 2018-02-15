package ru.moneta.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.ContactData;
import ru.moneta.pft.addressbook.model.Contacts;
import ru.moneta.pft.addressbook.model.GroupData;
import ru.moneta.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {

    @DataProvider // xml
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
            String xml = "";
            String line = reader.readLine();
            while (line != null){
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("GroupNameForDbConnection").withHeader("HeaderForDbConnection").withFooter("FooterDbConnection"));
        }
        if (app.db().contacts().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstName("FirstName").withMiddleName("MiddleName").withLastName("LastName").withNickName("NickName")
                    .withCompany("Company").withMobilePhone("+79051005555").withEmail("test@yandex.ru").inGroup(new GroupData().withName("TestGroupName1")));
        }
    }

    @Test

    public void testContactAddToGroup(){
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        app.goTo().ContactPage();
        ContactData contactDb = before.iterator().next();
        contactDb.inGroup(groups.iterator().next());

        app.contact().addContactToGroup(contactDb);
        Contacts after = app.db().contactsInChoosenGroup(contactDb.getId());


    }

}
