package ru.moneta.pft.bugify.tests;


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
        String status = app.rest().getIssueStatus(issueId);
        return ! status.equals("Resolved");
    }

    public void skipIfNotFixed(int issueId) throws SkipException, IOException {
        if (isIssueOpen(issueId)){
            throw new SkipException("Ignored because of issue with id " + issueId + " is not resolved");
        }
    }



}
