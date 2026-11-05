// There are n couples sitting in 2n seats arranged in a row and want to hold hands.

// The people and seats are represented by an integer array row where row[i] is the ID of the person sitting in the ith seat. 
// The couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2n - 2, 2n - 1).

// Return the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any two people, then they stand up and switch seats.

 

// Example 1:

// Input: row = [0,2,1,3]
// Output: 1
// Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
// Example 2:

// Input: row = [3,2,0,1]
// Output: 0
// Explanation: All couples are already seated side by side.


package Hard;

public class CouplesHoldingHands {
   public int minSwapsCouples(int[] row) {
        int n = row.length;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) pos[row[i]] = i;

        int swaps = 0;
        for (int i = 0; i < n; i += 2) {
            int partner = row[i] ^ 1; // XOR equvialent:
                                        //int partner;
                                        // if (row[i] % 2 == 0) partner = row[i] + 1;
                                        // else partner = row[i] - 1;
            if (row[i + 1] != partner) {
                int j = pos[partner];       // where the partner currently sits
                int other = row[i + 1];     // person we're displacing

                row[j] = other;
                pos[other] = j;
                row[i + 1] = partner;
                pos[partner] = i + 1;
                swaps++;
            }
        }
        return swaps;

       }    }
