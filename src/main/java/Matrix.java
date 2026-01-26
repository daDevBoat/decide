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

    int M, N;
    Cond[][] matrixArray;

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
                printString += matrixArray[i][j].value + " ";
            }
            printString += matrixArray[i][N - 1].value + "]\n";
        }
        return printString;
    }

}
