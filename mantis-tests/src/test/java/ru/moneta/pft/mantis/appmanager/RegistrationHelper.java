package ru.moneta.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
    // fields
    private final ApplicationManager app;
    private WebDriver driver;

    // constructor
    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        driver = app.getDriver();
    }

    // methods
    public void start(String userName, String email) {
        driver.get(app.getProperty("web.baseUrl") + "signup_page.php");
    }
}
