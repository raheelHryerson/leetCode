// # A string can be shortened by replacing any number of non-adjacent, non-empty substrings with their lengths (without leading zeros).

// # For example, the string "implementation" can be abbreviated in several ways, such as:

// # "i12n" -> ("i mplementatio n")
// # "imp4n5n" -> ("imp leme n tatio n")
// # "14" -> ("implementation")
// # "implemetation" -> (no substrings replaced)
// # Invalid abbreviations include:

// # "i57n" -> (i mplem entatio n, adjacent substrings are replaced.)
// # "i012n" -> (has leading zeros)
// # "i0mplementation" (replaces an empty substring)
// # You are given a string named word and an abbreviation named abbr, return true if abbr correctly abbreviates word, otherwise return false.

// # A substring is a contiguous non-empty sequence of characters within a string.

// # Example 1:

// # Input: word = "apple", abbr = "a3e"

// # Output: true
// # Example 2:

// # Input: word = "international", abbr = "i9l"

// # Output: false
// # Example 3:

// # Input: word = "abbreviation", abbr = "abbreviation"

// # Output: true
// # Constraints:

// # 1 <= word.length <= 100
// # word is made up of only lowercase English letters.
// # 1 <= abbr.length <= 100
// # abbr is made up of lowercase English letters and digits.
// # All digit-only substrings of abbr fit in a 32-bit integer.

class ValidWordAbbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0; // Pointer for word
        int j = 0; // Pointer for abbr

        while (i<word.length() && j<abbr.length()){
            if (word.charAt(i) == abbr.charAt(j)){
                i++;
                j++;
            } else if (Character.isDigit(abbr.charAt(j))){
                if (abbr.charAt(j)=='0')
                    return false;
                else if (j+1 < abbr.length() && Character.isDigit(abbr.charAt(j+1))){
                    //check edge case of 100
                
                    if (j+2 < abbr.length() && Character.isDigit(abbr.charAt(j+2)) && Integer.parseInt(String.valueOf(abbr.charAt(j+2)))==0){
                        i+= Integer.parseInt(abbr.substring(j, j+3));
                        j+=3;
                    }
                    else{
                        i+= Integer.parseInt(abbr.substring(j, j+2));
                        j+=2;
                    }
                }
                else{
                    i+= Integer.parseInt(String.valueOf(abbr.charAt(j)));
                    j++;
                }
            }
            else {
                return false;
        }
    }

            

        return i == word.length() && j == abbr.length(); // Both pointers should reach the end
    }
}