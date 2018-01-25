package ru.moneta.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().gotoGroupPage();
        if(! app.getGroupHelper().isThereAnGroup()){
            app.getGroupHelper().createGroup(new GroupData("NewGroup", "ModHeader 3", "ModFooter 3"));
        }
    }
    @Test
    public void testGroupModification(){
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = before.size() - 1;
        GroupData groupData = new GroupData(before.get(index).getId(),"group1", "header 2", "footer 3");
        app.getGroupHelper().selectGroup(index);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(groupData);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(groupData);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
