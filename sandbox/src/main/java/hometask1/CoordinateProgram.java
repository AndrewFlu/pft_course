package hometask1;

public class CoordinateProgram {
    public static void main(String[] args) {

        double distanceBtwPoints = distance(new Point(2,2), new Point (7,2));
        System.out.println("Расстояние между точками равно " + distanceBtwPoints);
    }

    // вычисляем расстояние между двумя точками
    public static double distance(Point p1, Point p2){
        double a = Math.pow((p2.x - p1.x), 2);
        double b = Math.pow((p2.y - p1.y), 2);
        return Math.sqrt(a + b);
    }
}
