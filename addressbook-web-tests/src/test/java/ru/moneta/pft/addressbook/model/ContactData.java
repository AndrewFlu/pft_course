package ru.moneta.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

    private int id = Integer.MAX_VALUE;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nickName;
    private String company;
<<<<<<< HEAD
<<<<<<< HEAD
=======
    private String address;
    private String homePhone;
>>>>>>> parent of 1844a65... Решено. Задание 11
=======
    private String address;
    private String address2;
    private String allAddresses;
    private String homePhone;
>>>>>>> parent of 81ab83f... Revert "Мердж проекта"
    private String mobilePhone;
    private String workPhone;
    private String allPhones;
    private String email;
    private String email2;
    private String email3;
    private String allEmails;
    private String group;

    // getters
<<<<<<< HEAD
    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getNickName() {
        return nickName;
    }
    public String getCompany() {
        return company;
    }
<<<<<<< HEAD
    public String getMobilePhone() {
        return mobilePhone;
    }
=======
    public String getAddress() { return address; }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public String getHomePhone() { return homePhone; }
    public String getWorkPhone() { return workPhone; }
    public String getAllPhones() { return allPhones; }
>>>>>>> parent of 1844a65... Решено. Задание 11
    public String getEmail() {
        return email;
    }
    public String getGroup() {
        return group;
    }
    public int getId() {
        return id;
    }
=======
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }
    public String getNickName() { return nickName; }
    public String getCompany() { return company; }
    public String getAddress() { return address; }
    public String getAddress2() { return address2; }
    public String getAllAddresses() { return allAddresses; }
    public String getMobilePhone() { return mobilePhone; }
    public String getHomePhone() { return homePhone; }
    public String getWorkPhone() { return workPhone; }
    public String getAllPhones() { return allPhones; }
    public String getEmail() { return email; }
    public String getEmail2() { return email2; }
    public String getEmail3() { return email3; }
    public String getAllEmails() { return allEmails; }
    public String getGroup() { return group; }
    public int getId() { return id; }
>>>>>>> parent of 81ab83f... Revert "Мердж проекта"

    //setters
    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> parent of 81ab83f... Revert "Мердж проекта"
    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }
<<<<<<< HEAD

>>>>>>> parent of 1844a65... Решено. Задание 11
=======
    public ContactData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }
    public ContactData withAllAddresses(String allAddresses) {
        this.allAddresses = allAddresses;
        return this;
    }

>>>>>>> parent of 81ab83f... Revert "Мердж проекта"
    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

>>>>>>> parent of 81ab83f... Revert "Мердж проекта"
    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

<<<<<<< HEAD
>>>>>>> parent of 1844a65... Решено. Задание 11
=======
>>>>>>> parent of 81ab83f... Revert "Мердж проекта"
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return getId() == that.getId() &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName());
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
