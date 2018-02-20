package ru.moneta.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;

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



    public void start() {
        wiser.start();
    }

    public void stop() {
        wiser.stop();
    }

}
