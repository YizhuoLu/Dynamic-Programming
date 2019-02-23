package dp;

public class LargestXInMatrix {
	/*
	 * Q: Given a Matrix that contains only 0 and 1. Find and return arm length of the largest 'X' of '1's.
	 * */
	
	/*
	 * Algorithm: Dynamic Programming
	 * 1. run longest consecutive '1's in left up, left down, right down, right up four directions.
	 * 2. merge M[i][j] = min (4 table's same position)
	 * 3. find and return the max in M[i][j].
	 * 
	 * Complexity Analysis:
	 * T: O(n^2)
	 * S: O(n^2)
	 * */
	
	public static int largestX(int[][] matrix) {
		// Assumption: matrix is valid that it has rows and columns.
		// N >= 0  and  M >= 0.
		int N = matrix.length;
		if (N == 0) {
			return 0;
		}
		int M = matrix[0].length;
		if (M == 0) {
			return 0;
		}
		int[][] leftUp = leftUp(matrix, N, M);
		int[][]  rightDown = rightDown(matrix, N, M);
		return merge(leftUp, rightDown, N, M);
	}
	
	private static int merge(int[][] leftUp, int[][] rightDown, int N, int M) {
		int res = 0;
		for (int i = 0; i<  N; ++i) {
			for (int j = 0; j < M; ++j) {
				leftUp[i][j] = Math.min(leftUp[i][j], rightDown[i][j]);
				res = Math.max(res, leftUp[i][j]);
			}
		}
		return res;
	}
	
	private static int[][] leftUp(int[][] matrix, int N, int M) {
		int[][] left = new int[N][M];
		int[][] up = new int[N][M];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (matrix[i][j] == 1) {
					// leftDown and rightDown
					left[i][j] = getNumber(left, i - 1, j + 1, N, M) + 1;
					up[i][j] = getNumber(up, i - 1, j - 1, N, M) + 1;
				}
			}
		}
		merge(left, up, N, M);
		return left;
	}
	
	private static int[][] rightDown(int[][] matrix, int N, int M) {
		int[][] right = new int[N][M];
		int[][] down = new int[N][M];
		for (int i = N - 1; i >= 0;  --i) {
			for (int j = M - 1; j >= 0; --j) {
				if (matrix[i][j] == 1) {
					// leftUp and rightUp
					right[i][j]  = getNumber(right, i + 1, j + 1, N, M) + 1;
					down[i][j] = getNumber(down, i + 1, j - 1, N, M) + 1;
				}
			}
		}
		merge(right, down, N, M);
		return right;
	}
	
	private static int getNumber(int[][] num, int x, int y, int N, int M) {
		if (x < 0 || x >= N || y < 0 || y >= M) {
			return  0;
		}
		return num[x][y];
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}, {0, 1, 0, 0}};
		System.out.println("Largest X's arm length: " + largestX(matrix));
	}
}
