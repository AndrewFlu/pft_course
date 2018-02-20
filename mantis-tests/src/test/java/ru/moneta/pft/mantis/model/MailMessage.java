package ru.moneta.pft.mantis.model;

public class MailMessage {
    // fields
    public String to;
    public String text;

    // constructor
    public MailMessage(String to, String text){
        this.to = to;
        this.text = text;
    }
}
