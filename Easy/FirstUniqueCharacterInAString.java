// Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.

 

// Example 1:

// Input: s = "leetcode"

// Output: 0

// Explanation:

// The character 'l' at index 0 is the first character that does not occur at any other index.

// Example 2:

// Input: s = "loveleetcode"

// Output: 2

// Example 3:

// Input: s = "aabb"

// Output: -1

 

// Constraints:

// 1 <= s.length <= 105
// s consists of only lowercase English letters.

import java.util.HashMap;

public class FirstUniqueCharacterInAString {
        public int firstUniqChar(String s) {
        
        // One way is to solve using hashmap; key = letter, value = index; if it already exists then index=-1.
        HashMap<Character,Integer> map = new HashMap<>();
        int count=0;
        for (char c: s.toCharArray()){
            if (map.containsKey(c)) map.put(c, Integer.MAX_VALUE);
            else map.put(c, count);
            count++;
        }
        
        //now look for the minimum value >=0
        int min = Integer.MAX_VALUE;
        for (int a: map.values()){
            min = Math.min(a, min);
        }
        return min==Integer.MAX_VALUE?-1:min;
    }
}
