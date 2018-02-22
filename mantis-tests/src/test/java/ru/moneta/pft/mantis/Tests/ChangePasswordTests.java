package ru.moneta.pft.mantis.Tests;

import org.testng.annotations.Test;
import ru.moneta.pft.mantis.model.Users;

public class ChangePasswordTests extends TestBase {

    @Test
    public void testResetUserPassword() {

        // ! нужно взять пользователя из БД


        long now = System.currentTimeMillis();
        String user = String.format("testuser%s", now);
        String password = "newpassword";
        String email = String.format("testuser%s@localhost", now);

        // login as administrator
        app.goTo().loginPage();
        app.user().signIn(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));

        // find user
        Users users = app.db().users();
        System.out.println(users);

        System.out.println("Successful login as admin");

    }

}
