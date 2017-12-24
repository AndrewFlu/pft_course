package ru.moneta.pft.sandbox;

public class CoordinateProgram {

    public static void main(String[] args) {
        Point p1 = new Point(5,1);
        Point p2 = new Point (10,1);

        System.out.println("Расстояние между точкой c координатами " + p1.getCoordinate() + " " +
                "и точкой с координатами " + p2.getCoordinate() + " равно " + p1.distance(p2));
    }

}
