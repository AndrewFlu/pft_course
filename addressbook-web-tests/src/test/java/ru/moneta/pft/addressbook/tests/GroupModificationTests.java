package ru.moneta.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{
    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        if(! app.getGroupHelper().isThereAnGroup()){
            app.getGroupHelper().createGroup(new GroupData("NewGroup", "ModHeader 3", "ModFooter 3"));
        }
        // считаем количество групп в этом месте,
        // т.к. если групп нет, то для теста группа будет создана в теле конструкции if выше.
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        GroupData groupData = new GroupData(before.get(before.size() - 1).getId(),"group1", "Mod header 1", "Mod footer 1");
        app.getGroupHelper().fillGroupForm(groupData);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(groupData);
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    }
}
