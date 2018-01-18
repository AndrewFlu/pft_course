package ru.moneta.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
        // массив
        String [] moto = {"Yamaha Raider", "BMW R1100R", "Yamaha Warrior", "Honda Goldwing"};

        // список
        List<String> motoBikes = Arrays.asList("Yamaha Raider", "BMW R1100R", "Yamaha Warrior", "Honda Goldwing");

        for (String bike : motoBikes) {
            System.out.println("Мне очень нравится мотоцикл " + bike);
        }
    }
}
