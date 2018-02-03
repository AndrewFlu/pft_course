package ru.moneta.pft.addressbook.Generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.moneta.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-df", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try{
            jCommander.parse(args);
        } catch (ParameterException ex){
            ex.usage();
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContact(count);
        if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")){
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try(Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        try(Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private List<ContactData> generateContact(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData().withFirstName(String.format("Name %d", i)).withLastName(String.format("LastName %d", i))
                    .withMiddleName(String.format("MiddleName %d", i)).withCompany(String.format("Company %d", i))
                    .withEmail(String.format("first.email%d@yahoo.com", i)).withEmail2(String.format("second.email%d@gmail.com", i))
                    .withEmail3(String.format("%d@mail.ru", i)).withMobilePhone(String.format("8911222550%d", i))
                    .withHomePhone(String.format("+7901505040%d", i)).withWorkPhone(String.format("55-77-5%d", i))
                    .withAddress(String.format("Russia, Petrova %d", i)).withAddress2(String.format("secondAddress %d", i)));
        }
        return contacts;
    }
}
