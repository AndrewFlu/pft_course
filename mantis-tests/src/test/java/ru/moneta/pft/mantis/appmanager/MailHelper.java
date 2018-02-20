package ru.moneta.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.moneta.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {

    // fields
    private ApplicationManager app;
    private final Wiser wiser;

    // constructor
    public MailHelper(ApplicationManager app) {
        this.app = app;
        wiser = new Wiser();
    }

    // methods
    public List<MailMessage> waitForMail (int count, long timeout) throws MessagingException, IOException {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout){
            if (wiser.getMessages().size() >= count){
                return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        throw new Error("There are no mail!");
    }

    public static MailMessage toModelMail(WiserMessage m) {
        try {
            MimeMessage mm = m.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
        } catch (MessagingException me){
            me.printStackTrace();
            return null;
        } catch (IOException ioe){
            ioe.printStackTrace();
            return null;
        }

    }


    public void start() {
        wiser.start();
    }

    public void stop() {
        wiser.stop();
    }

}
