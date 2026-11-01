// There are an infinite amount of bags on a number line, one bag for each coordinate. Some of these bags contain coins.

// You are given a 2D array coins, where coins[i] = [li, ri, ci] denotes that every bag from li to ri contains ci coins.

// The segments that coins contain are non-overlapping.

// You are also given an integer k.

// Return the maximum amount of coins you can obtain by collecting k consecutive bags.

 

// Example 1:

// Input: coins = [[8,10,1],[1,3,2],[5,6,4]], k = 4

// Output: 10

// Explanation:

// Selecting bags at positions [3, 4, 5, 6] gives the maximum number of coins: 2 + 0 + 4 + 4 = 10.

// Example 2:

// Input: coins = [[1,10,3]], k = 2

// Output: 6

// Explanation:

// Selecting bags at positions [1, 2] gives the maximum number of coins: 3 + 3 = 6.

 

// Constraints:

// 1 <= coins.length <= 105
// 1 <= k <= 109
// coins[i] == [li, ri, ci]
// 1 <= li <= ri <= 109
// 1 <= ci <= 1000
// The given segments are non-overlapping.

package Medium;

import java.util.Arrays;

public class MaximumCoinsFromKConsecutiveBags {
    public long maximumCoins(int[][] coins, int k) {
        // Windows that START at some segment's left end
        long a = solve(coins, k);

        // Mirror the number line so windows that END at a right end become windows that START
        int m = coins.length;
        int[][] mirrored = new int[m][3];
        for (int i = 0; i < m; i++) {
            mirrored[i][0] = -coins[i][1];   // new l = -r
            mirrored[i][1] = -coins[i][0];   // new r = -l
            mirrored[i][2] = coins[i][2];
        }
        long b = solve(mirrored, k);

        return Math.max(a, b);
    }

    private long solve(int[][] coins, int k) {
        Arrays.sort(coins, (x, y) -> Integer.compare(x[0], y[0]));
        int n = coins.length;

        long sum = 0;   // coins from segments fully inside the window (segments i..j-1)
        long best = 0;
        int j = 0;      // first segment not fully inside the window

        for (int i = 0; i < n; i++) {
            long end = (long) coins[i][0] + k - 1;

            // 1. swallow every segment that fits fully inside the window
            while (j < n && coins[j][1] <= end) {
                sum += (long) (coins[j][1] - coins[j][0] + 1) * coins[j][2];
                j++;
            }

            // 2. partial segment cut off by the window's end
            long partial = 0;
            if (j < n && coins[j][0] <= end) {
                partial = (end - coins[j][0] + 1) * coins[j][2];
            }

            best = Math.max(best, sum + partial);

            // 3. window moves right: segment i leaves
            if (j > i) {
                sum -= (long) (coins[i][1] - coins[i][0] + 1) * coins[i][2];
            } else {
                j = i + 1;   // segment i was longer than the window, never added to sum
            }
        }

        return best;
    }


    // Method two reversing the loop.
    public long maximumCoinsTwo(int[][] coins, int k) {
    Arrays.sort(coins, (a, b) -> a[0] - b[0]);
    int n = coins.length;
    long result = 0, currentSum = 0;
    currentSum = 0;
    for(int i=0,j=0;i<n;i++){
        int windowEnd = coins[i][0]+k-1;
        while(j<n && coins[j][1]<=windowEnd){
            currentSum+=(long)(coins[j][1]-coins[j][0]+1)*coins[j][2];
            j++;
        }
        result = Math.max(result,currentSum);
        if(j<n){
            long partial = (long)Math.max(0,windowEnd-coins[j][0]+1)*coins[j][2];
            result = Math.max(result,currentSum+partial);
        }
        currentSum-=(long)(coins[i][1]-coins[i][0]+1)*coins[i][2];
    }

    // Case 2: 
    currentSum = 0;
    for (int i = n - 1, j = n - 1; i >= 0; i--) {
        int windowStart = coins[i][1] - k + 1;
        while (j >= 0 && coins[j][0] >= windowStart) {
            currentSum += (long) (coins[j][1] - coins[j][0] + 1) * coins[j][2];
            j--;
        }
        long partial = 0;
        if (j >= 0) {
            partial = (long) Math.max(
                    0, coins[j][1] - windowStart + 1) * coins[j][2];
        }
        result = Math.max(result, currentSum + partial);
        currentSum -= (long) (coins[i][1] - coins[i][0] + 1) * coins[i][2];
    }
    return result;
    }
}
