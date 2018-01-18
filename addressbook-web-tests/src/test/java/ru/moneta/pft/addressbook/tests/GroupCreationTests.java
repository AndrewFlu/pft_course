package ru.moneta.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupsCount();
        app.getGroupHelper().createGroup(new GroupData("group10", "header 1", null));
        int after = app.getGroupHelper().getGroupsCount();
        Assert.assertEquals(after, before + 1);
    }
}
