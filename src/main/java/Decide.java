public class Decide {

    public static boolean DECIDE(int numPoints, Point[] points, Parameters p, Matrix LCM, Matrix PUV) {
        /* Guard clause */  
        if (numPoints < 2) return false;      
        
        Matrix CMV = CMV(numPoints, points, p);
        System.out.println("---CMV---");
        System.out.println(CMV.toString());
        Matrix PUM = PUM(CMV, LCM);
        System.out.println("---PUM---");
        System.out.println(PUM.toString());
        Matrix FUV = FUV(PUM, PUV);
        System.out.println("---FUV---");
        System.out.println(FUV.toString());

        boolean allTrue = true;
        for (int i = 0; i < FUV.M; i++) {
            if (FUV.getElement(i, 0) == Cond.FALSE) {
                allTrue = false;
            }
        }
        System.out.println(allTrue ? "YES" : "NO");
        return allTrue;

    }

    public static Matrix CMV(int numPoints, Point[] points, Parameters p) {

        Matrix CMV = new Matrix(15, 1);

        CMV.updateElement(0, 0, LIC.LIC0(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);
        CMV.updateElement(1, 0, LIC.LIC1(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);
        CMV.updateElement(2, 0, LIC.LIC2(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);
        CMV.updateElement(3, 0, LIC.LIC3(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);
        CMV.updateElement(4, 0, LIC.LIC4(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);
        CMV.updateElement(5, 0, LIC.LIC5(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);
        CMV.updateElement(6, 0, LIC.LIC6(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);
        CMV.updateElement(7, 0, LIC.LIC7(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);
        CMV.updateElement(8, 0, LIC.LIC8(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);
        CMV.updateElement(9, 0, LIC.LIC9(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);
        CMV.updateElement(10, 0, LIC.LIC10(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);
        CMV.updateElement(11, 0, LIC.LIC11(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);
        CMV.updateElement(12, 0, LIC.LIC12(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);
        CMV.updateElement(13, 0, LIC.LIC13(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);
        CMV.updateElement(14, 0, LIC.LIC14(numPoints, points, p) ? Cond.TRUE : Cond.FALSE);

        return CMV;
        
    }

    public static Matrix PUM(Matrix CMV, Matrix LCM) {
        Matrix PUM = new Matrix(15, 15);

        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){

                if(i == j){
                    PUM.updateElement(i, j, Cond.TRUE);
                }
                else if(LCM.getElement(i, j) == Cond.NOTUSED){
                    PUM.updateElement(i, j, Cond.TRUE);
                }
                else if(LCM.getElement(i, j) == Cond.ANDD){
                    PUM.updateElement(i, j, (CMV.getElement(i, 0) == Cond.TRUE && CMV.getElement(j,0) == Cond.TRUE) ? Cond.TRUE : Cond.FALSE);
                }
                else if(LCM.getElement(i, j) == Cond.ORR){
                    PUM.updateElement(i, j, (CMV.getElement(i, 0) == Cond.TRUE || CMV.getElement(j,0) == Cond.TRUE) ? Cond.TRUE : Cond.FALSE); 
                }
            }
        }

        return PUM;
    }
    
    public static Matrix FUV(Matrix PUM, Matrix PUV) {
        Matrix FUV = new Matrix(PUM.M, 1);

        for (int i = 0; i < PUM.M; i++) {
            // this LIC does not matter
            if (PUV.getElement(i, 0) == Cond.FALSE) {
                FUV.updateElement(i, 0, Cond.TRUE);
                continue;
            }
            // check the whole row of the PUM
            if (PUM.evalLine(i)) {
                FUV.updateElement(i, 0, Cond.TRUE);
            } else {
                FUV.updateElement(i, 0, Cond.FALSE);
            }
        }
        return FUV;
    }

    public static void main(String[] args) {
        System.out.println("Build was successful. Hi from Decide!");
    }
    
}