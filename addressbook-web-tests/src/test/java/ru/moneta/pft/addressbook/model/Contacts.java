package ru.moneta.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet <ContactData> {

    private Set<ContactData> delegate;

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactData>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<ContactData>();
    }

    public Contacts(Collection<ContactData> contacts) {
        this.delegate = new HashSet<ContactData>(contacts);
    }

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactData contact){
        Contacts contacts = new Contacts(this);
        if (contact.getFirstName() == null) contact.withFirstName("");
        if (contact.getMiddleName() == null) contact.withMiddleName("");
        if (contact.getLastName() == null) contact.withLastName("");
        if (contact.getNickName() == null) contact.withNickName("");
        if (contact.getCompany() == null) contact.withCompany("");
        if (contact.getAddress() == null) contact.withAddress("");
        if (contact.getAddress2() == null) contact.withAddress2("");
        if (contact.getAllAddresses() == null) contact.withAllAddresses("");
        if (contact.getHomePhone() == null) contact.withHomePhone("");
        if (contact.getWorkPhone() == null) contact.withWorkPhone("");
        if (contact.getMobilePhone() == null) contact.withMobilePhone("");
        //if (contact.getAllPhones().equals(null)) contact.withAllPhones("");
        if (contact.getEmail()== null) contact.withEmail("");
        if (contact.getEmail2() == null) contact.withEmail2("");
        if (contact.getEmail3() == null) contact.withEmail3("");
        //if (contact.getAllEmails().equals(null)) contact.withAllEmails("");
        //if (contact.getGroup().equals(null)) contact.withGroup("");
        if (contact.getPhoto() == null) contact.withPhoto(null);

        contacts.add(contact);
        return contacts;
    }
    public Contacts without(ContactData contact){
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
