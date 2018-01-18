package ru.moneta.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{
    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        if(! app.getGroupHelper().isThereAnGroup()){
            app.getGroupHelper().createGroup(new GroupData("ModGroup3", "ModHeader 3", "ModFooter 3"));
        }
        // считаем количество групп в этом месте,
        // т.к. если групп нет, то для теста группа будет создана в теле конструкции if выше.
        int before = app.getGroupHelper().getGroupsCount();
        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("group1", "Mod header 1", "Mod footer 1"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupsCount();
        Assert.assertEquals(after, before);
    }
}
