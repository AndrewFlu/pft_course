package ru.moneta.pft.bugify.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.moneta.pft.bugify.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class RestTests extends TestBase{

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = app.rest().getIssues();
        Issue newIssue = new Issue().withSubject("Test issue subject").withDescription("Test issue description");
        int issueId = app.rest().createIssue(newIssue);
        oldIssues.add(newIssue.withId(issueId));
        Set<Issue> newIssues = app.rest().getIssues();
        Assert.assertEquals(newIssues, oldIssues);
    }

    @Test
    public void testCloseIssue() throws IOException {
        Issue testIssue = app.rest().getIssues().iterator().next();

        // проверяем статус выбранного issue. Если статус отличается от "Решена" - тест игнорируется.
        // иначе - задача автоматически переводится в статус "Закрыта"
        skipIfNotFixed(testIssue.getId());

        app.rest().closeIssue(testIssue);
        String updatedIssueStatus = app.rest().getIssueStatus(testIssue.getId());
        assertEquals("Closed", updatedIssueStatus);

    }
}
