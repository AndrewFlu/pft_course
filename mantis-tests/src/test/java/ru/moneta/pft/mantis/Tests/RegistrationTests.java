package ru.moneta.pft.mantis.Tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @Test
    public void testRegistration(){
        app.registration().start("user1", "user1@localhost.localdomain");

    }
}
