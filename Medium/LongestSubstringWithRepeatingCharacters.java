// Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.

// if no such substring exists, return 0.

 

// Example 1:

// Input: s = "aaabb", k = 3
// Output: 3
// Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
// Example 2:

// Input: s = "ababbc", k = 2
// Output: 5
// Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 

// Constraints:

// 1 <= s.length <= 104
// s consists of only lowercase English letters.
// 1 <= k <= 105

package Medium;

public class LongestSubstringWithRepeatingCharacters {
  public int longestSubstring(String s, int k) {
        return helper(s, 0, s.length(), k);
    }

    // solves for the part of s from index start up to (not including) end
    private int helper(String s, int start, int end, int k) {
        // too short to hold even one letter k times
        if (end - start < k) return 0;

        // step 1: count each letter in this piece
        int[] count = new int[26];
        for (int i = start; i < end; i++) {
            count[s.charAt(i) - 'a']++;
        }

        // step 2: find a wall (a letter that appears fewer than k times)
        for (int i = start; i < end; i++) {
            if (count[s.charAt(i) - 'a'] < k) {
                // step 3: cut here and solve both sides
                int leftBest = helper(s, start, i, k);
                int rightBest = helper(s, i + 1, end, k);
                return Math.max(leftBest, rightBest);
            }
        }

        // no walls found: every letter appears at least k times
        return end - start;
    }
}
