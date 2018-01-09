package ru.moneta.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String company;
    private final String mobilePhone;
    private final String email;
    private String group;
    private boolean creation;

    public ContactData(String firstName, String middleName, String lastName, String nickName, String company, String mobilePhone, String email, String group, boolean creation) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.company = company;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.group = group;
        this.creation = creation;
    }

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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}
