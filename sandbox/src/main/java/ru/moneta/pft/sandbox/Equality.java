package ru.moneta.pft.sandbox;

public class Equality {
    public static void main(String[] args) {
        String s1 = "firefox 5.0";
        String s2 = "firefox " + Math.sqrt(25.0);
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

    }
}
