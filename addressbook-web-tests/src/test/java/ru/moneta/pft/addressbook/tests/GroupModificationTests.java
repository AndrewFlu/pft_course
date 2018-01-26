package ru.moneta.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if(app.group().all().size() == 0){
            app.group().create(new GroupData().withName("NewGroup0").withHeader("NewHeader0").withFooter("NewFooter0"));
        }
    }
    @Test
    public void testGroupModification(){
        Set<GroupData> before = app.group().all();
        GroupData modifyGroup = before.iterator().next();
        GroupData groupData = new GroupData()
                .withId(modifyGroup.getId()).withName("Modify name 1").withHeader("Modify header 1").withFooter("Modify footer 1");
        app.group().modify(groupData);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifyGroup);
        before.add(groupData);
        Assert.assertEquals(after, before);
    }
}
