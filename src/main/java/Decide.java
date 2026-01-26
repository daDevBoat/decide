public class Decide {

    public static boolean DECIDE(int numPoints, Point[] points, Parameters p, Matrix LCM, boolean[] PUV) {


        return true;

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
    
    public static void main(String[] args) {
        System.out.println("Build was successful. Hi from Decide!");
    }

    public static Matrix fuv_logic(Matrix PUM, Matrix PUV) {
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
    
}