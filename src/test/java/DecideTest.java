import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecideTest {
    
    @Test
    public void testMatrix() {
        Matrix m = new Matrix(5, 5);
        System.out.println(m);
        m.updateElement(2, 2, Cond.NOTUSED);
        m.updateElement(1, 1, Cond.ORR);
        System.out.println(m);
        System.out.println("fetched (2, 2):" + m.getElement(2, 2));
    }

    void matrix_eval_line_false() {
        // Contract: Matrix_eval_line returns false iff at least one the elements in a row are NOT Cond.TRUE
        Matrix m = new Matrix(1, 6);

        for (int i = 0; i < 6; i++) {
            m.updateElement(0, i, Cond.TRUE);
        }
        m.updateElement(0, 2, Cond.FALSE);
        assertFalse(m.evalLine(0));
    }

    @Test
    void matrix_eval_line_true() {
        // Contract: Matrix_eval_line returns true iff all the elements in a row are Cond.TRUE
        Matrix m = new Matrix(1, 6);

        for (int i = 0; i < 6; i++) {
            m.updateElement(0, i, Cond.TRUE);
        }
        assertTrue(m.evalLine(0));
    }

    //CMV Unit Test
    @Test
    public void CMV_is_Vector_15_And_Only_True_Or_False() {
        /* Contract: Given a set of input points and parameters, calling Decide.CMV(numPoints, points, p) 
           shall return a 15×1 matrix. Every entry CMV[i,0] must be either TRUE or FALSE. */
        Point[] points = new Point[] {
            new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0), new Point(4,0)
        };

        int numPoints = points.length;
        Parameters p = new Parameters();
        Matrix cmv = Decide.CMV(numPoints, points, p);

        assertEquals(15, cmv.M);
        assertEquals(1, cmv.N);

        for(int i = 0; i < 15; i++){
            Cond v = cmv.getElement(i, 0);
            assertTrue(v == Cond.TRUE || v == Cond.FALSE); 
        }
    }

    //PUM Unit Test
    @Test
    public void PUM_builds_expected_matrix_entries() {
         /* Contract: Given a CMV vector (15x1) with TRUE/FALSE values and a 15x15 LCM initialized to NOTUSED, ANDD, or ORR,
           shall produce a 15x15 matrix such that:
           - All diagonal entries PUM[i, i] are TRUE.
           - Any entry whose connector is NOTUSED results in TRUE.
           - For entries with connectors ANDD, PUM[i, j] = TRUE iff both corresponding CMV values are TRUE, otherwise FALSE.
           - For entries with connectors ORR, PUM[i, j] = TRUE iff at least one corresponding CMV value is TRUE, otherwise FALSE.
         */

        Matrix CMV = new Matrix(15, 1);
        CMV.updateElement(0, 0, Cond.TRUE);
        CMV.updateElement(1, 0, Cond.TRUE);
        CMV.updateElement(2, 0, Cond.FALSE);
        CMV.updateElement(3, 0, Cond.FALSE);
        CMV.updateElement(4, 0, Cond.TRUE);
        CMV.updateElement(5, 0, Cond.TRUE);
        CMV.updateElement(6, 0, Cond.TRUE);
        CMV.updateElement(7, 0, Cond.TRUE);
        CMV.updateElement(8, 0, Cond.FALSE);
        CMV.updateElement(9, 0, Cond.TRUE);
        CMV.updateElement(10, 0, Cond.FALSE);
        CMV.updateElement(11, 0, Cond.FALSE);
        CMV.updateElement(12, 0, Cond.TRUE);
        CMV.updateElement(13, 0, Cond.TRUE);
        CMV.updateElement(14, 0, Cond.FALSE);

        Matrix LCM = new Matrix(15, 15);

        //All Initialized values set to NOTUSED to be true by default.
        for(int i = 0; i < 15; i++){
            for(int j= 0; j < 15; j++){
                LCM.updateElement(i, j, Cond.NOTUSED);
            }
        }

        //We overwrite some entries to check ANDD/ORR
        LCM.updateElement(0, 2, Cond.ANDD);
        LCM.updateElement(1, 2, Cond.ORR);
        LCM.updateElement(2, 0, Cond.ANDD);
        LCM.updateElement(2, 1, Cond.ORR);
        LCM.updateElement(6, 5, Cond.ANDD);
        LCM.updateElement(5, 6, Cond.ANDD);
        LCM.updateElement(10, 11, Cond.ORR);
        LCM.updateElement(11, 10, Cond.ORR);

        Matrix PUM = Decide.PUM(CMV, LCM);
        //diagonal 
        assertEquals(Cond.TRUE, PUM.getElement(5, 5));

        //NOTUSED -> True
        assertEquals(Cond.TRUE, PUM.getElement(0, 1));

        //ANDD -> True
        assertEquals(Cond.TRUE, PUM.getElement(6, 5));

        //ANDD -> False
        assertEquals(Cond.FALSE, PUM.getElement(0, 2));

        //ORR -> True
        assertEquals(Cond.TRUE, PUM.getElement(1, 2));

        //ORR -> False
        assertEquals(Cond.FALSE, PUM.getElement(10, 11));

        //Symmetric check
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                assertEquals(PUM.getElement(i, j), PUM.getElement(j, i));
            }
        }
    }

    @Test
    void FUV_logic_true() {
        /*
        * The FUV function should return a vector with all elements set to Cond.TRUE iff all elements of the 
        * PUM are Cond.TRUE 
        * */
        Matrix PUV = new Matrix(3, 1);
        Matrix PUM = new Matrix(3, 3);
        Matrix FUV = new Matrix(3, 1);

        for (int i = 0; i < 3; i++) {
            PUV.updateElement(i, 0, Cond.TRUE);
            FUV.updateElement(i, 0, Cond.TRUE);
            for (int j= 0; j < 3; j++) {
                PUM.updateElement(i, j, Cond.TRUE);
            }
        }

        Matrix actual = Decide.FUV(PUM, PUV);
        for (int i = 0; i < 3; i++) {
            assertEquals(FUV.getElement(i, 0), actual.getElement(i, 0));
        }
    }

    @Test
    void FUV_logic_false_columns() {
        /*
        * The FUV function should return a vector with all elements set to Cond.FALSE iff all elements of the 
        * PUM are Cond.FALSE 
        * */
        Matrix PUV = new Matrix(3, 1);
        Matrix PUM = new Matrix(3, 3);
        Matrix FUV = new Matrix(3, 1);

        for (int i = 0; i < 3; i++) {
            PUV.updateElement(i, 0, Cond.TRUE);
            FUV.updateElement(i, 0, Cond.FALSE);
            for (int j= 0; j < 3; j++) {
                PUM.updateElement(i, j, Cond.FALSE);
            }
        }

        Matrix actual = Decide.FUV(PUM, PUV);
        for (int i = 0; i < 3; i++) {
            assertEquals(FUV.getElement(i, 0), actual.getElement(i, 0));
        }
    }

    @Test
    void FUV_logic_mixed_columns_with_PUV_false() {
        /*
        * The FUV function should return a vector like [Cond.FALSE, Cond.TRUE, Cond.FALSE] iff all elements of the 
        * PUM are Cond.FALSE, and the PUV is set to Cond.TRUE in every position, 
        * except for index 1 where it has value Cond.FALSE.
        * PUV = [Cond.TRUE, Cond.FALSE, Cond.TRUE]
        * */
        Matrix PUV = new Matrix(3, 1);
        Matrix PUM = new Matrix(3, 3);
        Matrix FUV = new Matrix(3, 1);

        for (int i = 0; i < 3; i++) {
            PUV.updateElement(i, 0, Cond.TRUE);
            FUV.updateElement(i, 0, Cond.FALSE);
            for (int j= 0; j < 3; j++) {
                PUM.updateElement(i, j, Cond.FALSE);
            }
        }

        PUV.updateElement(1, 0, Cond.FALSE);
        FUV.updateElement(1, 0, Cond.TRUE);

        Matrix actual = Decide.FUV(PUM, PUV);
        for (int i = 0; i < 3; i++) {
            assertEquals(FUV.getElement(i, 0), actual.getElement(i, 0));
        }
    }

    @Test
    public void testTrueCaseDecide() {
        Parameters p = new Parameters();
        p.LENGTH1 = 0; //Best for all LICS
        p.RADIUS1 = 0; //Best for all LICS
        p.EPSILON = 0; //Best for all LICS
        p.AREA1 = 0; //Best for all LICs
        p.Q_PTS = 5; // The one in LIC4 testcase
        p.QUADS = 2; // Same for LIC4
        p.DIST = 3.0; //Same for LIC6
        p.N_PTS = 3; //Same for LIC6
        p.K_PTS = 1; //same for LIC7 and LIC12
        p.A_PTS = 1; //Same for LIC8 and LIC 13
        p.B_PTS = 1; //Same for LIC8 and LIC 13
        p.C_PTS = 1; //Same for LIC9
        p.D_PTS = 1; //Same for LIC9
        p.E_PTS = 1; //Same for LIC 10 and 14
        p.F_PTS = 1; //Same for LIC 10 and 14
        p.G_PTS = 1; //Same for LIC 11
        p.LENGTH2 = 10000;
        p.RADIUS2 = 10000;
        p.AREA2 = 10000;

        Point[] points = new Point[] {
            //True for LIC0 witch LENGTH1:4
            new Point(0, 0), new Point(3, 4), 
            // True for LIC1 
            new Point(2, 2),
            new Point(8, 2),
            new Point(6, 5),
            //True for LIC2 
            new Point(1, 1),
            new Point(1, 1),
            new Point(-2, -2),
            new Point(-2, -1),
            new Point(-3, -1),
            //True for LIC3 
            new Point(0.0, 0.0),
            new Point(4.0, 0.0),
            new Point(0.0, 3.0),
            //True for LIC4
            new Point(0, 0),
            new Point(1, 0),
            new Point(-1, 0),
            new Point(0, -2),
            new Point(4, 3),
            //True for LIC5
            new Point(2, 0),
            new Point(1, 1),
            // True for LIC 6
            new Point(0, 0),
            new Point(1, 0),
            new Point(2, 0),
            new Point(1, 1),
            new Point(2, 2),
            new Point(1, 1),
            new Point(0, 3),
            new Point(1, 2),
            new Point(6, 0),
            new Point(1, -1),
            //True for LIC7
            new Point(2, 2),
            new Point(5, 5),
            new Point(8, 2),
            //True for LIC 8
            new Point(0, 10),
            new Point(0, 0),
            new Point(-10, 0),
            new Point(0, -1),
            new Point(10, 0),
            //True for LIC 9
            new Point(0, 0), // 0 = A
            new Point(999, 999), // 1 = C_PTS
            new Point(1, 0), // 2 = B
            new Point(999, 999), // 3 = D_PTS
            new Point(1, 1), // 4 = C
            //True for LIC 10
            new Point(10, 0),
            new Point(0, 0),
            new Point(20, 0),
            new Point(0, 0),
            new Point(10, 10),
            //True for LIC 11
            new Point(1, -1),  
            new Point(0, 0),  
            new Point(-1, 1),
            //True for LIC 12
            new Point(0, 0),
            new Point(0, 0),
            new Point(6, 0),
            new Point(0, 0),
            new Point(7, 0),
            //True for LIC 13
            new Point(2, 2),
            new Point(100, 100),
            new Point(8, 2),
            new Point(101, 101),
            new Point(6, 5),
            new Point(99, 99),
            new Point(5, 11),
            //True for LIC 14
            new Point(0,0),
            new Point(2,0),
            new Point(1,2),
            new Point(2.5,1),
            new Point(1,0),
            new Point(2.5,0)
        };
        //Implement valid LCM
        Matrix LCM = new Matrix(15, 15);
        for(int i = 0; i < 15; i++){
            for(int j= 0; j < 15; j++){
                LCM.updateElement(i, j, Cond.ANDD);
            }
        }

        //Implement valid PUV
        Matrix PUV = new Matrix(15, 1);
        for (int i = 0; i < 15; i++) {
            PUV.updateElement(i, 0, Cond.TRUE);
        }

        assertTrue(Decide.DECIDE(points.length, points, p, LCM, PUV));
    }

    @Test
    public void testFalseCaseDecide() {
        /* Contract: Decide.DECIDE(int numPoints, Point[] points, 
        Parameters p, Matrix LCM, boolean[] PUV)
        should return true if and only if FUV[i] is true for all i = 0,1,...,14 */

        Parameters p = new Parameters();
        p.LENGTH1 = 0; //Best for all LICS
        p.RADIUS1 = 100000000; //Worst for LIC 1,8,13
        p.EPSILON = 0; //Best for all LICS
        p.AREA1 = 0; //Best for all LICs
        p.Q_PTS = 5; // The one in LIC4 testcase
        p.QUADS = 2; // Same for LIC4
        p.DIST = 3.0; //Same for LIC6
        p.N_PTS = 3; //Same for LIC6
        p.K_PTS = 1; //same for LIC7 and LIC12
        p.A_PTS = 1; //Same for LIC8 and LIC 13
        p.B_PTS = 1; //Same for LIC8 and LIC 13
        p.C_PTS = 1; //Same for LIC9
        p.D_PTS = 1; //Same for LIC9
        p.E_PTS = 1; //Same for LIC 10 and 14
        p.F_PTS = 1; //Same for LIC 10 and 14
        p.G_PTS = 1; //Same for LIC 11
        p.LENGTH2 = 10000;
        p.RADIUS2 = 10000;
        p.AREA2 = 10000;

        Point[] points = new Point[] {
            //True for LIC0 with LENGTH1:4
            new Point(0, 0), new Point(3, 4), 
            // False for LIC1 
            new Point(2, 2),
            new Point(8, 2),
            new Point(6, 5),
            //True for LIC2 
            new Point(1, 1),
            new Point(1, 1),
            new Point(-2, -2),
            new Point(-2, -1),
            new Point(-3, -1),
            //True for LIC3 
            new Point(0.0, 0.0),
            new Point(4.0, 0.0),
            new Point(0.0, 3.0),
            //True for LIC4
            new Point(0, 0),
            new Point(1, 0),
            new Point(-1, 0),
            new Point(0, -2),
            new Point(4, 3),
            //True for LIC5
            new Point(2, 0),
            new Point(1, 1),
            // True for LIC 6
            new Point(0, 0),
            new Point(1, 0),
            new Point(2, 0),
            new Point(1, 1),
            new Point(2, 2),
            new Point(1, 1),
            new Point(0, 3),
            new Point(1, 2),
            new Point(6, 0),
            new Point(1, -1),
            //True for LIC7
            new Point(2, 2),
            new Point(5, 5),
            new Point(8, 2),
            //False for LIC 8
            new Point(0, 10),
            new Point(0, 0),
            new Point(-10, 0),
            new Point(0, -1),
            new Point(10, 0),
            //True for LIC 9
            new Point(0, 0), 
            new Point(999, 999), 
            new Point(1, 0), 
            new Point(999, 999), 
            new Point(1, 1), 
            //True for LIC 10
            new Point(10, 0),
            new Point(0, 0),
            new Point(20, 0),
            new Point(0, 0),
            new Point(10, 10),
            //True for LIC 11
            new Point(1, -1),  
            new Point(0, 0),  
            new Point(-1, 1),
            //True for LIC 12
            new Point(0, 0),
            new Point(0, 0),
            new Point(6, 0),
            new Point(0, 0),
            new Point(7, 0),
            //False for LIC 13
            new Point(2, 2),
            new Point(100, 100),
            new Point(8, 2),
            new Point(101, 101),
            new Point(6, 5),
            new Point(99, 99),
            new Point(5, 11),
            //True for LIC 14
            new Point(0,0),
            new Point(2,0),
            new Point(1,2),
            new Point(2.5,1),
            new Point(1,0),
            new Point(2.5,0)
        };

        Matrix LCM = new Matrix(15, 15);
        for (int i=0; i<15 ;i++){
            for (int j=0; j<15 ;j++){
                LCM.updateElement(i, j, Cond.ANDD);
            }
        }

        Matrix PUV = new Matrix(15, 1);
        for (int i = 0; i < PUV.N; i++) {
            PUV.updateElement(i, 0, Cond.TRUE);
        }


        //The DECIDE function should return false because
        // LIC 1,8,13 should be false due to 
        //extremely high RADIUS1, which means some PUM elements should
        //be false, and since all PUV elements are true it implies that
        //the PUM rows which contains atleast one false element will
        //lead to the corresponding row in FUV being false, which 
        //means the DECIDE function should return false.
        assertFalse(Decide.DECIDE(points.length, points, p, LCM, PUV));
    }
    @Test
    public void testTrueCaseSomeLICsFalseDecide() {
        /*
        * Contract: Decide.DECIDE(int numPoints, Point[] points, 
        * Parameters p, Matrix LCM, boolean[] PUV)
        * should return true if and only if FUV[i] is true for all i = 0,1,...,14

        * Set high RADIUS1 and high AREA1 to fail some LICs, then use ORR and PUV to generate
        * true launch anyway.
        */

        Parameters p = new Parameters();
        p.LENGTH1 = 0; //Best for all LICS
        p.RADIUS1 = 100000000; //Worst for LIC 1,8,13
        p.EPSILON = 0; //Best for all LICS
        p.AREA1 = 10000000; //Best for all LICs
        p.Q_PTS = 5; // The one in LIC4 testcase
        p.QUADS = 2; // Same for LIC4
        p.DIST = 3.0; //Same for LIC6
        p.N_PTS = 3; //Same for LIC6
        p.K_PTS = 1; //same for LIC7 and LIC12
        p.A_PTS = 1; //Same for LIC8 and LIC 13
        p.B_PTS = 1; //Same for LIC8 and LIC 13
        p.C_PTS = 1; //Same for LIC9
        p.D_PTS = 1; //Same for LIC9
        p.E_PTS = 1; //Same for LIC 10 and 14
        p.F_PTS = 1; //Same for LIC 10 and 14
        p.G_PTS = 1; //Same for LIC 11
        p.LENGTH2 = 10000;
        p.RADIUS2 = 10000;
        p.AREA2 = 10000;

        Point[] points = new Point[] {
            //True for LIC0 with LENGTH1:4
            new Point(0, 0), new Point(3, 4), 
            // False for LIC1 
            new Point(2, 2),
            new Point(8, 2),
            new Point(6, 5),
            //True for LIC2 
            new Point(1, 1),
            new Point(1, 1),
            new Point(-2, -2),
            new Point(-2, -1),
            new Point(-3, -1),
            //True for LIC3 
            new Point(0.0, 0.0),
            new Point(4.0, 0.0),
            new Point(0.0, 3.0),
            //True for LIC4
            new Point(0, 0),
            new Point(1, 0),
            new Point(-1, 0),
            new Point(0, -2),
            new Point(4, 3),
            //True for LIC5
            new Point(2, 0),
            new Point(1, 1),
            // True for LIC 6
            new Point(0, 0),
            new Point(1, 0),
            new Point(2, 0),
            new Point(1, 1),
            new Point(2, 2),
            new Point(1, 1),
            new Point(0, 3),
            new Point(1, 2),
            new Point(6, 0),
            new Point(1, -1),
            //True for LIC7
            new Point(2, 2),
            new Point(5, 5),
            new Point(8, 2),
            //False for LIC 8
            new Point(0, 10),
            new Point(0, 0),
            new Point(-10, 0),
            new Point(0, -1),
            new Point(10, 0),
            //True for LIC 9
            new Point(0, 0), 
            new Point(999, 999), 
            new Point(1, 0), 
            new Point(999, 999), 
            new Point(1, 1), 
            //True for LIC 10
            new Point(10, 0),
            new Point(0, 0),
            new Point(20, 0),
            new Point(0, 0),
            new Point(10, 10),
            //True for LIC 11
            new Point(1, -1),  
            new Point(0, 0),  
            new Point(-1, 1),
            //True for LIC 12
            new Point(0, 0),
            new Point(0, 0),
            new Point(6, 0),
            new Point(0, 0),
            new Point(7, 0),
            //False for LIC 13
            new Point(2, 2),
            new Point(100, 100),
            new Point(8, 2),
            new Point(101, 101),
            new Point(6, 5),
            new Point(99, 99),
            new Point(5, 11),
            //True for LIC 14
            new Point(0,0),
            new Point(2,0),
            new Point(1,2),
            new Point(2.5,1),
            new Point(1,0),
            new Point(2.5,0)
        };

        //Implement valid LCM
        Matrix LCM = new Matrix(15, 15);
        for(int i = 0; i < 15; i++){
            for(int j= 0; j < 15; j++){
                LCM.updateElement(i, j, Cond.ORR);
            }
        }

        //Implement valid PUV
        Matrix PUV = new Matrix(15, 1);
        for (int i = 0; i < 15; i++) {
            PUV.updateElement(i, 0, Cond.TRUE);
        }
        // Ignore the ones that get False in some element on some row in PUM
        PUV.updateElement(1, 0, Cond.FALSE); 
        PUV.updateElement(3, 0, Cond.FALSE);
        PUV.updateElement(8, 0, Cond.FALSE);
        PUV.updateElement(10, 0, Cond.FALSE);
        PUV.updateElement(13, 0, Cond.FALSE);
        PUV.updateElement(14, 0, Cond.FALSE);

        assertTrue(Decide.DECIDE(points.length, points, p, LCM, PUV));
    }

}