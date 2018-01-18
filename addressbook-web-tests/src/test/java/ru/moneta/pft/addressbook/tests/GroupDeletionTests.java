package ru.moneta.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase{
    
    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        if(! app.getGroupHelper().isThereAnGroup()){
            app.getGroupHelper().createGroup(new GroupData("group2", "header 2", null));
        }
        // считаем количество групп в этом месте,
        // т.к. если групп нет, то для теста группа будет создана в теле конструкции if выше.
        int before = app.getGroupHelper().getGroupsCount();
        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupsCount();
        Assert.assertEquals(after, before - 1);
    }
}
