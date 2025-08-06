public class MatrixCalculator {

    public static int[][] add(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][j] = A[i][j] + B[i][j];

        return result;
    }

    public static int[][] multiply(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < cols; i++)
            for (int j = 0; j < rows; j++)
                result[i][j] = matrix[j][i];

        return result;
    }

    public static int[] findMinMax(int[][] matrix) {
        int min = matrix[0][0];
        int max = matrix[0][0];

        for (int[] row : matrix) {
            for (int val : row) {
                if (val > max) max = val;
                if (val < min) min = val;
            }
        }

        return new int[]{min, max};
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] A = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] B = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] C = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        System.out.println("矩陣 A:");
        printMatrix(A);

        System.out.println("矩陣 B:");
        printMatrix(B);

        System.out.println("A + B:");
        printMatrix(add(A, B));
        System.out.println("A x C:");
        printMatrix(multiply(A, C));
        System.out.println("A 的轉置:");
        printMatrix(transpose(A));
        int[] minMax = findMinMax(A);
        System.out.println("A 的最大值：" + minMax[1]);
        System.out.println("A 的最小值：" + minMax[0]);
    }
}