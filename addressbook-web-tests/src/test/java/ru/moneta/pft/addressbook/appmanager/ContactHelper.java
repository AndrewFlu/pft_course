package ru.moneta.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.moneta.pft.addressbook.model.ContactData;
import ru.moneta.pft.addressbook.model.Contacts;
import ru.moneta.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase{


    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    String[] allGroupsInSelect;

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {

        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());


        if (creation){        // если создаем контакт
            // выполним проверку значений в селекторе выбора групп.
            String[] allGroupsInSelect = getAllValuesInSelect(By.name("new_group"));
            // проверим список значений в селекторе по длинне.
            if(allGroupsInSelect.length > 1){
                // Если в базе уже заведена как минимум 1 группа -
                // прогоним список значений селектора на предмет совпадения имени уже имеющейся группы и имени группы самого контакта
                for (int n=0; n<allGroupsInSelect.length; n++) {
                    boolean isExactlyGroup = allGroupsInSelect[n].equals(contactData.getGroup());
                    // и если имена совпадают - выбираем это значение из списка
                    if (isExactlyGroup) {
                        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
                        return;
                    }
                }
            } else{
                //  если же список селектора состоит из одного значения по умолчанию "[none]"
                // создадим группу. И в качесте имени группы укажем имя группы самого контакта
                new NavigationHelper(wd).groupPage();
                new GroupHelper(wd).create(new GroupData().withName(contactData.getGroup()));
                new NavigationHelper(wd).ContactPage();
                // продолжим сценарий создания контакта
                new ContactHelper(wd).initContactCreation();
                // вызовем рекурсию, где уже будет выполняться блок if(allGroupsInSelect.length > 1)
                // контакт создастся, а в качестве группы - будет выбрана вновь созданная группа
                fillContactForm(contactData, true);
            }

            Assert.assertTrue(isElementPresent(By.name("new_group")));
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    private String[] getAllValuesInSelect(By locator) {
        Select select = new Select(wd.findElement(locator));
        // получаем список всех элементов в селекте
        List<WebElement> allGroups = select.getOptions();
        int size = allGroups.size();
        // заполняем массив строковыми значениями из селекта
        allGroupsInSelect = new String[size];
        for (int n = 0; n < size; n++) {
            String groupInSelect = allGroups.get(n).getText().toString();
            allGroupsInSelect[n] = groupInSelect;
        }
        return allGroupsInSelect;
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void initContactModificationById(int id) {
//        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[id='%s']", id)));
//        WebElement tableRow = checkbox.findElement(By.xpath("./../.."));
//        List<WebElement> cells = tableRow.findElements(By.tagName("td"));
//        cells.get(7).findElement(By.tagName("a")).click();

//        wd.findElement(By.xpath("//tbody/tr/td/input[@id='" + id + "']/../../td[8]")).click();
          wd.findElement(By.xpath(String.format("//input[@id='%s']/../../td[8]/a", id))).click();
//        wd.findElement(By.xpath(String.format("//tr[.//input[@id='%s']]/td[8]/a", id))).click();
//        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void deleteContact() {
        click(By.xpath("//div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void submitContactModification() {
        click(By.cssSelector("input[value=\"Update\"]"));
    }

    public void create(ContactData contactData) {
        initContactCreation();
        fillContactForm(contactData, true);
        submitContactCreation();
        cashedContacts = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        cashedContacts = null;
        new NavigationHelper(wd).ContactPage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        cashedContacts = null;
        returnToHomePage();
    }

    public boolean isThereAnContact() {
        return isElementPresent(By.xpath("//tbody/tr[2]//input"));
    }

    public int count(){
        return wd.findElements(By.cssSelector("tbody>tr>td>input")).size();
    }

    private Contacts cashedContacts = null;

    public Contacts all() {
        if (cashedContacts != null){
            return new Contacts(cashedContacts);
        }
        cashedContacts = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));

        for (WebElement row : rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.name("selected[]")).getAttribute("id"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String address = cells.get(3).getText();
            String[] emails = cells.get(4).getText().split("\n");
            String phones = cells.get(5).getText();
            cashedContacts.add(new ContactData()
                    .withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address)
                    .withEmail(emails[0])
                    .withAllPhones(phones));
        }
        return new Contacts(cashedContacts);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String middleName = wd.findElement(By.name("middlename")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String nickName = wd.findElement(By.name("nickname")).getAttribute("value");
        String company = wd.findElement(By.name("company")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId()).withFirstName(firstName).withMiddleName(middleName).withLastName(lastName)
                .withNickName(nickName).withCompany(company).withAddress(address)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).withEmail(email);
    }
}
