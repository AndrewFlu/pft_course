package ru.moneta.pft.sandbox;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PointTests {

    @Test
    public void testDistanceAssertEquals(){
        Point a = new Point(1,5);
        Point b = new Point(10, 5);
        assertEquals(b.distance(a), 9.0);
    }

    @Test
    public void testDistanceAssert(){
        Point point1 = new Point(0,0);
        Point point2 = new Point(0, -17);
        assert point1.distance(point2) == 17.0;
    }

    @Test
    public void testDistanceAssertTrue(){
        Point p1 = new Point(1,1);
        Point p2 = new Point(11,1);
        assertTrue(p1.distance(p2) == 10, "Расстояние между точками равно 10");
    }
}
