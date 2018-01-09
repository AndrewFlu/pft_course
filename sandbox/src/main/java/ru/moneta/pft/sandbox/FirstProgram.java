package ru.moneta.pft.sandbox;

public class FirstProgram {
    public static void main(String[] args) {
        String someString = "Hello! ";
        System.out.println(someString + "This is simple java program");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " равна " + s.area());

        Rectangle r = new Rectangle(5,7);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " равна " + r.area());
    }

}