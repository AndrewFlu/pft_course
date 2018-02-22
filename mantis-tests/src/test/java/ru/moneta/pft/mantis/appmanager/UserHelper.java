package ru.moneta.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.moneta.pft.mantis.model.UserData;

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

    public void initChangeUserPassword(UserData testUser) {
        wd.findElement(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", testUser.getId()))).click();
        wd.findElement(By.cssSelector("input[value='Сбросить пароль']")).click();

    }

    public void setupNewPassword(String confirmationLink, String newUserPassword) {
        wd.get(confirmationLink);
        type(By.name("password"), newUserPassword);
        type(By.name("password_confirm"), newUserPassword);
        click(By.cssSelector("button[type='submit']"));
    }
}
