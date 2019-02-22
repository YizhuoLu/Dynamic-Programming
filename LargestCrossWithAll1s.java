package dp;

public class LargestCrossWithAll1s {
	/*
	 * Q: Given a matrix that contains only 1s and 0s, how to find the largest cross with the same
	 * arm lengths and two arms join at the central point of each other.
	 * */
	
	/*
	 * Algorithm: Dynamic Programming
	 * I will go four direction for each position to find the longest consecutive one's.
	 * for each M_k[i][j] each row, I'll have M_k[i] = M_[k[i-1] + 1 if a[i] = 1. else: a[i] =  0;
	 * Eventually I will get final M[i][j] = min(M_1[i][j], M_1[i][j], M_1[i][j], M_1[i][j])
	 * Return globalMax at last.
	 * 
	 * Complexity Analysis:
	 * T: O(n*m)
	 * S: O(n*m)
	 * */
	
	public static int largestCrossOfOne(int[][] matrix) {
		// Assumptions: matrix is not null, has size of N*M
		int N = matrix.length;
		if (N == 0) {
			return 0;
		}
		int M = matrix[0].length;
		if (M == 0) {
			return 0;
		}
		int[][] leftUp =leftUp(matrix, N,M);
		int[][] rightDown = rightDown(matrix, N, M);
		return merge(leftUp, rightDown,N,M);
	}
	
	private static int merge(int[][] leftUp, int[][] rightDown, int N, int M) {
		int res = 0;
		for (int i = 0; i <N;  ++i) {
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
			for (int j = 0; j <M;++j) {
				if (matrix[i][j] == 1) {
					left[i][j] = getNumber(left, i, j - 1, N, M) + 1;
					up[i][j] = getNumber(up, i - 1, j, N, M)+ 1;
				}
			}
		}
		merge(left, up, N, M);
		return left;
	}
	
	private static int[][] rightDown(int[][] matrix, int N, int M) {
		int[][] right = new int[N][M];
		int[][] down = new int[N][M];
		for (int i = N - 1; i>= 0; --i) {
			for (int j = M - 1; j >= 0; --j) {
				if (matrix[i][j] == 1) {
					right[i][j] = getNumber(right, i, j + 1,N,M) + 1;
					down[i][j] = getNumber(down,i + 1, j, N, M) + 1;
				}
			}
		}
		merge(right, down, N, M);
		return right;
	}
	
	private static int getNumber(int[][] number, int x, int y, int N, int M) {
		if (x <  0 || x >= N  || y <  0 ||  y >= M) {
			return 0;
		}
		return number[x][y];
	}
	
	public static void main(String[] args) {
		int[][] number = {{0, 1, 0, 0}, {1, 1, 1, 1}, {0, 1, 0, 0}, {0, 1, 0, 0}};
		System.out.println("Longest arm length of cross: " + largestCrossOfOne(number));
	}
}
