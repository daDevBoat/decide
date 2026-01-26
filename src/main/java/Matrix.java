public class Matrix {
    /*
    Values and their meaning for this project
    -1 -> Only inititalised never sat
    0 -> false
    1 -> true
    2 -> ORR
    3 -> ANDD
    4 -> NOTUSED
    
    */

    public final int M, N;
    private final Cond[][] matrixArray;

    Matrix(int M, int N) {
        this.M = M;
        this.N = N;

        this.matrixArray = new Cond[M][N];

        for (int i = 0; i < M;  i++) {
            for (int j = 0; j < N; j++) {
                matrixArray[i][j] = Cond.INIT;
            }
        }
    }

    @Override
    public String toString() {
        String printString = "";

        for (int i = 0; i < M;  i++) {
            printString += "[";
            for (int j = 0; j < N - 1; j++) {
                printString += String.format("%-9s", matrixArray[i][j]);
            }
            printString += matrixArray[i][N - 1] + "]\n";
        }
        return printString;
    }

    public void updateElement(int m, int n, Cond c) {
        matrixArray[m][n] = c;
    }

    public Cond getElement(int m, int n) {
        return matrixArray[m][n];
    }

}
