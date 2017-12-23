package ru.moneta.pft.sandbox;

public class FirstProgram {
    public static void main(String[] args) {
        String someString = "Hello! ";
        System.out.println(someString + "This is simple java program");

        Square s = new Square(7);
        System.out.println("Площадь квадрата со стороной " + s.l + " равна " + area(s));

        Rectangle r = new Rectangle(3,7);

        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " равна " + area(r));
    }

    public static double area (Square s){
        return s.l * s.l;
    }

    public static double area (Rectangle r){
        return r.a * r.b;
    }
}