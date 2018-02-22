package ru.moneta.pft.mantis.Tests;

import org.testng.annotations.Test;
import ru.moneta.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    // @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost", now);

        // создаем пользователя на сервере James
        app.james().createUser(user, password);

        app.registration().start(user, email);
        // Получаем письмо из внутреннего почтового сервера
        // List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);

        // Получаем письмо из внешнего почтового сервера
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);

        String confirmationLink = app.james().findConfirmationLink(mailMessages, email);
        app.registration().stop(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }



    // @AfterMethod (alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
