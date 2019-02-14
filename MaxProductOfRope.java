package dp;

public class MaxProductOfRope {
	/*
	 * Q: Given a rope of integer-length n, find a way to get the max product of sub-ropes after m cuts.
	 * 	You must cut at least once. p[0]*p[1]*...*p[m-1] must be the max. m is determined by yourself.
	 * */
	
	/*
	 * Algorithm 1: recursion
	 * Intuitively, a n long rope can have (n-1) places to be cut which means that we have (n-1) choices. Say
	 * we want to cut the right part which is n-i long. So the max product I can obtain is:
	 * max{n-i, maxProd(n-i)}*i. i is from 1 to (n-1), since it's recursion, I need to go to base case for each
	 * time I call maxProd(x).
	 * 
	 * Performance Analysis:
	 * T: O(n!)  ->  O(2^n)
	 * S: O(n) n is the height of the call stack.
	 * */
	public static int maxProdI(int n) {
		// base case
		if (n <= 1)  {
			return 0;
		}
		int maxProd = 1;
		for (int i = 1; i < n; ++i) {
			int best = Math.max(n-i, maxProdI(n-i));
			maxProd = Math.max(maxProd, i * best);
		}
		return maxProd;
	}
	
	/*
	 * Algorithm 2: Dynamic programming (left big segment and right big segment)
	 * M[i] represents the max product of cutting i long rope.
	 * base case: M[1] = 0
	 * induction rule: M[i] = max{j, M[j]} * max{i-j, M[i-j]}, j is within [1, i-1].
	 * return M[n] eventually.
	 * 
	 * Performance Analysis: 
	 * T: O(n^2)
	 * S: O(n)
	 * */
	
	public static int maxProdII(int n) {
		// corner case
		if (n <= 1) {
			return 0;
		}
		int[] M = new int[n + 1];
		M[1] = 0;
		for (int i = 2; i <= n; ++i) {
			int maxProd = 0;
			for (int j = 1; j <= i / 2; ++j)  {
				int cur = Math.max(j, M[j]) * Math.max(i - j, M[i - j]);
				maxProd = Math.max(maxProd, cur);
			}
			M[i] = maxProd;
		}
		return M[n];
	}
	
	/*
	 * Algorithm 3: Dynamic Programming (left big segment + right small segment(not divisible))
	 * Case: maxProd(5) =  case 1: max(4, M[4]) * 1
	 * 					   case 2: max(3, M[3]) * 2
	 * 					   case 3: max(2, M[2]) * 3
	 * 					   case 4: max(1, M[1]) * 4
	 *  base case: M[0]=0,M[1]=0
	 *  induction rule:
	 *  for i in range(n):
	 *  	for j in range(1, i):
	 *  		M[i] = max(M[i], max(i-j, M[i-j]) * j)
	 *  
	 * Performance Analysis:
	 * T: O(n^2)
	 * S: O(n)
	 * */
	
	public static int maxProdIII(int n) {
		// corner case
		if (n <= 1) {
			return 0;
		}
		int[] M = new int[n + 1];
		M[0] = 0;
		M[1] = 0;
		for (int i = 2; i <= n; ++i) {
			M[i] = 0;
			for (int j = 1; j <= i / 2; ++j) {
				M[i] = Math.max(M[i], Math.max(i-j, M[i-j]) * j);
			}
		}
		return M[n];
	}
	
	public static void main(String[] args) {
		int n = 5;
		System.out.println("The max product is: " + maxProdIII(n));
	}
}
