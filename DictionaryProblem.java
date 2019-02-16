package dp;

import java.util.HashSet;
import java.util.Set;

public class DictionaryProblem {
	/*
	 * Q: Given a dictionary and a string. Tell if the string can be composed by concatenating the words 
	 * from the dictionary.
	 * */
	
	/*
	 * Algorithm: Dynamic programming
	 * left big segment (M[i]) + right small segment (n - i) which we directly tell from the given condition.
	 * M[i] represents if the the sub-string ending at ith index could be composed by concatenating words
	 * 	from the given dictionary.
	 * base case: M[0] = true; 
	 * induction rule: M[i] = M[j] && sub-String (i-j) is a word in the dictionary. j belongs to [0, i], i belongs
	 * 	to [0, n]. M[j] must be true.
	 * return M[n] eventually.
	 * 
	 * Performance Analysis:
	 * T: O(n^2)
	 * S: O(n)
	 * */
	public static boolean canBreak(String input, String[] dict) {
		// corner case
		if (input == null ||  input.length() == 0) {
			return false;
		}
		Set<String> set = loadInSet(dict);
		int N = input.length();
		boolean[] M = new boolean[N  + 1];
		M[0] = true;
		for (int i  = 1; i <= N; ++i)  {
			for (int j = 0; j < i; ++j) {
				if (M[j] && set.contains(input.substring(j, i))) {
					M[i] = true;
					break;
				}
			}
		}
		return M[N];
	}
	
	private static Set<String> loadInSet(String[] dict) {
		Set<String> res = new HashSet<>();
		for (String s: dict) {
			res.add(s);
		}
		return res;
	}
	
	public static void main(String[] args) {
		String[] dict  = {"bob", "cat",  "rob"};
		String input = "bcoabt";
		// bobcatrob bobbob bcoabt
		System.out.println("If input can break: " + canBreak(input, dict));
	}
}
