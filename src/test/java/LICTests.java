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
    public void testDirectedAngleFunction() {
        /* Contract: returns the directed angle formed by three points */
        assertAll(
            () -> assertEquals(Math.PI / 2.0, // 90°
                Point.directedAngle(new Point(1,0), new Point(0,0), new Point(0,1))),

            () -> assertEquals(Math.PI, // 180°
                 Point.directedAngle(new Point(-1,0), new Point(0,0), new Point(1,0))),

            () -> assertEquals(3.0 * Math.PI / 2.0, // 270°
                 Point.directedAngle(new Point(1,0), new Point(0,0), new Point(0,-1))),

            () -> assertEquals(0.0, // 0°
                Point.directedAngle(new Point(1,0), new Point(0,0), new Point(2,0)))
        );
    }

    @Test
    /* Contract: returns the area of a triangle formed by the three points */
    public void testTriangleAreaFunction() {
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

    
    //LIC3 Unit Tests

    @Test
    void LIC3_true_Triangle_GreaterThan_AREA1() {
        /* Contract: LIC must return true iff exists at least one set of three consecutive points 
                     that form a triangle with area strictly greater than AREA1. */

        int numPoints = 3;

        Point[] points = new Point[] {
            new Point(0.0, 0.0),
            new Point(4.0, 0.0),
            new Point(0.0, 3.0), 
        };

        Parameters p = new Parameters();
        p.AREA1 = 5.0;

        assertTrue(LIC.LIC3(numPoints, points, p));
    }

    @Test
    void LIC3_false_Triangle_NotGreaterThan_AREA1() {
        /* Contract: If for every three consecutive points, the triangle area 
                     is NOT strictly greater than AREA1, then LIC3 must return false. */
        
        int numPoints = 3;

        Point[] points = new Point[] {
            new Point(0.0, 0.0),
            new Point(2.0, 0.0),
            new Point(0.0, 1.0), 
        };

        Parameters p = new Parameters();
        p.AREA1 = 2.0;

        assertFalse(LIC.LIC3(numPoints, points, p));
    }

    @Test
    void LIC3_false_InputInvalid_numPoints_Equals_2() {
        /* Contract: If there are 2 points, LIC3 has no valid triple consecutive points to evaluate, 
                     therefore it must return false. */

        int numPoints = 2;

        Point[] points = new Point[] {
            new Point(0.0, 0.0),
            new Point(4.0, 0.0),
        };

        Parameters p = new Parameters();
        p.AREA1 = 0.0;

        assertFalse(LIC.LIC3(numPoints, points, p));
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

    //LIC9 Unit Tests

    @Test
    public void LIC9_false_when_numPoint_LessThan_5() {
        /* Contract: If numPoints < 5, returns false (invalid input) */

        int numPoints = 4;
        Point[] points = new Point[] {
            new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0)
        };

        Parameters p = new Parameters();
        p.C_PTS = 1;
        p.D_PTS = 1;
        p.EPSILON = 0.1;

        assertFalse(LIC.LIC9(numPoints, points, p));
    }

    @Test
    public void LIC9_false_whenC_PTSorD_PTS_Invalid() {
        /* Contract: If C_PTS < 1 or D_PTS < 1, LCI9 returns false (invalid input) */
        
        int numPoints = 5;
        Point[] points = new Point[] {
            new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0), new Point(4,0)
        };

        Parameters p1 = new Parameters();
        p1.C_PTS = 0;
        p1.D_PTS = 1;
        p1.EPSILON = 0.1;

        assertFalse(LIC.LIC9(numPoints, points, p1));

        Parameters p2 = new Parameters();
        p2.C_PTS = 1;
        p2.D_PTS = 0;
        p2.EPSILON = 0.1;
        assertFalse(LIC.LIC9(numPoints, points, p2));
    }

    @Test
    public void LIC9_false_When_C_PTSplusD_PTS_LesserOrEqualTo_numPoints_minus_three() {
        /* Contract: If C_PTS + D_PTS > numPoints - 3, LIC9 returns false (invalid input) */
        int numPoints = 5;

        Point[] points = new Point[] {
            new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0), new Point(4,0)
        };

        Parameters p = new Parameters();
        p.C_PTS = 2;
        p.D_PTS = 1;
        p.EPSILON = 0.1;

        assertFalse(LIC.LIC9(numPoints, points, p));
    }

    @Test
    public void LIC9_true_When_AngleOutside_PIPlusMinusEpsilon() {
        /* Contract: LIC9 returns true iff there exists points A,B,C with the required spacing
        such that the angle at B is < (PI - EPSILON) or > (PI + EPSILON) */

        int numPoints = 5;

        Point[] points = new Point[] {
            new Point(0, 0), // 0 = A
            new Point(999, 999), // 1 = C_PTS
            new Point(1, 0), // 2 = B
            new Point(999, 999), // 3 = D_PTS
            new Point(1, 1)  // 4 = C
        };

        Parameters p = new Parameters();
        p.C_PTS = 1;
        p.D_PTS = 1;
        p.EPSILON = 0.1; 

        assertTrue(LIC.LIC9(numPoints, points, p));
    }

    @Test
    public void LIC9_false_AllEvaluatingAngles_AreWithin_PIPlusMinusEpsilon() {
        /* Contract:  If for every valid triple A,B,C (with required spacing),
        the angle at B lies within [PI - EPSILON, PI + EPSILON], LIC9 returns false */

        int numPoints = 5;

        Point[] points = new Point[] {
            new Point(0, 0), // 0 = A
            new Point(111, 111), // 1 = C_PTS
            new Point(1, 0), // 2 = B
            new Point(222, 222), // 3 = D_PTS
            new Point(2, 0) // 4 = C
        };

        Parameters p = new Parameters();
        p.C_PTS = 1;
        p.D_PTS = 1;
        p.EPSILON = 0.01;

        assertFalse(LIC.LIC9(numPoints, points, p));
    }

    @Test
    public void  LIC9_false_when_SkippedTriples_Where_A_equals_B_or_C_equals_B_IfNoOtherTriplesSatisfies() {
        /* Contract: f A==B or C==B, the angle is undefined and that triple is ignored.
        If no other valid triple satisfies the angle condition, LIC9 returns false. */
        
        int numPoints = 5;

        Point[] points = new Point[] {
            new Point(1, 0),  // 0 = A
            new Point(0, 0),  // 1 = C_PTS
            new Point(1, 0),  // 2 = B
            new Point(0, 0),  // 3 = D_PTS
            new Point(2, 0)   // 4 = C
        };

        Parameters p = new Parameters();
        p.C_PTS = 1;
        p.D_PTS = 1;
        p.EPSILON = 0.1;

        assertFalse(LIC.LIC9(numPoints, points, p));
    } 

    //LIC12 Unit Tests
    
    @Test
    public void LIC12_true_existsPair_GreaterThan_LENGTH1_and_existsPair_LessThan_LENGTH2() {

        int numPoints = 5;

        Parameters p = new Parameters();
        p.K_PTS = 1;
        p.LENGTH1 = 5.0;
        p.LENGTH2 = 2.0;

        Point[] points = new Point[] {
            new Point(0,0),  
            new Point(0,0),  
            new Point(6,0),  
            new Point(0,0),  
            new Point(7,0)
        };

        assertTrue(LIC.LIC12(numPoints, points, p));
    }

    @Test
    public void LIC12_false_when_onlyGreaterThan_LENGTH1() {

        int numPoints = 5;

        Parameters p = new Parameters();
        p.K_PTS = 1;
        p.LENGTH1 = 5.0;
        p.LENGTH2 = 2.0;

        Point[] points = new Point[] {
            new Point(0,0),
            new Point(0,0),
            new Point(6,0),
            new Point(0,3),
            new Point(20,0)
        };

        assertFalse(LIC.LIC12(numPoints, points, p));
    }

    @Test
    public void LIC12_false_whenOnlyLessThan_LENGTH2() {

        int numPoints = 5;

        Parameters p = new Parameters();
        p.K_PTS = 1;
        p.LENGTH1 = 5.0;
        p.LENGTH2 = 2.0;

        Point[] points = new Point[] {
            new Point(0,0),
            new Point(0,0),
            new Point(1,0),
            new Point(0,0),
            new Point(2,0)
        };

        assertFalse(LIC.LIC12(numPoints, points, p));
    }

    @Test
    public void LIC12_false_when_numPoints_lessThan_3() {

        int numPoints = 2;

        Parameters p = new Parameters();
        p.K_PTS = 1;
        p.LENGTH1 = 5.0;
        p.LENGTH2 = 2.0;

        Point[] points = new Point[] {
            new Point(0,0),
            new Point(1,0)
        };

        assertFalse(LIC.LIC12(numPoints, points, p));
    }

    @Test
    public void LIC12_false_when_LENGTH2_IsNegative() {

        int numPoints = 3;

        Parameters p = new Parameters();
        p.K_PTS = 1;
        p.LENGTH1 = 5.0;
        p.LENGTH2 = -1.0; 

        Point[] points = new Point[] {
                new Point(0,0),
                new Point(0,0),
                new Point(6,0)
        };

        assertFalse(LIC.LIC12(numPoints, points, p));

    }

    @Test
    public void testLIC13() {
        Point[] points = new Point[] {
            new Point(2, 2),
            new Point(100, 100),
            new Point(8, 2),
            new Point(101, 101),
            new Point(6, 5),
            new Point(99, 99),
            new Point(5, 11)
        };
        
        double radius = Point.circleRadius(points[0], points[2], points[4]);
        double radius2 = Point.circleRadius(points[1], points[3], points[5]);
        double radius3 = Point.circleRadius(points[0], points[2], points[6]);
        System.out.println(radius);
        System.out.println(radius2);
        System.out.println(radius3);

        assertTrue(LIC.LIC13(points.length, points, 3, 1, 1, 1.5));
        assertFalse(LIC.LIC13(points.length, points, 3, 1, 1, 1.4));
    }

}
