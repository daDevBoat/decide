import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

public class LICTests {

    @Test
    public void testTest() {
        // Contract: 2 + 2 should be 4, used to see if the JUnit works
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testAngleFunction() {
        /* Point.angle(Point p1, Point p2, Point p3) returns the 
        angle of the 3 points when using the dot product method */
        Point p1 = new Point(2, 0);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(1, 3);
        assertEquals(Point.angle(p1, p2, p3), 3.14159 / 2, 0.0001);
        assertNotEquals(Point.angle(p1, p2, p3), 3.14159 / 3, 0.0001);
    }

    @Test
    public void testDirectedAngleFunction() {
        /* Contract: returns the directed angle formed by three points */
        assertAll(
                () -> assertEquals(Math.PI / 2.0, // 90°
                        Point.directedAngle(new Point(1, 0), new Point(0, 0), new Point(0, 1))),

                () -> assertEquals(Math.PI, // 180°
                        Point.directedAngle(new Point(-1, 0), new Point(0, 0), new Point(1, 0))),

                () -> assertEquals(3.0 * Math.PI / 2.0, // 270°
                        Point.directedAngle(new Point(1, 0), new Point(0, 0), new Point(0, -1))),

                () -> assertEquals(0.0, // 0°
                        Point.directedAngle(new Point(1, 0), new Point(0, 0), new Point(2, 0))));
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
    /*
    * Contract: the function returns the radius of the smallest circle that contains or has the three
    * points on its boarder.
    */ 
    public void testCircleRadiusFunction() {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(8, 2);
        Point p3 = new Point(6, 5);

        assertEquals(Point.circleRadius(p3, p1, p2), 3.004626062886658, 0.0000001);
    }

    @Test
    /*
    * Contract: DistancePointToLine returns the closest distance of the 3rd point p3 to the line created by points
    * p1 and p2.   
    */
    public void testDistancePointToLine() {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(5, 4);
        Point p3 = new Point(4, 2);

        assertEquals(1.1094, Point.distancePointToLine(p1, p2, p3), 0.0001);
        assertEquals(1.1094, Point.distancePointToLine(p2, p1, p3), 0.0001);
        assertEquals(1.7889, Point.distancePointToLine(p2, p3, p1), 0.0001);
        assertEquals(3.6056, Point.distancePointToLine(p1, p1, p2), 0.0001);
    }

    @Test
    /*
    * Contract: PointQuadrantEvaluation fulfills: There exists at least one set of Q PTS consecutive data points that lie 
    * in more than QUADS quadrants. Where there is ambiguity as to which quadrant contains a given point, priority of  
    * decision will be by quadrant number, i.e., I, II, III, IV. For example, the data point (0,0)
    * is in quadrant I, the point (-l,0) is in quadrant II, the point (0,-l) is in quadrant III, the point
    * (0,1) is in quadrant I and the point (1,0) is in quadrant I.
    */
    public void testPointQuadrantEvaluation() {
        Point[] quadOne = new Point[]{
            new Point(0,0),
            new Point(0,1),
            new Point(1, 0),

        };
        Point[] quadTwo = new Point[]{
            new Point(-1,0),
            new Point(-2,0),
            new Point(-2,2)
        };
        Point[] quadThree = new Point[]{
            new Point(0,-1),
            new Point(0,-3),
            new Point(-2,-3)
        };
        Point[] quadFour = new Point[]{
            new Point(1, -1),
            new Point(2, -3)
        };
        for (Point p : quadOne) {
            assertTrue(Point.pointQuadrant(p) == 1);
        }
        for (Point p : quadTwo) {
            assertTrue(Point.pointQuadrant(p) == 2);
        }
        for (Point p : quadThree) {
            assertTrue(Point.pointQuadrant(p) == 3);
        }
        for (Point p : quadFour) {
            assertTrue(Point.pointQuadrant(p) == 4);
        }
    }

    @Test
    /*
    * Contract: LIC0 is true iff at least two consecutive points exist, with
    * distance strictly greater than LENGHT1 between them.
    */
    void LIC0_true_with_greater_distance() {
        Parameters p = new Parameters();
        p.LENGTH1 = 4;
        Point[] pts = { new Point(0, 0), new Point(3, 4) };
        assertTrue(LIC.LIC0(pts.length, pts, p));
    }

    @Test
    /*
    * Contract: LIC0 is false iff no two consecutive points exists, with
    * distance strictly greater than LENGHT1 between them.
    */
    void LIC0_false_with_smaller_distance() {
        Parameters p = new Parameters();
        p.LENGTH1 = 20;
        Point[] pts = { new Point(0, 0), new Point(3, 4) };
        assertFalse(LIC.LIC0(pts.length, pts, p));
    }

    @Test
    // Contract: LIC0 throws an IllegelArgumentException if less than two points exist.
    void LIC0_throws_with_too_few_points() {
        Parameters p = new Parameters();
        p.LENGTH1 = 4;
        Point[] pts = { new Point(0, 0) };
        assertThrows(IllegalArgumentException.class, () -> LIC.LIC0(pts.length, pts,  p));
    }

    @Test
    // Contract: LIC0 throws an IllegelArgumentException if LENGTH1 < 0
    void LIC0_throws_with_length_too_small() {
        Parameters p = new Parameters();
        p.LENGTH1 = -1;
        Point[] pts = { new Point(0, 0), new Point(3, 4)};
        assertThrows(IllegalArgumentException.class, () -> LIC.LIC0(pts.length, pts,  p));
    }


    @Test
    /*
    * Contract: Method throws exception due to RADIUS1 being invalid since it is negative
    */
    void testLIC1InvalidRADIUS1() {
        Point[] points = new Point[] {
            new Point(2, 2),
            new Point(8, 2),
            new Point(6, 5)
        };
        Parameters p = new Parameters();
        p.RADIUS1 = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC1(points.length, points, p);
        });

    }

    @Test
    /*
    * Contract: LIC1 is true iff there exists 3 consecutive points that
    * cannot all be contained within a circle of RADIUS1. If not such 3 
    * consecutive points exist then LIC1 is false
    */
    void testLIC1() {
        Point[] points = new Point[] {
            new Point(2, 2),
            new Point(8, 2),
            new Point(6, 5)
        };
        Parameters p = new Parameters();
        p.RADIUS1 = 3;

        assertTrue(LIC.LIC1(points.length, points, p));
        points[0].x = 2.1;
        assertFalse(LIC.LIC1(points.length, points, p));

    }

    @Test
    public void testLIC2() {
        /* LIC2 returns true if and only if
        there exists at least one set of three consecutive data points which form an angle such that:
        angle < (PI−EPSILON)
        or
        angle > (PI+EPSILON)
        The second of the three consecutive points is always the vertex of the angle. If either the first
        point or the last point (or both) coincides with the vertex, the angle is undefined and the LIC
        is not satisfied by those three points.
        (0 ≤ EPSILON < PI) */
        Parameters p = new Parameters();
        p.EPSILON = 0.01;

        Point[] points1 = new Point[] {
                new Point(2, 2),
                new Point(5, 5)
        };

        Point[] points2 = new Point[] {
                new Point(1, 0),
                new Point(1, 0),
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

        //Tests that false is returned when NUMPOINTS < 3
        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC2(points1.length, points1, p);
        });

        //Tests that false is returned if any point coincides with the vertex
        assertFalse(LIC.LIC2(points2.length, points2, p));

        //Tests that true is returned for an input which fulfills the contract 
        p.EPSILON = (PI / 2) - 0.01;
        assertTrue(LIC.LIC2(points3.length, points3, p));

        //Tests that false is returned when EPSILON < 0
        p.EPSILON = -0.01;
        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC2(points4.length, points4, p);
        });

        //Tests that false is returned when EPISILON > PI
        p.EPSILON = PI + 0.01;
        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC2(points4.length, points4, p);
        });

        //Tests that false is returned when there does not exist at least one set of three 
        //consecutive data points which form an angle such that: angle < (PI−EPSILON)
        //or angle > (PI+EPSILON)
        p.EPSILON = PI / 2 + 0.01;
        assertFalse(LIC.LIC2(points4.length, points3, p));
    }

    // LIC3 Unit Tests

    @Test
    public void LIC3_true_Triangle_GreaterThan_AREA1() {
        /*
         * Contract: LIC must return true iff exists at least one set of three
         * consecutive points
         * that form a triangle with area strictly greater than AREA1.
         */

        
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
    public void LIC3_false_InputInvalid_AREA1_Negative() {
        /* Contract: If AREA1 < 0 (invalid input), LIC3 must return false */
        int numPoints = 3;
        Point[] points = new Point[] {
            new Point(0, 0), new Point(4,0), new Point(0,3)
        };

        Parameters p = new Parameters();
        p.AREA1 = -1.0;

        assertFalse(LIC.LIC3(numPoints, points, p));
    }

    @Test
    public void LIC3_false_Triangle_NotGreaterThan_AREA1() {
        /*
         * Contract: If for every three consecutive points, the triangle area
         * is NOT strictly greater than AREA1, then LIC3 must return false.
         */

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
    public void LIC3_false_InputInvalid_numPoints_Equals_2() {
        /*
         * Contract: If there are 2 points, LIC3 has no valid triple consecutive points
         * to evaluate,
         * therefore it must return false.
         */

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
    void LIC4_true_five_consecutive_points_three_quadrants() {
        /* 
         * Contract: LIC4 is true iff there is at least Q_PTS number of consecutive points that are
        contained in more than QUADS quadrants.
        Q_PTS=5, QUADS=2, with an array of five points that are contained in three quadrants.
         */
        Parameters p = new Parameters();
        p.Q_PTS = 5;
        p.QUADS = 2;
        
        Point[] points = new Point[] {
                new Point(0, 0), // Q1
                new Point(1, 0), // Q1
                new Point(-1, 0), // Q2
                new Point(0, -2), // Q3
                new Point(4, 3), // Q1
        };
        assertTrue(LIC.LIC4(points.length, points, p));
    }

    @Test
    void LIC4_throws_on_too_few_points() {
        /* 
         * Contract: LIC4 has constraints: 2 <= Q_PTS <= NUMPOINTS, 1 <= QUADS <= 3. 
        * Set Q_PTS = 5, QUADS = 3, with 4 points. 
         */
        Parameters p = new Parameters();
        p.Q_PTS = 5;
        p.QUADS = 3;
        
        Point[] points = new Point[] {
                new Point(0, 0),
                new Point(1, 0),
                new Point(-1, 0),
                new Point(0, -2),
        };
        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC4(points.length, points, p);
        });
    }

    @Test
    void LIC4_throws_on_too_many_quads() {
        /* 
         * Contract: LIC4 has constraints: 2 <= Q_PTS <= NUMPOINTS, 1 <= QUADS <= 3. 
         * Set Q_PTS = 3, QUADS = 4, with 4 points.
         */
        Parameters p = new Parameters();
        p.Q_PTS = 3;
        p.QUADS = 4;
        
        Point[] points = new Point[] {
                new Point(0, 0),
                new Point(1, 0),
                new Point(-1, 0),
                new Point(0, -2),
        };
        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC4(points.length, points, p);
        });
    }

    @Test
    void LIC4_throws_on_too_few_quadrants() {
        /* 
         * Contract: LIC4 is false when the are not Q_PTS number of consecutive points that are contained in more than QUADS quadrants.
        Set Q_PTS=5, QUADS=2, with an array of five points that are contained in two quadrants. 
         */
        Parameters p = new Parameters();
        p.Q_PTS = 5;
        p.QUADS = 2;
        
        Point[] points = new Point[] {
                new Point(0, 0), // Q1
                new Point(1, 0), // Q1
                new Point(-1, 0), // Q2
                new Point(-2, 0), // Q2
                new Point(4, 3), // Q1
        };
        assertFalse(LIC.LIC4(points.length, points, p));
    }

    @Test
    void LIC5_true_two_consecutive_points_x_difference_less_than_zero() {
        /*
         * Contract: LIC5 is true iff there exists at least one pair of consecutive points (i, i+1)
         * such that the x coordinate of i is larger than that of i+1: X[i+1]-X[i] < 0.
         * The minimum amounts of points (2) is being tested, where the second point has a lower x coordinate
         * than the first.
         */
        Parameters p = new Parameters();
        Point[] points = new Point[] {
                new Point(2, 0),
                new Point(1, 1),
        };

        assertTrue(LIC.LIC5(points.length, points, p));
    }

    @Test
    void LIC5_false_two_consecutive_points_x_difference_larger_than_zero() {
        /*
         * Contract: LIC5 is false iff there is no pair of consecutive points (i, i+1)
         * such that the x coordinate of i is larger than that of i+1: X[i+1]-X[i] < 0.
         * The minimum amount of points (2) is being tested, where the second point has a higher x coordinate
         * than the first.
         */
        Parameters p = new Parameters();
        Point[] points = new Point[] {
                new Point(1, 0),
                new Point(2, 1),
        };

        assertFalse(LIC.LIC5(points.length, points, p));
    }

    @Test
    void LIC5_throws_on_too_few_points() {
        /* 
         * Contract: LIC5 needs at least two points to evaluate.
         * Use only 1 point.
         */
        Parameters p = new Parameters();
        
        Point[] points = new Point[] {
                new Point(0, 0)
        };
        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC5(points.length, points, p);
        });
    }

    @Test
    public void LIC6_false_with_points_too_close() {
        /* 
         * Contract: LIC6 is false iff there exists no set of N PTS consecutive data points such that at 
         * least one of the points lies a distance greater than DIST from the line joining the first
         * and last of these N PTS points.
         * All points in this test are within a small area, max distance: 2
         */
        Parameters p = new Parameters();
        p.N_PTS = 3;
        p.DIST = 30;
        Point[] points1 = new Point[] {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(1, 1),
                new Point(2, 2),
                new Point(1, 1),
                new Point(0, 3),
                new Point(1, 2),
                new Point(6, 0)
        };
        assertFalse(LIC.LIC6(points1.length, points1, p));
    }

    @Test
    public void LIC6_true() {
        Parameters p = new Parameters();
        p.N_PTS = 3;
        p.DIST = 3;
        /* 
         * Contract: LIC6 is true iff there exists at least one set of N PTS consecutive data points such that at 
         * least one of the points lies a distance greater than DIST from the line joining the first
         * and last of these N PTS points.
         * Line fron (1,2) - (1,-1) is parallel to the y axis - point (6,0) is 5 away
         */

        Point[] points2 = new Point[] {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(1, 1),
                new Point(2, 2),
                new Point(1, 1),
                new Point(0, 3),
                new Point(1, 2),
                new Point(6, 0),
                new Point(1, -1)
        };
        assertTrue(LIC.LIC6(points2.length, points2, p));
    }

    @Test
    public void LIC6_false_with_too_few_points() {
        // Contract: LIC6 is false iff there exist less than 3 points
        Parameters p = new Parameters();
        p.N_PTS = 3;
        p.DIST = 30;
        Point[] points3 = new Point[] {
                new Point(0, 0),
                new Point(1, 0)
        };
        assertFalse(LIC.LIC6(points3.length, points3, p));
    }

     @Test
    public void LIC6_throws_exception_with_DIST_too_small() {
        // Contract: LIC6 throws an exception iff DIST < 0
        Parameters p = new Parameters();
        p.N_PTS = 3;
        p.DIST = -1;
        Point[] points3 = new Point[] {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(1, 1),
                new Point(2, 2),
                new Point(1, 1),
                new Point(0, 3),
                new Point(1, 2),
                new Point(6, 0),
                new Point(1, -1)
        };
        assertThrows(IllegalArgumentException.class, () -> LIC.LIC6(points3.length, points3, p));
    }

    @Test
    public void LIC6_throws_exception_with_too_small_N_PTS() {
        // Contract: LIC6 throws an exception iff N_PTS < 3
        Parameters p = new Parameters();
        p.N_PTS = 2;
        p.DIST = 30;
        Point[] points2 = new Point[] {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(1, 1),
                new Point(2, 2),
                new Point(1, 1),
                new Point(0, 3),
                new Point(1, 2),
                new Point(6, 0),
                new Point(1, -1)
        };
        assertThrows(IllegalArgumentException.class, () -> LIC.LIC6(points2.length, points2, p));
    }

    @Test
    public void LIC6_throws_exception_with_N_PTS_bigger_than_numpoints() {
        // Contract: LIC6 throws an exception iff N_PTS > numpoints
        Parameters p = new Parameters();
        p.N_PTS = 20;
        p.DIST = 30;
        Point[] points2 = new Point[] {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(1, 1),
                new Point(2, 2),
                new Point(1, 1),
                new Point(0, 3),
                new Point(1, 2),
                new Point(6, 0),
                new Point(1, -1)
        };
        assertThrows(IllegalArgumentException.class, () -> LIC.LIC6(points2.length, points2, p));
    }

    @Test
    public void testLIC7InvalidKPTS() {
        /*
        * Contract: Method throws exception due to K_PTS being invalid since it is sat 0
        */
        Point[] points = new Point[] {
                new Point(2, 2),
                new Point(5, 5),
                new Point(8, 2)
        };

        Parameters p = new Parameters();
        p.LENGTH1 = 1;
        p.K_PTS = 0;
        
        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC7(points.length, points, p);
        });
    }

    @Test
    public void testLIC7InvalidLENGTH1() {
        /*
        * Contract: Method throws exception due to LENGTH1 being invalid since it is negtive
        */
        Point[] points = new Point[] {
                new Point(2, 2),
                new Point(5, 5),
                new Point(8, 2)
        };

        Parameters p = new Parameters();
        p.LENGTH1 = -1;
        p.K_PTS = 1;
        
        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC7(points.length, points, p);
        });
    }

    @Test
    public void testLIC7() {
        /*
        * Contract: LIC7 is true iff there exists at least on set of 2 consecutive points seperated by K_PTS 
        * (meaning number of points in between) that are a greater distance apart than LENGTH1 
        * If not such 2 consecutive points exist then LIC7 is false
        */
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

        
        Parameters p = new Parameters();
        p.K_PTS = 1;
        p.LENGTH1 = 5.9;
        assertTrue(LIC.LIC7(points1.length, points1, p));
        p.LENGTH1 = 6;
        assertFalse(LIC.LIC7(points1.length, points1, p));
        p.K_PTS = 2;
        p.LENGTH1 = 26;
        assertTrue(LIC.LIC7(points2.length, points2, p));
        p.LENGTH1 = 27;
        assertFalse(LIC.LIC7(points2.length, points2, p));
    }

    @Test
    public void testLIC8() {
        /*
         * Contract: LIC8 returns true if and only if there exists at least one set of
         * three data points
         * separated by exactly A PTS and B PTS
         * consecutive intervening points, respectively, that cannot be contained within
         * or on a circle of
         * radius RADIUS1. The condition is not met when NUMPOINTS < 5.
         * 1 ≤ A PTS, 1 ≤ B PTS
         * A PTS+B PTS ≤ (NUMPOINTS−3)
         */

        Point[] points2 = new Point[] {
                new Point(0, 10),
                new Point(0, 0),
                new Point(-10, 0),
                new Point(0, -1),
                new Point(10, 0)
        };

        Parameters p = new Parameters();
        p.RADIUS1 = 1;
        p.A_PTS = 0;
        p.B_PTS  = 1;

        // Tests that IllegalArgumentException is thrown when A PTS < 1
        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC8(points2.length, points2, p);
        });
        // Tests that IllegalArgumentException is thrown when B PTS < 1
        p.A_PTS = 1;
        p.B_PTS = 0;
        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC8(points2.length, points2, p);
        });

        // Tests that IllegalArgumentException is thrown when A PTS + B PTS > (NUMPOINTS-3)
        p.A_PTS = 2;
        p.B_PTS = 1;
        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC8(points2.length, points2, p);
        });

        // Tests that false is returned when there exists a set of three data points
        // separated by exactly A PTS and B PTS
        // consecutive intervening points, respectively, that can be contained within or
        // on a circle of
        // radius RADIUS1
        p.RADIUS1 = 100;
        p.A_PTS = 1;
        assertFalse(LIC.LIC8(points2.length, points2, p));

        Point[] points3 = new Point[] {
                new Point(0, 10),
                new Point(-10, 0),
                new Point(0, 10),
                new Point(10, 0),
                new Point(0, 10),
        };

        // Tests that false is returned if there exists at least
        // one set of three data points which are not separated by exactly A PTS and B
        // PTS
        // consecutive intervening points, respectively, that cannot be contained within
        // or on a circle of
        // radius RADIUS1.
        p.RADIUS1 = 1;
        assertFalse(LIC.LIC8(points3.length, points3, p));

        // Tests that IllegalArgumentException is thrown when RADIUS1 < 0:
        p.RADIUS1 = -1; 
        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC8(points2.length, points2, p);
        });

        p.RADIUS1 = 1;
        // Tests that true is returned for a valid input
        assertTrue(LIC.LIC8(points2.length, points2, p));
    }

    // LIC9 Unit Tests

    @Test
    public void LIC9_false_when_numPoint_LessThan_5() {
        /* Contract: If numPoints < 5, returns false (invalid input) */

        int numPoints = 4;
        Point[] points = new Point[] {
                new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0)
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
                new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(4, 0)
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
    public void LIC9_false_when_EPSILON_OutOfRange() {
        /* Contract: Given EPSILON outside [0, PI), LIC9 must return FALSE */
        Point[] points = new Point[] {
            new Point(0,0), new Point(1,0), new Point(2,0), new Point(2,1), new Point(3,1)
        };
        int numPoints = points.length;

        /* Case 1: EPSILON < 0 */
        Parameters p1 = new Parameters();
        p1.C_PTS = 1;
        p1.D_PTS = 1;
        p1.EPSILON = -0.1;
        assertFalse(LIC.LIC9(numPoints, points, p1));

        /* Case 2: EPSILON >= 1 */
        Parameters p2 = new Parameters();
        p2.C_PTS = 1;
        p2.D_PTS = 1;
        p2.EPSILON = Math.PI;
        assertFalse(LIC.LIC9(numPoints, points, p2));
    }

    @Test
    public void LIC9_false_When_C_PTSplusD_PTS_GreaterThan_numPoints_minus_three() {
        /*
         * Contract: If C_PTS + D_PTS > numPoints - 3, LIC9 returns false (invalid
         * input)
         */
        int numPoints = 5;

        Point[] points = new Point[] {
                new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(4, 0)
        };

        Parameters p = new Parameters();
        p.C_PTS = 2;
        p.D_PTS = 1;
        p.EPSILON = 0.1;

        assertFalse(LIC.LIC9(numPoints, points, p));
    }

    @Test
    public void LIC9_true_When_AngleOutside_PIPlusMinusEpsilon() {
        /*
         * Contract: LIC9 returns true iff there exists points A,B,C with the required
         * spacing
         * such that the angle at B is < (PI - EPSILON) or > (PI + EPSILON)
         */

        int numPoints = 5;

        Point[] points = new Point[] {
                new Point(0, 0), // 0 = A
                new Point(999, 999), // 1 = C_PTS
                new Point(1, 0), // 2 = B
                new Point(999, 999), // 3 = D_PTS
                new Point(1, 1) // 4 = C
        };

        Parameters p = new Parameters();
        p.C_PTS = 1;
        p.D_PTS = 1;
        p.EPSILON = 0.1;

        assertTrue(LIC.LIC9(numPoints, points, p));
    }

    @Test
    public void LIC9_false_AllEvaluatingAngles_AreWithin_PIPlusMinusEpsilon() {
        /*
         * Contract: If for every valid triple A,B,C (with required spacing),
         * the angle at B lies within [PI - EPSILON, PI + EPSILON], LIC9 returns false
         */

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
    public void LIC9_false_when_SkippedTriples_Where_A_equals_B_or_C_equals_B_IfNoOtherTriplesSatisfies() {
        /*
         * Contract: f A==B or C==B, the angle is undefined and that triple is ignored.
         * If no other valid triple satisfies the angle condition, LIC9 returns false.
         */

        int numPoints = 5;

        Point[] points = new Point[] {
                new Point(1, 0), // 0 = A
                new Point(0, 0), // 1 = C_PTS
                new Point(1, 0), // 2 = B
                new Point(0, 0), // 3 = D_PTS
                new Point(2, 0) // 4 = C
        };

        Parameters p = new Parameters();
        p.C_PTS = 1;
        p.D_PTS = 1;
        p.EPSILON = 0.1;

        assertFalse(LIC.LIC9(numPoints, points, p));
    } 

    @Test
    public void LIC10_false_when_too_few_points() {
        // Contract: LIC10 is false if less than 5 points are being passed
        Point [] points = new Point[] {
                new Point(1, 0),
                new Point(0, 0),
                new Point(1, 0)
        };
        Parameters p = new Parameters();
        p.E_PTS = 2;
        p.F_PTS = 2;
        p.AREA1 = 10;
        assertFalse(LIC.LIC10(3, points, p));
    }

    @Test
    public void LIC10_throws_exception_when_area_too_small() {
        // Contract: LIC10 throws exception if the area is smaller than 0
        Point [] points = new Point[] {
                new Point(1, 0),
                new Point(0, 0),
                new Point(1, 0),
                new Point(1, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(1, 0),
                new Point(1, 0),
                new Point(0, 0)
        };
        Parameters p = new Parameters();
        p.E_PTS = 2;
        p.F_PTS = 2;
        p.AREA1 = -1;
        assertThrows(IllegalArgumentException.class, () -> LIC.LIC10(points.length, points, p));
    }

     @Test
    public void LIC10_throws_exception_when_EPTS_too_small() {
        // Contract: LIC10 throws exception if the EPTS < 1
        Point [] points = new Point[] {
                new Point(1, 0),
                new Point(0, 0),
                new Point(1, 0),
                new Point(1, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(1, 0),
                new Point(1, 0),
                new Point(0, 0)
        };
        Parameters p = new Parameters();
        p.E_PTS = 0;
        p.F_PTS = 2;
        p.AREA1 = 4;
        assertThrows(IllegalArgumentException.class, () -> LIC.LIC10(points.length, points, p));
    }

    @Test
    public void LIC10_throws_exception_when_FPTS_too_small() {
        // Contract: LIC10 throws exception if FPTS < 1
        Point [] points = new Point[] {
                new Point(1, 0),
                new Point(0, 0),
                new Point(1, 0),
                new Point(1, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(1, 0),
                new Point(1, 0),
                new Point(0, 0)
        };
        Parameters p = new Parameters();
        p.E_PTS = 2;
        p.F_PTS = 0;
        p.AREA1 = 4;
        assertThrows(IllegalArgumentException.class, () -> LIC.LIC10(points.length, points, p));
    }

     @Test
    public void LIC10_throws_exception_when_EPTS_plus_FPTS_too_large() {
        // Contract: LIC10 throws exception if EPTS + FPTS > numpoints - 3
        Point [] points = new Point[] {
                new Point(1, 0),
                new Point(0, 0),
                new Point(1, 0),
                new Point(1, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(1, 0),
                new Point(1, 0),
                new Point(0, 0)
        };
        Parameters p = new Parameters();
        p.E_PTS = 5;
        p.F_PTS = 5;
        p.AREA1 = 4;
        assertThrows(IllegalArgumentException.class, () -> LIC.LIC10(points.length, points, p));
    }

    @Test
    public void LIC10_true() {
        /*
        * Contract: LIC10 is true iff there exists at least one set of three data points 
        * separated by exactly E PTS and F PTS consecutive intervening points, respectively,
        * that are the vertices of a triangle with area greater than AREA1.
        * The triangle is (10,0), (20,0), (10, 10) with area > 10
        */
        Point [] points = new Point[] {
                new Point(10, 0),
                new Point(0, 0),
                new Point(20, 0),
                new Point(0, 0),
                new Point(10, 10)
        };
        Parameters p = new Parameters();
        p.E_PTS = 1;
        p.F_PTS = 1;
        p.AREA1 = 10;
        assertTrue(LIC.LIC10(points.length, points, p));
    }

  @Test
    public void testLIC11() {
        /* Contract: LIC11 returns true if and only if there exists at least one set of two data points, 
        (X[i],Y[i]) and (X[j],Y[j]), separated by
        exactly G PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j ) The
        condition is not met when NUMPOINTS < 3.
        1 ≤ G PTS ≤ NUMPOINTS−2 */

        Point[] points1 = new Point[] {
            new Point(1, -1),  
            new Point(0, 0),  
            new Point(-1, 1) 
        };

        Point[] points3 = new Point[] {
            new Point(-1, -1),  
            new Point(0, 0),  
            new Point(1, 1) 
        };

        Point[] points4 = new Point[] {
            new Point(1, -1),  
            new Point(-1, 1),
            new Point(2, 1)  

        };
        Parameters p = new Parameters();

        p.G_PTS = 0;
        // Tests that IllegalArgumentException is thrown when G_PTS < 1
        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC11(points1.length, points1, p);
        });

        p.G_PTS = 2;
        // Tests that IllegalArgumentException is thrown when NUMPOINTS-2 < G_PTS
        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC11(points1.length, points1, p);
        });

        //Tests that false is returned when there does not exist at least one set of two data points, 
        //(X[i],Y[i]) and (X[j],Y[j]), separated by
        //exactly G PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j )
        p.G_PTS = 1;
        assertFalse(LIC.LIC11(points3.length, points3, p));

        //Tests that false is returned if there exists at least one set of two data points, 
        //(X[i],Y[i]) and (X[j],Y[j]), which are not separated by
        //exactly G PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j )
        p.G_PTS = 1;
        assertFalse(LIC.LIC11(points4.length, points4, p));

        //Tests that true is returned for an input which fulfills the contract
        assertTrue(LIC.LIC11(points1.length, points1, p));

    }


    // LIC12 Unit Tests

    @Test
    public void LIC12_true_existsPair_GreaterThan_LENGTH1_and_existsPair_LessThan_LENGTH2() {
        /* Contract: LIC12 returns ture iff:
           A. There exists at least one pair of points (i, i+K_PTS+1) with distance > LENGHT1, and
           B. There exists at least one (possibly different) pair of points (j, j+K_PTS+1) with distance < LENGTH2.
           Both A and B conditions must hold to be satisfied and return true.
         */

        int numPoints = 5;

        Parameters p = new Parameters();
        p.K_PTS = 1;
        p.LENGTH1 = 5.0;
        p.LENGTH2 = 2.0;

        Point[] points = new Point[] {
                new Point(0, 0),
                new Point(0, 0),
                new Point(6, 0),
                new Point(0, 0),
                new Point(7, 0)
        };

        assertTrue(LIC.LIC12(numPoints, points, p));
    }

    @Test
    public void LIC12_false_K_pts_OutOfRange() {
        /* Contract: Given invalid K_PTS (outside [1, numPoints -2]), LIC12 must return false */

        Parameters p = new Parameters();
        p.K_PTS = 0;
        p.LENGTH1 = 1.0;
        p.LENGTH2 = 2.0;

        Point[] points = new Point[] {
            new Point(0,0), new Point(1,0), new Point(2,0)
        };
        int numPoints = points.length;

        assertFalse(LIC.LIC12(numPoints, points, p));
    }

    @Test
    public void LIC12_false_when_onlyGreaterThan_LENGTH1() {
        /* Contract: If the condition A holds (exists pair of points with distance > LENGTH1) 
           but the condition B does not (no pair with distance < LENGTH2), then LIC12 must return false. 
         */

        int numPoints = 5;

        Parameters p = new Parameters();
        p.K_PTS = 1;
        p.LENGTH1 = 5.0;
        p.LENGTH2 = 2.0;

        Point[] points = new Point[] {
                new Point(0, 0),
                new Point(0, 0),
                new Point(6, 0),
                new Point(0, 3),
                new Point(20, 0)
        };

        assertFalse(LIC.LIC12(numPoints, points, p));
    }

    @Test
    public void LIC12_false_whenOnlyLessThan_LENGTH2() {
        /* Contract: 
            If condition B holds (exists pair with distance < LENGTH2) but condition A does not
            (no pair with distance > LENGTH1), then LIC12 must return false.
        */

        int numPoints = 5;

        Parameters p = new Parameters();
        p.K_PTS = 1;
        p.LENGTH1 = 5.0;
        p.LENGTH2 = 2.0;

        Point[] points = new Point[] {
                new Point(0, 0),
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 0),
                new Point(2, 0)
        };

        assertFalse(LIC.LIC12(numPoints, points, p));
    }

    @Test
    public void LIC12_false_when_numPoints_lessThan_3() {
        /* Contract: When numPoints < 3, the condition is not met and must return false. */

        int numPoints = 2;

        Parameters p = new Parameters();
        p.K_PTS = 1;
        p.LENGTH1 = 5.0;
        p.LENGTH2 = 2.0;

        Point[] points = new Point[] {
                new Point(0, 0),
                new Point(1, 0)
        };

        assertFalse(LIC.LIC12(numPoints, points, p));
    }

    @Test
    public void LIC12_false_when_LENGTH2_IsNegative() {
        /* Contract: LENGTH2 must be non-negative. If LENGTH2 < 0 (invalid input), LIC12 must return false */

        int numPoints = 3;

        Parameters p = new Parameters();
        p.K_PTS = 1;
        p.LENGTH1 = 5.0;
        p.LENGTH2 = -1.0;

        Point[] points = new Point[] {
                new Point(0, 0),
                new Point(0, 0),
                new Point(6, 0)
        };

        assertFalse(LIC.LIC12(numPoints, points, p));

    }

    @Test
    public void LIC12_false_when_LENGTH1_is_Negative() {
        /* Contract: Given LENGTH1 < 0, LIC12 must return false */
        Parameters p = new Parameters();
        p.K_PTS = 1;
        p.LENGTH1 = -0.5;
        p.LENGTH2 = 2.0;

        Point[] points = new Point[] {
            new Point(0,0), new Point(1,0), new Point(2,0)
        };
        int numPoints = points.length;

        assertFalse(LIC.LIC12(numPoints, points, p));
    }

    @Test
    public void testLIC13InvalidAPTS() {
        /*
        * Contract: Method throws exception due to A_PTS being invalid since it is sat 0
        */
        Point[] points = new Point[] {
                new Point(2, 2),
                new Point(99, 99),
                new Point(8, 2),
                new Point(100, 100),
                new Point(6, 5),
                new Point(101, 101),
                new Point(5, 11)
        };

        Parameters p = new Parameters();
        p.RADIUS1 = 3;
        p.A_PTS = 0;
        p.B_PTS = 1;
        p.RADIUS2 = 1.5;

        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC13(points.length, points, p);
        });
    }

    @Test
    public void testLIC13InvalidBPTS() {
        /*
        * Contract: Method throws exception due to B_PTS being invalid since it is sat 0
        */
        Point[] points = new Point[] {
                new Point(2, 2),
                new Point(99, 99),
                new Point(8, 2),
                new Point(100, 100),
                new Point(6, 5),
                new Point(101, 101),
                new Point(5, 11)
        };

        Parameters p = new Parameters();
        p.RADIUS1 = 3;
        p.A_PTS = 1;
        p.B_PTS = 0;
        p.RADIUS2 = 1.5;

        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC13(points.length, points, p);
        });
    }

    @Test
    public void testLIC13InvalidRADIUS1() {
        /*
        * Contract: Method throws exception due to RADIUS1 being invalid since it is negative
        */
        Point[] points = new Point[] {
                new Point(2, 2),
                new Point(99, 99),
                new Point(8, 2),
                new Point(100, 100),
                new Point(6, 5),
                new Point(101, 101),
                new Point(5, 11)
        };

        Parameters p = new Parameters();
        p.RADIUS1 = -3;
        p.A_PTS = 1;
        p.B_PTS = 1;
        p.RADIUS2 = 1.5;

        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC13(points.length, points, p);
        });
    }

    @Test
    public void testLIC13InvalidRADIUS2() {
        /*
        * Contract: Method throws exception due to RADIUS2 being invalid since it is negative
        */
        Point[] points = new Point[] {
                new Point(2, 2),
                new Point(99, 99),
                new Point(8, 2),
                new Point(100, 100),
                new Point(6, 5),
                new Point(101, 101),
                new Point(5, 11)
        };

        Parameters p = new Parameters();
        p.RADIUS1 = 3;
        p.A_PTS = 1;
        p.B_PTS = 1;
        p.RADIUS2 = -1.5;

        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC13(points.length, points, p);
        });
    }

    @Test
    public void testLIC13() {
        /*
        * Contract: LIC13 is true iff there exists at least one set of 3 consecutive points seperated by first A_PTS 
        * (meaning number of points in between) and then B_PTS that are not contained by a circle with RADIUS1, and also 
        * a similar set of 3 points with the same seperation that is contained/or on the border of a circle with RADIUS2. 
        * If not these two sets of 3 consecutive points exist then LIC13 is false
        */
        Point[] points = new Point[] {
                new Point(2, 2),
                new Point(99, 99),
                new Point(8, 2),
                new Point(100, 100),
                new Point(6, 5),
                new Point(101, 101),
                new Point(5, 11)
        };

        double radius = Point.circleRadius(points[0], points[2], points[4]);
        double radius2 = Point.circleRadius(points[1], points[3], points[5]);
        double radius3 = Point.circleRadius(points[0], points[2], points[6]);
        System.out.println(radius);
        System.out.println(radius2);
        System.out.println(radius3);

        Parameters p = new Parameters();
        p.RADIUS1 = 3;
        p.A_PTS = 1;
        p.B_PTS = 1;
        p.RADIUS2 = 1.5;
        assertTrue(LIC.LIC13(points.length, points, p));
        p.RADIUS2 = 1.4;
        assertFalse(LIC.LIC13(points.length, points, p));
    }
    
    @Test
    void LIC14_true_triangle_exists_gt_AREA1_and_triangle_exists_lt_AREA2() {
        /*
         * Contract: LIC14 is true iff there exists at least one triplet of points (i, j=i+E_PTS+1, k=i+E_PTS+1+F_PTS+1) that forms a 
         * triangle with area > AREA1 AND there exists at least one other or same triplet of points with area < AREA2.
         * Set E_PTS=1, F_PTS=1, NUMPOINTS=6, AREA1=0.9, AREA2=0.5
         * Make the first triplet i=0, j=2, k=4 with area 1>AREA1 but > AREA2, the second triplet
         * i=1, j=3, k=5 with area 0.25 < AREA1 and < AREA2.
         */
        Parameters p = new Parameters();
        p.E_PTS = 1;
        p.F_PTS = 1;
        p.AREA1 = 0.9;
        p.AREA2 = 0.5;

        Point[] points = new Point[]{
            new Point(0,0),
            new Point(2,0),
            new Point(1,2),
            new Point(2.5,1),
            new Point(1,0),
            new Point(2.5,0)
        };

        assertTrue(LIC.LIC14(points.length, points, p));
    }

    @Test
    void LIC14_false_one_triplet() {
        /*
         * Contract: LIC14 is false when there exists no triplet of points (i, j=i+E_PTS+1, k=i+E_PTS+1+F_PTS+1) that forms a 
         * triangle with area > AREA1 or there does not exist at least one other or same triplet of points with area < AREA2.
         * Set E_PTS=1, F_PTS=1, NUMPOINTS=5, AREA1=1.1, AREA2=0.9
         * Make the first triplet (and only triplet) i=0, j=2, k=4 with area 1, which does not fullfil either of the AREA1 and AREA 2 conditions.
         */
        Parameters p = new Parameters();
        p.E_PTS = 1;
        p.F_PTS = 1;
        p.AREA1 = 1.1;
        p.AREA2 = 0.9;

        Point[] points = new Point[]{
            new Point(0,0),
            new Point(2,0),
            new Point(1,2),
            new Point(2.5,1),
            new Point(1,0)
        };

        assertFalse(LIC.LIC14(points.length, points, p));
    }

    @Test
    void LIC14_false_too_few_points() {
        /*
         * Contract: LIC14 is false when there exists no triplet of points (i, j=i+E_PTS+1, k=i+E_PTS+1+F_PTS+1) that forms a 
         * triangle with area > AREA1 or there does not exist at least one other or same triplet of points with area < AREA2.
         * Set E_PTS=1, F_PTS=1, but use only four points, which triggers the guard clause
         * to flag not enough points and return false (and trivially there exists no such previously mentioned triplet)
         */
        Parameters p = new Parameters();
        p.E_PTS = 1;
        p.F_PTS = 1;
        p.AREA1 = 0.9;
        p.AREA2 = 0.5;

        Point[] points = new Point[]{
            new Point(0,0),
            new Point(2,0),
            new Point(1,2),
            new Point(2.5,1),
        };

        assertFalse(LIC.LIC14(points.length, points, p));
    }

    @Test
    void LIC14_false_on_too_few_points() {
        /*
         * Contract: LIC14 is false when NUMPOINTS < 5
         * Use 4 points to test, with otherwise correct parameter setup.
         */
        Parameters p = new Parameters();
        p.E_PTS = 1;
        p.F_PTS = 1;
        p.AREA1 = 0.9;
        p.AREA2 = -1;

        Point[] points = new Point[]{
            new Point(0,0),
            new Point(2,0),
            new Point(1,2),
            new Point(2.5,1),
        };

        assertFalse(LIC.LIC14(points.length, points, p));
    }

    @Test
    void LIC14_throws_on_negative_area() {
        /*
         * Contract: Negative area is not a correct input.
         * Set negative areas, with otherwise correct parameters and point setup.
         */
        Parameters p = new Parameters();
        p.E_PTS = 1;
        p.F_PTS = 1;
        p.AREA1 = -0.9;
        p.AREA2 = -0.5;

        Point[] points = new Point[]{
            new Point(0,0),
            new Point(2,0),
            new Point(1,2),
            new Point(2.5,1),
            new Point(1,0),
            new Point(2.5,0)
        };

        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC14(points.length, points, p);
        });
    }

    @Test
    void LIC14_throws_on_too_large_E_PTS_and_F_PTS() {
        /*
         * Contract: We require a minimum of E_PTS+F_PTS+3 points to evaluate LIC14.
         * Set E_PTS=F_PTS=2, which requires a minimum of 7 points to evaluate, use
         * 6 points. Otherwise use correct parameters and point setup.
         */
        Parameters p = new Parameters();
        p.E_PTS = 2;
        p.F_PTS = 2;
        p.AREA1 = 0.9;
        p.AREA2 = 0.5;

        Point[] points = new Point[]{
            new Point(0,0),
            new Point(2,0),
            new Point(1,2),
            new Point(2.5,1),
            new Point(1,0),
            new Point(2.5,0)
        };

        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC14(points.length, points, p);
        });
    }

    @Test
    void LIC14_throws_on_too_small_E_PTS_and_F_PTS() {
        /*
         * Contract: E_PTS >= 1 AND F_PTS >= 1.
         * Set E_PTS=F_PTS=0, with otherwise correct parameters and point setup.
         */
        Parameters p = new Parameters();
        p.E_PTS = 0;
        p.F_PTS = 0;
        p.AREA1 = 0.9;
        p.AREA2 = 0.5;

        Point[] points = new Point[]{
            new Point(0,0),
            new Point(2,0),
            new Point(1,2),
            new Point(2.5,1),
            new Point(1,0),
            new Point(2.5,0)
        };

        assertThrows(IllegalArgumentException.class, () -> {
            LIC.LIC14(points.length, points, p);
        });
    }
}
