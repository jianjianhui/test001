
public class MaxSquare {
    public static int maximalSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxLen = 0;

        if (matrix[0][0] == 1 || matrix[rows-1][cols - 1] == 1) {
            return 0;
        }

        // 创建一个dp数组，dp[i][j]表示以(i, j)为右下角的最大正方形的边长
        int[][] dp = new int[rows][cols];

        // 初始化第一行和第一列
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 1) {
                dp[i][0] = 1;
                maxLen = 1;
            }
        }
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 1) {
                dp[0][j] = 1;
                maxLen = 1;
            }
        }

        // 填充dp数组
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 0; // 当前位置为1，不能作为正方形的右下角
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{0,0},{1,0},{0,0}};
        System.out.println(maximalSquare(matrix1)); // 输出 1

        int[][] matrix2 = {{1,0},{0,0},{0,1}};
        System.out.println(maximalSquare(matrix2)); // 输出 0

        int[][] matrix3 = {{0,0,0},{0,0,0},{1,0,0},{0,0,0}};
        System.out.println(maximalSquare(matrix3)); // 输出 2
    }
}