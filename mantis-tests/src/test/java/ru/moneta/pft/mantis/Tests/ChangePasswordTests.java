package ru.moneta.pft.mantis.Tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.moneta.pft.mantis.model.MailMessage;
import ru.moneta.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() throws MessagingException {
        if (app.db().users().size() == 0){
            long now = System.currentTimeMillis();
            String user = String.format("user%s", now);
            String password = "password";
            String email = String.format("user%s@localhost", now);
            app.james().createUser(user, password);
            app.registration().start(user, email);
            List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
            String confirmationLink = app.james().findConfirmationLink(mailMessages, email);
            app.registration().stop(confirmationLink, password);
        }
    }


    @Test
    public void testResetUserPassword() throws MessagingException, IOException {
        String currentUserPassword = "password";
        String newUserPassword = "newpassword";

        // get user from DB
        UserData testUser = app.db().users().iterator().next();

        // login as administrator
        app.goTo().loginPage();
        app.user().signIn(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.goTo().manageUserPage();
        app.user().initChangeUserPassword(testUser);

        // get user email and use link to change password
        app.james().initTelnetSession();
        app.james().drainEmail(testUser.getUserName(), currentUserPassword);
        List<MailMessage> emailMessages = app.james().waitForMail(testUser.getUserName(), currentUserPassword, 60000);
        String confirmationLink = app.james().findChangePasswordLink(emailMessages, testUser.getEmail());

        // set up new password
        app.user().setupNewPassword(confirmationLink, newUserPassword);

        assertTrue(app.newSession().login(testUser.getUserName(), newUserPassword));
    }

}
