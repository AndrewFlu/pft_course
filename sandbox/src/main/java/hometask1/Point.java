package hometask1;

public class Point {
    // атрибуты
    public double x;
    public double y;

    // конструктор
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    // вычисляем расстояние между двумя точками
    public static double distance(Point p1, Point p2){
        double a = Math.pow((p2.x - p1.x), 2);
        double b = Math.pow((p2.y - p1.y), 2);
        return Math.sqrt(a + b);
    }

    // метод для получения координат точки
    public String getCoordinate(){
        return "(" + this.x + "; " + this.y + ")";
    }
}
