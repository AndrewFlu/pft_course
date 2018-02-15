package ru.moneta.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
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

        // выбираем объект типа GroupData для дальнейшего использования в тесте
        // сохраним в переменную before все контакты, находящиеся в выбранной группе до теста
        Groups groups = app.db().groups();
        ContactData targetContact = app.db().contacts().iterator().next();
        GroupData targetGroup = groups.iterator().next();
        app.contact().goToTargetGroupPage(targetGroup);

        // на этом шаге ошибка: Unknown column 'group_id' in 'where clause'
        Contacts before = app.db().contactsInChoosenGroup(targetGroup);

        // добавляем контакты в выбранную группу
        app.goTo().ContactPage();
        app.contact().addContactToGroup(targetGroup, targetContact);

        // сохраним новый список контактов в переменную after после теста
        Contacts after = app.db().contactsInChoosenGroup(targetGroup);
        assertThat(after, equalTo(before.withAdded(targetContact)));


    }

}
