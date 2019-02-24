package dp;

public class LargestSubsquare {
	/*
	 * Q: Given a matrix where every element is either '0' or '1', find the largest sub-square surrounded by '1'.
	 * */
	
	/*
	 * Algorithm: as shown in the codes below.
	 * 
	 * Complexity Analysis:
	 * T: O(n^3)
	 * S: O(n^2)
	 * */
	
	// return the length of the largest square.
	public static int largest(int[][] matrix) {
		// assumptions: matrix is not null, size of M * N
		if (matrix.length ==0 ||matrix[0].length == 0)  {
			return 0;
		}
		int res = 0;
		int M = matrix.length, N = matrix[0].length;
		// the longest left arm length ending at each position in the matrix.
		// left[i][j] is actually mapped to  matrix[i-1][j-1].
		int[][] left = new int[M+1][N+1];
		int[][] up = new int[M+1][N+1];
		for (int i = 0; i < M; ++i) {
			for (int j = 0; j < N; ++j) {
				if (matrix[i][j] == 1) {
					left[i + 1][j + 1] = left[i + 1][j] + 1;
					up[i + 1][j + 1] = up[i][j + 1] + 1;
					// the maximum length of a surrounded by 1 matrix with right bottom
					// position at matrix[i][j] is determined by the min value of
					// left[i+1][j+1] and up[i+1][j+1]
					// and we check one by one all the possible lengths if it can
					// provide the actual matrix.
					// we only need to check the longest left arm length of the right top
					// cell and the longest up arm length of the left bottom cell.
					for (int maxLength =  Math.min(left[i+1][j+1], up[i+1][j+1]); maxLength >= 1; maxLength--) {
						if (left[i+2-maxLength][j+1] >=maxLength &&  up[i+1][j + 2 - maxLength] >= maxLength) {
							res = Math.max(res, maxLength);
							break;
						}
					}
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{1,0,1,1,1}, {1,1,1,1,1}, {1,1,0,1,0}, {1,1,1,1,1}, {1,1,1,0,0}};
		System.out.println("The longest arm length of largest subsquare: " + largest(matrix));
	}
}
