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
=======
    private String address;
    private String homePhone;
>>>>>>> parent of 1844a65... Решено. Задание 11
    private String mobilePhone;
    private String email;
    private String group;

    // getters
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
=======
    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

>>>>>>> parent of 1844a65... Решено. Задание 11
    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

<<<<<<< HEAD
=======
    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

>>>>>>> parent of 1844a65... Решено. Задание 11
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
