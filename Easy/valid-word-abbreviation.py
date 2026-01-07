# A string can be shortened by replacing any number of non-adjacent, non-empty substrings with their lengths (without leading zeros).

# For example, the string "implementation" can be abbreviated in several ways, such as:

# "i12n" -> ("i mplementatio n")
# "imp4n5n" -> ("imp leme n tatio n")
# "14" -> ("implementation")
# "implemetation" -> (no substrings replaced)
# Invalid abbreviations include:

# "i57n" -> (i mplem entatio n, adjacent substrings are replaced.)
# "i012n" -> (has leading zeros)
# "i0mplementation" (replaces an empty substring)
# You are given a string named word and an abbreviation named abbr, return true if abbr correctly abbreviates word, otherwise return false.

# A substring is a contiguous non-empty sequence of characters within a string.

# Example 1:

# Input: word = "apple", abbr = "a3e"

# Output: true
# Example 2:

# Input: word = "international", abbr = "i9l"

# Output: false
# Example 3:

# Input: word = "abbreviation", abbr = "abbreviation"

# Output: true
# Constraints:

# 1 <= word.length <= 100
# word is made up of only lowercase English letters.
# 1 <= abbr.length <= 100
# abbr is made up of lowercase English letters and digits.
# All digit-only substrings of abbr fit in a 32-bit integer.


