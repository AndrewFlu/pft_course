package ru.moneta.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

    // fields

    // constructor
    public RegistrationHelper(ApplicationManager app) {
        super (app);
    }

    // methods
    public void start(String userName, String email) {
        app.getDriver().get(app.getProperty("web.baseUrl") + "signup_page.php");
        type(By.name("username"), userName);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
    }
}
