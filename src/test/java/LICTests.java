import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LICTests {
    
    @Test
    public void testTest() {
        assertEquals(4, 2 + 2);
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
            new Point(5, 5)
        };
        
        assertTrue(LIC.LIC1(points.length, points, 3.1));
        assertFalse(LIC.LIC1(points.length, points, 3));

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
