package dp;

public class LargestSubarraySum {
	/*
	 * Q: Given an unsorted integer array, find the sub-array that has the greatest sum and return it.
	 * */
	
	/*
	 * Algorithm: Dynamic Programming
	 * M[i] represents the greatest sum from 0th index until the ith index.
	 * base case: M[0] = A[0].
	 * induction rule: M[i] = M[i-1] + A[i] if: M[i-1] >= 0, else: M[i] = A[i].
	 * 
	 * Performance Analysis:
	 * T: O(n)
	 * S: O(n)
	 * */
	
	public static int largestSubarraySum(int[] input) {
		// assumption: input is not null and its length is not zero
		int N = input.length;
		if (N == 1) {
			return input[0];
		}
		int[] M = new int[N];
		M[0] = input[0];
		int globalMax = M[0];
		for (int i = 1; i < N; ++i) {
			if (M[i - 1] >= 0) {
				M[i] = M[i-1] + input[i];
			} else {
				M[i] = input[i];
			}
			globalMax = Math.max(globalMax, M[i]);
		}
		return globalMax;
	}
	
	/*
	 * Follow-up 1: how to optimize space complexity from O(n) to O(1)
	 * */
	public static int largestSubarraySumII(int[] input) {
		int N = input.length;
		int curMax = input[0];
		int globalMax = curMax;
		for (int i = 0; i < N; ++i) {
			curMax = curMax >= 0 ? curMax + input[i] : input[i];
			// curMax = Math.max(curMax, globalMax);
			globalMax = Math.max(globalMax, curMax);
		}
		return globalMax;
	}
	
	/*
	 * Follow-up 2: how to return the left-right border of the solution?
	 * 
	 * Algorithm: 
	 * 	we need to keep track of curLeft / curRight and globalLeft / globalRight.
	 * 1. if lastMax < 0, we make curLeft =  curRight = i
	 * 	else: curRight++
	 * 2. we only update globalLeft / globalRight when we update globalMax.
	 * 
	 * Performance Analysis:
	 * T: O(n)
	 * S: O(1)
	 * */
	
	public static int[] findLargestSubarray(int[] input) {
		int N = input.length;
		int curMax = input[0];
		int curLeft = 0, curRight = 0, globalMax = curMax, globalLeft = 0, globalRight = 0;
		for (int i = 0; i < N; ++i) {
			if (curMax < 0) {
				curMax = input[i];
				curLeft = curRight = i;
			} else {
				curMax = curMax + input[i];
				curRight++;
			}
			if (globalMax < curMax) {
				globalMax = curMax;
				globalLeft = curLeft;
				globalRight = curRight;
			}
		}
		return new int[] {globalLeft, globalRight};
	}
	
	public static void main(String[] args) {
		int[] input = {1, 2, 4, -1, -2, 10, -1, -100, -1, 10, 20};
		// 1, 2, 4, -1, -2, 10, -1, -100, -1, 10, 20
		// System.out.println("The largest subarray's sum is: " + largestSubarraySumII(input));
		int[] res = findLargestSubarray(input);
		System.out.println("The border of largest subarray are respectively: " +  res[0] + "," + res[1]);
	}
}
