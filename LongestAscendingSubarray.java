package dp;

public class LongestAscendingSubarray {
	/*
	 * Q: Find the length of the longest sub-array in the given array that are all in ascending order.
	 * strictly ascending(not including equal).
	 * */
	
	/*
	 * Algorithm: Dynamic Programming
	 * we make a 1-D array called opt[i] representing the length of sub-array that ends at ith index.
	 * base case:  opt[0] = 1
	 * induction rule: opt[i] = opt[i-1] + 1 if arr[i] > arr[i-1]. opt[i] = 1 o.w.
	 * 
	 * Performance Analysis:
	 * T: O(n)
	 * S: O(n) but since I only need to look back the most previous item, we can make it O(1).
	 * */
	public static int longestAscending(int[] input) {
		// corner case
		if (input == null || input.length == 0) {
			return 0;
		}
		// cur is the most previous item in opt[i]
		int cur = 1, res = 0;
		for (int i = 1; i < input.length; ++i) {
			if (input[i] > input[i - 1]) {
				cur += 1;
				res = Math.max(res, cur);
			} else {
				cur = 1;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] input = {7, 2, 3, 1, 5, 8,9, 6};
		System.out.println("The length of longest ascending sub-array: " + longestAscending(input));
	}
}
