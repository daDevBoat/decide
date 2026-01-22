import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LICTests {
    
    @Test
    void simpleTest() {
        assertEquals(4, 2 + 2);
    }

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
