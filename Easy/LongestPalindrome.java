// Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.

// Letters are case sensitive, for example, "Aa" is not considered a palindrome.

 

// Example 1:

// Input: s = "abccccdd"
// Output: 7
// Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
// Example 2:

// Input: s = "a"
// Output: 1
// Explanation: The longest palindrome that can be built is "a", whose length is 1.
 

// Constraints:

// 1 <= s.length <= 2000
// s consists of lowercase and/or uppercase English letters only.

public class LongestPalindrome {
    public int longestPalindrome(String s) {
        int[] count = new int[58];
        for (char c: s.toCharArray()){
            int index = c-'A';
            count[index]++;
        }

        //loop through it again and find the number of times its divisble by 2 each letter
        int length = 0;
        boolean hasOdd = false;
        for (int c : count) {
            length += (c / 2) * 2;   // use every complete pair
            if (c % 2 == 1) hasOdd = true;
        }

        // one leftover odd character can go in the middle
        return hasOdd ? length + 1 : length;
    }
    }
