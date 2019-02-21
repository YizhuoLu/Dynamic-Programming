package dp;

public class LongestConsecutiveOnes {
	/*
	 * Q: Find the length of the longest consecutive one's in a given array (only contains 0 or 1).
	 * */
	
	/*
	 * Algorithm: Dynamic programming
	 *  M[i] represents the length of consecutive one's ending at ith index.
	 *  base case: M[0] = A[0]
	 *  induction rule: M[i] = M[i-1] + 1 if A[i] = 1, else M[i] = 0.
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(n) can be optimized to O(1)
	 * */
	
	public static int longestConsecutive(int[] arr) {
		// corner case
		if (arr.length == 1) {
			return arr[0];
		}
		int N= arr.length;
		int[] M = new int[N];
		M[0] = arr[0];
		int globalMax = 0;
		for (int i = 1; i < N; ++i) {
			if (arr[i] == 1) {
				M[i] = M[i - 1] + 1;
				globalMax =  Math.max(globalMax, M[i]);
			} else {
				M[i] = 0;
			}
		}
		return globalMax;
	}
	/*
	 * Optimization: space complexity -> O(1)
	 * */
	public static int longestConsecutiveII(int[] arr) {
		// corner case
		if (arr.length == 1) {
			return arr[0];
		}
		int N= arr.length;
		int cur = arr[0];
		int globalMax = 0;
		for (int i = 1; i < N; ++i) {
			if (arr[i] == 1) {
				cur = cur + 1;
				globalMax =  Math.max(globalMax, cur);
			} else {
				cur = 0;
			}
		}
		return globalMax;
	}
	
	public static void main(String[] args) {
		int[] arr = {0, 0,1,1,0,0,0,1,1,1,1,0,0,1,1,1,1,1,1,1};
		System.out.println("The length of longest consecutive '1's is: " + longestConsecutiveII(arr));
	}
}
