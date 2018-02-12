package ru.moneta.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData> delegateObject;

    public Groups(Groups groups) {
        this.delegateObject = new HashSet<GroupData>(groups.delegateObject);
    }

    public Groups() {
        this.delegateObject = new HashSet<GroupData>();
    }

    public Groups(Collection<GroupData> groups) {
        this.delegateObject = new HashSet<GroupData>(groups);
    }

    @Override
    protected Set <GroupData> delegate() {
        return delegateObject;
    }

    public Groups withAdded(GroupData group){
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups without(GroupData group){
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }

}
