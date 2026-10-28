// Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

// The testcases will be generated such that the answer is unique.

 

// Example 1:

// Input: s = "ADOBECODEBANC", t = "ABC"
// Output: "BANC"
// Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
// Example 2:

// Input: s = "a", t = "a"
// Output: "a"
// Explanation: The entire string s is the minimum window.
// Example 3:

// Input: s = "a", t = "aa"
// Output: ""
// Explanation: Both 'a's from t must be included in the window.
// Since the largest window of s only has one 'a', return empty string.
 

// Constraints:

// m == s.length
// n == t.length
// 1 <= m, n <= 105
// s and t consist of uppercase and lowercase English letters.
package Hard;

public class MinumumWindowSubstring {
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) return "";

        int[] need = new int[128];          // ASCII covers upper/lowercase letters
        for (char c : t.toCharArray()) 
            need[c]++;

        int missing = n;                    // chars of t still needed (with duplicates)
        int left = 0;
        int bestStart = 0, bestLen = Integer.MAX_VALUE;

        for (int right = 0; right < m; right++) {
            char r = s.charAt(right);
            if (need[r] > 0) 
                missing--;     // this char was genuinely needed
            need[r]--;                      // surplus chars go negative

            while (missing == 0) {          // window is valid, so shrink it
                int len = right - left + 1;
                if (len < bestLen) {
                    bestLen = len;
                    bestStart = left;
                }

                char l = s.charAt(left);
                need[l]++;
                if (need[l] > 0) 
                    missing++; // we dropped a required char
                left++;
            }
        }

        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + bestLen);
    }
}