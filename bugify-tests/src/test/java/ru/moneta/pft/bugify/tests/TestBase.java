package ru.moneta.pft.bugify.tests;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.moneta.pft.bugify.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.GOOGLECHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.stop();
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        String json = app.rest().getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%d.json", issueId))).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issue = parsed.getAsJsonObject().get("issues");
        System.out.println(issue);
        return true;
    }

    public void skipIfNotFixed(int issueId) throws SkipException, IOException {
        if (isIssueOpen(issueId)){
            throw new SkipException("Ignored because of issue status with Id" + issueId + " is not resolved");
        }
    }



}
