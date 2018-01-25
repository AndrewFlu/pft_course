package ru.moneta.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().gotoGroupPage();
        if(! app.getGroupHelper().isThereAnGroup()){
            app.getGroupHelper().createGroup(new GroupData("group2", "header 2", null));
        }
    }
    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);
    }
}
