public class Decide {
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