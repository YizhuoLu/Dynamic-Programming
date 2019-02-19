package dp;

public class EditDistance {
	/*
	 * Q: Given two strings of alphanumeric characters, determine the minimum number of replace, delete,
	 * and insert operation needed to transform one string into another.
	 * */
	
	/*
	 * Algorithm 1: recursion
	 * we call editDistance(s1, s2)
	 * 	for the first character, if it's different, we can do:
	 * 1. replace --> editDistance(s1.substring(1), s2.substring(1)) + 1;
	 * 2. delete --> editDistance(s1.substring(1), s2) + 1
	 * 3. insert --> editDistance(s1, s2.substring(1)) + 1
	 * base case: if s1.length = 0, return s2.length(). if s2.length() = 0, return s1.length().
	 * 
	 * Complexity Analysis:
	 * T: O(3^(m+n)) m and n is s1, s2's length respectively.
	 * S: O(max(m,n)) the height of the call stack.
	 * */
	public static int editDistance(String a, String b) {
		// base case
		if (a.length() == 0) return b.length();
		if (b.length() == 0) return a.length();
		// if the first characters are same
		if (a.charAt(0) == b.charAt(0)) return editDistance(a.substring(1), b.substring(1));
		// replace
		int replace = editDistance(a.substring(1), b.substring(1)) + 1;
		// delete
		int delete = editDistance(a.substring(1), b) + 1;
		// insert
		int insert = editDistance(a, b.substring(1)) + 1;
		return Math.min(replace, Math.min(delete, insert));
	}
	
	/*
	 * Algorithm 2: Dynamic Programming
	 * 	M[i][j] represents the minimum distance to transform the first i characters in s1 to the first j
	 * characters of s2.
	 * 	base case: M[0][0] = 0, M[0][j]=j, M[i][0]=i.
	 * 	induction rule: M[i][j] = M[i-1][j-1] if s1[i-1]=s2[j-1]
	 * 					else: M[i][j] = min(M[i-1][j-1], M[i][j-1], M[i-1][j]) + 1
	 * Complexity Analysis:
	 * T: O(m*n)
	 * S: O(m*n) can be optimized to O(n)
	 * */
	public static int editDistanceII(String s1, String s2) {
		/*
		 * Assumption: s1 and s2 are both not null
		 * use M[i][j] to represent substring(0,i) in s1, substring(0, j) in s2.
		 * */
		int r = s1.length(), c = s2.length();
		int[][] M = new int[r + 1][c + 1];
		for (int i = 0; i <= r; ++i) {
			for (int j = 0; j <= c; ++j) {
				if (i == 0) {
					M[i][j] = j;
				} else if (j == 0) {
					M[i][j] = i;
				} else if (s1.charAt(i - 1) ==  s2.charAt(j - 1)) {
					M[i][j] = M[i - 1][j - 1];
				} else {
					M[i][j] = Math.min(M[i - 1][j -  1], Math.min(M[i - 1][j], M[i][j - 1])) + 1;
				}
			}
		}
		return M[r][c];
	}
	
	public static void main(String[] args) {
		String a = "asdf";
		String b = "sghj";
		System.out.println("Minimum edit distance is: " + editDistanceII(a, b));
	}
}
