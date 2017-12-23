package hometask1;

import static hometask1.Point.distance;

public class CoordinateProgram {

    public static void main(String[] args) {
        Point p1 = new Point(2,5);
        Point p2 = new Point (7,5);

        System.out.println("Расстояние между точкой c координатами " + p1.getCoordinate() + " " +
                "и точкой с координатами " + p2.getCoordinate() + " равно " + distance(p1,p2));
    }

}
