package ru.moneta.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("group4", "TestGroup4", "TestComment4"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
