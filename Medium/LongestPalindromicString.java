// Given a string s, return the longest palindromic substring in s.

 

// Example 1:

// Input: s = "babad"
// Output: "bab"
// Explanation: "aba" is also a valid answer.
// Example 2:

// Input: s = "cbbd"
// Output: "bb"
 

// Constraints:

// 1 <= s.length <= 1000
// s consist of only digits and English letters.

package Medium;

public class LongestPalindromicString {
public String longestPalindrome(String s) {
    int start = 0;
    int maxLen = 0;

    for (int i = 0; i < s.length(); i++) {
        int len1 = expand(s, i, i);       // odd length, center is one character
        int len2 = expand(s, i, i + 1);   // even length, center is between two characters
        int len = Math.max(len1, len2);

        if (len > maxLen) {
            maxLen = len;
            start = i - (len - 1) / 2;
        }
    }
    return s.substring(start, start + maxLen);
}

private int expand(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
        left--;
        right++;
    }
    return right - left - 1;
}
}
