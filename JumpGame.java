package dp;

public class JumpGame {
	/*
	 * Q: Given an array with all non-negative elements. Each integer represents the number of steps a 
	 * person can jump from this index to the right. You are set at the first index originally, tell if 
	 * you can jump to the last index.
	 * */
	
	/*
	 * Algorithm: Dynamic Programming
	 *  M[i] represents if I could jump from ith index to the last index.
	 *  base case: M[N- 1] = true;
	 *  induction rule: M[i] = M[j] && "j + arr[j] >= N-1" j belongs to [i, i + arr[i]]
	 *  return M[0] eventually.
	 * 
	 * Performance Analysis:
	 * T: O(n^2)
	 * S: O(n)
	 * */
	
	public static boolean canReach(int[] game) {
		// assumption: the given integer array is not null and the length is not 0.
		if (game.length == 1) {
			return true;
		}
		int N = game.length;
		boolean[] M = new boolean[N];
		M[N-1] = true;
		for (int i = N - 2; i >= 0; --i) {
			if (i + game[i] >= N - 1) {
				M[i] = true;
			} else {
				for (int j = i + game[i]; j >= 0; --j) {
					if (M[j] && j + game[j] >= N - 1) {
						M[i] = true;
						break;
					}
				}
			}
		}
		return M[0];
	}
	
	public static void main(String[] args) {
		int[] game = {2, 3, 1, 1, 4};
		//3, 2, 1, 0, 4 | 2, 3, 1, 1, 4
		System.out.println("Can reach the last index: " + canReach(game));
	}
}
