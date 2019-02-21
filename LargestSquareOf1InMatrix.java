package dp;

public class LargestSquareOf1InMatrix {
	/*
	 * Q: Find the length of the largest square of 1's in a given binary matrix.
	 * 
	 * e.g: 00000
	 *      11110
	 *      11110    return 3.
	 *      11100
	 *      11100
	 * */
	
	/*
	 * Algorithm: Dynamic Programming.
	 * (n^2-size matrix has at most n^3 sub-square.)
	 * 
	 * M[i][j] represents the max length square with (i, j) as its right bottom corner.
	 * base case: M[i][0] = A[i][0], M[0][j] = A[0][j].
	 * induction rule: M[i][j] = min(M[i-1][j-1], M[i-1][j], M[i][j-1]) + 1 if A[i][j] = 1
	 * 				   else M[i][j] = 0.
	 * return globalMax eventually.
	 * 
	 * Complexity Analysis:
	 * T: O(n^2)
	 * S: O(n^2)
	 * */
	
	public static int largestSquare(int[][] matrix) {
		// corner case
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int N = matrix.length;
		int[][] dp = new int[N][N];
		for (int i = 0; i < N; ++i) {
			dp[0][i] = matrix[0][i];
			dp[i][0] = matrix[i][0];
		}
		int globalMax = 0;
		for (int i = 1; i < N; ++i) {
			for (int j = 1; j < N; ++j) {
				if (matrix[i][j] == 1) {
					dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
					globalMax = Math.max(globalMax, dp[i][j]);
				} else {
					dp[i][j] = 0;
				}
			}
		}
		return globalMax;
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{0, 0, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 1, 1, 1, 0}, {1, 1, 1, 0, 0}, {1, 1, 1, 0, 0}};
		System.out.println("The length of largest square: " + largestSquare(matrix));
	}
}
