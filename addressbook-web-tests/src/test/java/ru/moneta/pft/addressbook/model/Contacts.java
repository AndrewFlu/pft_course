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
        if (contact.getFirstName().equals(null)) contact.withFirstName("");
        if (contact.getMiddleName().equals(null)) contact.withMiddleName("");
        if (contact.getLastName().equals(null)) contact.withLastName("");
        if (contact.getNickName().equals(null)) contact.withNickName("");
        if (contact.getCompany().equals(null)) contact.withCompany("");
        if (contact.getAddress().equals(null)) contact.withAddress("");
        if (contact.getAddress2().equals(null)) contact.withAddress2("");
        if (contact.getAllAddresses().equals(null)) contact.withAllAddresses("");
        if (contact.getHomePhone().equals(null)) contact.withHomePhone("");
        if (contact.getWorkPhone().equals(null)) contact.withWorkPhone("");
        if (contact.getMobilePhone().equals(null)) contact.withMobilePhone("");
        if (contact.getAllPhones().equals(null)) contact.withAllPhones("");
        if (contact.getEmail().equals(null)) contact.withEmail("");
        if (contact.getEmail2().equals(null)) contact.withEmail2("");
        if (contact.getEmail3().equals(null)) contact.withEmail3("");
        if (contact.getAllEmails().equals(null)) contact.withAllEmails("");
        if (contact.getGroup().equals(null)) contact.withGroup("");
        if (contact.getPhoto().equals(null)) contact.withPhoto(null);

        contacts.add(contact);
        return contacts;
    }
    public Contacts without(ContactData contact){
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
