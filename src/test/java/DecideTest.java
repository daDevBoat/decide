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
        Matrix m = new Matrix(1, 6);

        for (int i = 0; i < 6; i++) {
            m.updateElement(0, i, Cond.TRUE);
        }
        m.updateElement(0, 2, Cond.FALSE);
        assertFalse(m.evalLine(0));
    }

    @Test
    void matrix_eval_line_true() {
        Matrix m = new Matrix(1, 6);

        for (int i = 0; i < 6; i++) {
            m.updateElement(0, i, Cond.TRUE);
        }
        assertTrue(m.evalLine(0));
    }

    //PUM Unit Test
    @Test
    public void PUMTest() {

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
        LCM.updateElement(5, 6, Cond.ORR);
        LCM.updateElement(6, 5, Cond.ANDD);
        LCM.updateElement(10, 11, Cond.ORR);
        LCM.updateElement(11, 10, Cond.ANDD);

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
    }

    @Test
    void FUV_logic_true() {
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

        Matrix actual = Decide.fuv_logic(PUM, PUV);
        for (int i = 0; i < 3; i++) {
            assertEquals(FUV.getElement(i, 0), actual.getElement(i, 0));
        }
    }

    @Test
    void FUV_logic_false_columns() {
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

        Matrix actual = Decide.fuv_logic(PUM, PUV);
        for (int i = 0; i < 3; i++) {
            assertEquals(FUV.getElement(i, 0), actual.getElement(i, 0));
        }
    }

    @Test
    void FUV_logic_mixed_columns_with_PUV_false() {
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

        Matrix actual = Decide.fuv_logic(PUM, PUV);
        for (int i = 0; i < 3; i++) {
            assertEquals(FUV.getElement(i, 0), actual.getElement(i, 0));
        }
    }
}
