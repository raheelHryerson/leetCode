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


// "abcdaf"
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        //We are going to go through the string and create a sliding window. I will use a hash set and we will slide the window one 
        // to the right if we see a repeating character.

        HashSet<Character> characterList = new HashSet<>();
        int right =-1;
        int left =0;
        int max = 0;
        for (char c: s.toCharArray()){
            right++; //5
            while (characterList.contains(c)){
                left++; //1
                characterList.remove(s.charAt(left));

            }
                characterList.add(c);
                max= Math.max(max, right-left+1);
            }
            return max;
            
        }
    
    
  
        //Have a sliding window
    public int lengthOfLongestSubstringTwo(String s) {
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
