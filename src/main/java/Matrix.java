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
    int[][] matrixArray;

    Matrix(int M, int N) {
        this.M = M;
        this.N = N;

        this.matrixArray = new int[M][N];

        for (int i = 0; i < M;  i++) {
            for (int j = 0; j < N; j++) {
                matrixArray[i][j] = -1;
            }
        }
    }

    @Override
    public String toString() {
        String printString = "";

        for (int i = 0; i < M;  i++) {
            printString += "[";
            for (int j = 0; j < N - 1; j++) {
                printString += matrixArray[i][j] + " ";
            }
            printString += matrixArray[i][N - 1] + "]\n";
        }
        return printString;
    }

}
