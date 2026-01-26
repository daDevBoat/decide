import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DecideTest {
    
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
}
