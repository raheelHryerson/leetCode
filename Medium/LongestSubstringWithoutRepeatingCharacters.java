// Given a string s, find the length of the longest substring without duplicate characters.

 
// Example 1:

// Input: s = "abcabcbb"
// Output: 3
// Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
// Example 2:

// Input: s = "bbbbb"
// Output: 1
// Explanation: The answer is "b", with the length of 1.
// Example 3:

// Input: s = "pwwkew"
// Output: 3
// Explanation: The answer is "wke", with the length of 3.
// Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 

// Constraints:

// 0 <= s.length <= 105
// s consists of English letters, digits, symbols and spaces.


package Medium;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
//Have a sliding window
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0;
        int max = 0;

        for (int right = 0; right < s.length(); right++) {
            // if s[right] is already in the window, shrink from the left until it's gone
            while (window.contains(s.charAt(right))) {
                window.remove(s.charAt(left));
                left++;
            }

            window.add(s.charAt(right));
            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}
