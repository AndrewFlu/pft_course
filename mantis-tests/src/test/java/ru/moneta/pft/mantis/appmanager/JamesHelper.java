package ru.moneta.pft.mantis.appmanager;

import org.apache.commons.net.telnet.TelnetClient;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.moneta.pft.mantis.model.MailMessage;

import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JamesHelper {
    // fields
    private ApplicationManager app;

    private TelnetClient telnet;
    private InputStream in;
    private PrintStream out;

    private Session mailSession;
    private Store store;
    private String mailServer;

    // constructor

    public JamesHelper(ApplicationManager app) {
        this.app = app;
        telnet = new TelnetClient();
        mailSession = Session.getDefaultInstance(System.getProperties());
    }

    // methods

    // operations with user accounts
    public void createUser (String userName, String password){
        initTelnetSession();
        write ("adduser " + userName + " " + password);
        String result = readUntil("User " + userName + " added");
        closeTelnetSession();
    }

    public void deleteUser (String userName){
        initTelnetSession();
        write("deluser " + userName);
        String result = readUntil("User " + userName + " deleted");
        closeTelnetSession();
    }

    public boolean doesUserExist(String userName){
        initTelnetSession();
        write("verify " + userName);
        String result = readUntil("exist");
        closeTelnetSession();
        return result.trim().equals("User " + userName + " exist");

    }

    public void drainEmail (String userName, String password) throws MessagingException { // удаление всех писем в ящике
        Folder inbox = openInbox(userName, password);
        for (Message message : inbox.getMessages()){
            message.setFlag(Flags.Flag.DELETED, true);
        }
        closeFolder(inbox);
    }

    // end of operations with user accounts


    public void initTelnetSession() {
        mailServer = app.getProperty("mailserver.host");
        int port = Integer.parseInt(app.getProperty("mailserver.port"));
        String adminLogin = app.getProperty("mailserver.adminlogin");
        String adminPassword = app.getProperty("mailserver.adminpassword");

        try {
            telnet.connect(mailServer, port);
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // first attempt to login as administrator. Perhaps it wouldn't work. To fix it should use second attempt
        readUntil("Login id:");
        write(adminLogin);
        readUntil("Password:");
        write(adminPassword);

//        // second attempt
//        readUntil("Login id:");
//        write(adminLogin);
//        readUntil("Password:");
//        write(adminPassword);

        // wait successful login message
        readUntil("Welcome " + adminLogin + ". HELP for a list of commands");
    }

    public void closeTelnetSession(){
        write("quit");
    }

    private void write(String value) {
        try {
            out.println(value);
            out.flush();
            System.out.println(value);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer stringBuffer = new StringBuffer();
            char ch = (char) in.read();
            while(true){
                System.out.print(ch);
                stringBuffer.append(ch);
                if (ch == lastChar){
                    if (stringBuffer.toString().endsWith(pattern)){
                        return stringBuffer.toString();
                    }
                }
                ch = (char) in.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<MailMessage> waitForMail(String userName, String password, int timeout) throws MessagingException {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout){
            List<MailMessage> allMail = getAllMail(userName, password);
            if (allMail.size() > 0){
                return allMail;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        throw new Error("There are no mail!");
    }


    public List<MailMessage> getAllMail(String userName, String password) throws MessagingException {
        Folder inbox = openInbox(userName, password);
        List<MailMessage> messages = Arrays.asList(inbox.getMessages()).stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
        closeFolder(inbox);
        return messages;
    }

    private void closeFolder(Folder folder) throws MessagingException {
        folder.close(true);
        store.close();
    }

    private Folder openInbox(String userName, String password) throws MessagingException {
        store = mailSession.getStore("pop3");
        System.out.println("Test EMAIL username = " + userName);
        System.out.println("Test  EMAIL userPassword = " + password);

        store.connect(mailServer, userName, password);
        Folder folder = store.getDefaultFolder().getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        return folder;
    }

    public static MailMessage toModelMail(Message m){
        try {
            return new MailMessage(m.getAllRecipients()[0].toString(), (String) m.getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    public String findChangePasswordLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).filter((m) -> m.text.contains("requested a password change")).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
