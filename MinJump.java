package dp;

public class MinJump {
	/*
	 * Q: Given a input array in which each item represents the number of steps you can jump.
	 * Find the minimum steps that you need to jump to the last index given that you are at the first
	 * index at first.
	 * */
	
	/*
	 * Algorithm: Dynamic Programming
	 * M[i] represents the minimum step we need to jump from i to the last index.
	 * base case: M[N-1] = 0
	 * induction rule: M[i] = 1 + min{M[j]} where j = i + 1, i+2, ..., i+A[i]
	 * 
	 * Performance Analysis:
	 * T: O(n^2)
	 * S: O(n)
	 * */
	
	public static int minJump(int[] game) {
		// assumption: game is not null and its length is not zero
		if (game.length == 1) {
			return 0;
		}
		int N = game.length;
		int[] M = new int[game.length];
		M[N - 1] = 0;
		for (int i = N - 2; i >= 0; --i) {
			M[i] = Integer.MAX_VALUE;
			for (int j = i + 1; j <= i + game[i]; ++j) {
				M[i] = Math.min(M[i], 1 + M[j]);
			}
		}
		return M[0];
	}
	
	public static void main(String[] args) {
		int[] game = {2, 3, 1, 1, 4};
		System.out.println("The min step to jump:" + minJump(game));
	}
}
