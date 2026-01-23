import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LICTests {
    
    @Test
    public void testTest() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testAngleFunction() {
        Point p1 = new Point(2, 0);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(1, 3);
        assertEquals(Point.angle(p1, p2, p3), 3.14159/2, 0.0001);
    }

    @Test
    /* Contract: returns the area of a triangle formed by the three points */
    public void testTriangleAreaFunction(){
        Point p1 = new Point(0.0, 0.0);
        Point p2 = new Point(4.0, 0.0);
        Point p3 = new Point(0.0, 3.0);

        assertEquals(6.0, Point.triangleArea(p1, p2, p3));
    }

    @Test
    public void testCircleRadiusFunction() {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(8, 2);
        Point p3 = new Point(6, 5);

        assertEquals(Point.circleRadius(p3, p1, p2), 3.004626062886658, 0.0000001);
    }

    @Test
    void LIC0_true_with_greater_distance(){
        Point[] pts = {new Point(0, 0), new Point(3,4)};
        assertTrue(LIC.LIC0(pts.length, pts, 4));
    }

    @Test
    void LIC0_false_with_smaller_distance(){
        Point[] pts = {new Point(0, 0), new Point(3,4)};
        assertFalse(LIC.LIC0(pts.length, pts, 20));
    }

    @Test
    void LIC0_false_with_too_few_points(){
        Point[] pts = {new Point(0, 0)};
        assertFalse(LIC.LIC0(pts.length, pts, 4));
    }
    
    @Test
    void testLIC1() {
        Point[] points = new Point[] {
            new Point(2, 2),
            new Point(8, 2),
            new Point(6, 5)
        };
        
        assertTrue(LIC.LIC1(points.length, points, 3));
        points[0].x = 2.1;
        assertFalse(LIC.LIC1(points.length, points, 3));

    }

    @Test
    public void testLIC2() {

        Point[] points1 = new Point[] {
            new Point(2, 2),
            new Point(5, 5)
        };

        Point[] points2 = new Point[] {
            new Point(1, 0),
            new Point(1,0),
            new Point(0, 1)
        };

        Point[] points3 = new Point[] {
            new Point(1, 1),
            new Point(1, 1),
            new Point(-2, -2),
            new Point(-2, -1),
            new Point(-3, -1)
        };

        Point[] points4 = new Point[] {
            new Point(-2, -2),
            new Point(-2, -1),
            new Point(-3, -1)
        };
        
        assertFalse(LIC.LIC2(points1.length, points1, 0.01));
        assertFalse(LIC.LIC2(points2.length, points2, 0.01));
        assertTrue(LIC.LIC2(points3.length, points3, (3.14159265359/2)-0.01));
        assertFalse(LIC.LIC2(points4.length, points4, -0.01));
        assertFalse(LIC.LIC2(points4.length, points4, 3.14159265359+0.01));
        assertFalse(LIC.LIC2(points4.length, points3, (3.14159265359/2)+0.01));
    }

    @Test
    public void testLIC7() {
        Point[] points1 = new Point[] {
            new Point(2, 2),
            new Point(5, 5),
            new Point(8, 2)
       };

        Point[] points2 = new Point[] {
            new Point(2, 2),
            new Point(15, 5),
            new Point(8, 2),
            new Point(5, 5),
            new Point(-12, 5)
        };
        
        assertTrue(LIC.LIC7(points1.length, points1, 5.9, 1));
        assertFalse(LIC.LIC7(points1.length, points1, 6, 1));
        assertTrue(LIC.LIC7(points2.length, points2, 26, 2));
        assertFalse(LIC.LIC7(points2.length, points2, 27, 2));
        
    }
}
