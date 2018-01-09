package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{
    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("Mod name 1", "Mod header 1", "Mod footer 1"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
