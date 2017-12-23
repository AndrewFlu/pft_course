package ru.moneta.pft.sandbox;

public class FirstProgram {
    public static void main(String[] args) {
        String someString = "Hello! ";
        System.out.println(someString + "This is simple java program");
        double l = 5;
        double s = l * l;
        System.out.println("Площать квадрата со стороной " + l + " = " + s);

        double length = 7;
        System.out.println("Площадь квадрата со стороной " + length + " равна " + area(length));

        double a = 5;
        double b = 10;
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " равна " + area(a, b));
    }

    public static double area (double l){
        return l * l;
    }

    public static double area (double a, double b){
        return a * b;
    }
}