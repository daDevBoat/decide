import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LICTests {
    
    @Test
    void simpleTest() {
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
    
    @Test
    public void testLIC1() {
        Point[] points = new Point[] {
            new Point(2, 2),
            new Point(8, 2),
            new Point(5, 5)
        };
        
        assertTrue(LIC.LIC1(points.length, points, 3.1));
        assertFalse(LIC.LIC1(points.length, points, 3));

    }
}
