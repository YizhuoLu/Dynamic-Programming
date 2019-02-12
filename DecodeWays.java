package bnsf;

public class DecodeWays {
	/*
	 * Q: A message containing letters from A-Z is being encoded 
	 * to numbers using the following mapping:
	 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26
	 *   Given a non-empty string containing only digits, determine the 
	 * total number of ways to decode it.
	 * 
	 * Algorithm: Dynamic Programming
	 *  Say the number of N, we set up an (N +1) array.
	 * 	base case: M[0] =1, M[1] =1.
	 *  induction rule: if 10*a(n-1) + a(n) <= 26 M[n] = M[n-1] + M[n-2]
	 *  				else: M[n] = M[n-1]
	 *  
	 * T: O(n)
	 * S: O(n)
	 * */
	
	public static int decodeWays(String s) {
		// corner case
		if (s.length() == 0) {
			return 0;
		}
		if (s.length() == 1 && s.equals("1")) {
			return 0;
		}
		int n = s.length();
		int[] opt = new int[n + 1];
		opt[0] = 1;
		opt[1] =1;
		for (int i = 2; i <= n; ++i) {
			if (10 * (s.charAt(i-2) - '0') + s.charAt(i - 1) - '0' <= 26) {
				opt[i] = opt[i - 1] + opt[i - 2];
			} else {
				opt[i] = opt[i - 1];
			}
		}
		return opt[n];
	}
	
	public static void main(String[] args) {
		String s = "123";
		System.out.println(decodeWays(s));
	}
}
