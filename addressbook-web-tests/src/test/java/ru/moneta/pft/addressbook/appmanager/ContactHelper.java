package ru.moneta.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.moneta.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

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
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail());

        if (creation){
            // отключаю, т.к. тест на модифиакцию контактов может упасть, если группы отсутствуют (на этапе выбора группы из селектора во время создания контакта)
            // new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            Assert.assertTrue(isElementPresent(By.name("new_group")));
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void initContactModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void selectContact() {
        click(By.xpath("//tr[2]/td/input[@name='selected[]']"));
    }

    public void deleteContact() {
        click(By.xpath("//div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void submitContactModification() {
        click(By.cssSelector("input[value=\"Update\"]"));
    }

    public void createContact(ContactData contactData) {
        initContactCreation();
        fillContactForm(contactData, true);
        submitContactCreation();
        returnToHomePage();
    }

    public boolean isThereAnContact() {
        return isElementPresent(By.xpath("//tbody/tr[2]//input"));
    }
}
