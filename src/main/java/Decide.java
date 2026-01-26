public class Decide {

    public static boolean DECIDE(int numPoints, Point[] points, Parameters p, Matrix LCM, boolean[] PUV) {

        Matrix CMV = CMV(numPoints, points, p);
        Matrix PUM = PUM(CMV, LCM);
        boolean FUV = FUV(PUM, PUV);

        // return launch();

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
}