package ru.moneta.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UserData> {
    private Set<UserData> delegateObject;

    public Users(Users users) {
        this.delegateObject = new HashSet<UserData>(users.delegateObject);
    }

    public Users() {
        this.delegateObject = new HashSet<UserData>();
    }

    public Users(Collection<UserData> users) {
        this.delegateObject = new HashSet<UserData>(users);
    }

    @Override
    protected Set <UserData> delegate() {
        return delegateObject;
    }

//    public Users withAdded(UserData group){
//        Users users = new Users(this);
//        users.add(group);
//        return groups;
//    }
//
//    public Groups without(GroupData group){
//        Groups groups = new Groups(this);
//        groups.remove(group);
//        return groups;
//    }
}
