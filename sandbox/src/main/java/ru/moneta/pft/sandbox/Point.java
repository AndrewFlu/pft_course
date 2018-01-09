package ru.moneta.pft.sandbox;

public class Point {
    // атрибуты
    public double x;
    public double y;

    // конструктор
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    // метод для получения координат точки
    public String getCoordinate(){
        return "(" + this.x + "; " + this.y + ")";
    }

    // вычисляем расстояние между двумя точками
    public double distance(Point p2){
        double a = Math.pow((p2.x - this.x), 2);
        double b = Math.pow((p2.y - this.y), 2);
        return Math.sqrt(a + b);
    }
}
