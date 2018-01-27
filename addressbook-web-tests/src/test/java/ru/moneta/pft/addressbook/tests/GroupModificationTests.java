package ru.moneta.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.addressbook.model.GroupData;
import ru.moneta.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Groups before = app.group().all();
        GroupData modifyGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifyGroup.getId()).withName("Modify name 1").withHeader("Modify header 1").withFooter("Modify footer 1");
        app.group().modify(group);
        Groups after = app.group().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifyGroup).withAdded(group)));
    }
}
