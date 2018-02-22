package ru.moneta.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase {

    // constructor
    public UserHelper(ApplicationManager app){
        super(app);
    }

    // methods
    public void signIn(String user, String password) {
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

}
