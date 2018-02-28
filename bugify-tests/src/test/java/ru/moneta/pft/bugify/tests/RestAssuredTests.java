package ru.moneta.pft.bugify.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.moneta.pft.bugify.model.Issue;

import java.io.IOException;
import java.util.Set;

public class RestAssuredTests {

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("28accbe43ea112d9feb328d2c00b3eed", "");
    }

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test issue subject").withDescription("Test issue description");
        int issueId = createIssue(newIssue);
        oldIssues.add(newIssue.withId(issueId));
        Set<Issue> newIssues = getIssues();
        Assert.assertEquals(newIssues, oldIssues);
    }


    // methods
    private Set<Issue> getIssues() throws IOException {
        String json = RestAssured.get("http://demo.bugify.com/api/issues.json?limit=1000").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    private int createIssue(Issue newIssue) throws IOException {
        String json = RestAssured.given()
                .parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
