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
