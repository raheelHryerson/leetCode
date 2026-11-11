# Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.

# Each letter in magazine can only be used once in ransomNote.

 

# Example 1:

# Input: ransomNote = "a", magazine = "b"
# Output: false
# Example 2:

# Input: ransomNote = "aa", magazine = "ab"
# Output: false
# Example 3:

# Input: ransomNote = "aa", magazine = "aab"
# Output: true
 

# Constraints:

# 1 <= ransomNote.length, magazine.length <= 105
# ransomNote and magazine consist of lowercase English letters.

class Solution:
    def canConstruct(self, ransomNote: str, magazine: str) -> bool:
        m = {}
        
        # Create a hashmap of magazine plus count and then check if ransom note can go through . This is O(N+M).
        for char in magazine:
            m[char] = m.get(char, 0)+1

        for char in ransomNote:
            if m.get(char,0)>0:
                m[char]-=1
            else:
                return False

        return True
            