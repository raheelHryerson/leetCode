// You are given two integers m and n. Consider an m x n grid where each cell is initially white. You can paint each cell red, green, or blue. All cells must be painted.

// Return the number of ways to color the grid with no two adjacent cells having the same color. Since the answer can be very large, return it modulo 10^9 + 7.

 

// Example 1:


// Input: m = 1, n = 1
// Output: 3
// Explanation: The three possible colorings are shown in the image above.
// Example 2:


// Input: m = 1, n = 2
// Output: 6
// Explanation: The six possible colorings are shown in the image above.
// Example 3:

// Input: m = 5, n = 5
// Output: 580986
 

// Constraints:

// 1 <= m <= 5
// 1 <= n <= 1000

package Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PaintingAGridWithThreeDifferentColours {
    private static final int MOD = 1_000_000_007;

    // Counts valid colorings for an m x n grid (m <= 5, n <= 1000).
    public int colorTheGrid(int m, int n) {
        int[][] states = generateColumnStates(m);
        int stateCount = states.length;
        int[][] compat = buildCompatibility(states);

        long[] prev = new long[stateCount];
        long[] curr = new long[stateCount];
        for (int i = 0; i < stateCount; i++) prev[i] = 1; // first column

        for (int col = 1; col < n; col++) {
            for (int i = 0; i < stateCount; i++) {
                long ways = prev[i];
                if (ways == 0) continue;
                int[] adj = compat[i];
                for (int j = 0; j < adj.length; j++) {
                    int next = adj[j];
                    curr[next] += ways;
                    if (curr[next] >= MOD) curr[next] %= MOD;
                }
            }
            // roll arrays
            long[] tmp = prev;
            prev = curr;
            curr = tmp;
            for (int i = 0; i < stateCount; i++) curr[i] = 0;
        }

        long total = 0;
        for (long v : prev) {
            total += v;
            if (total >= MOD) total %= MOD;
        }
        return (int) total;
    }

    // Alternate implementation mirroring the hash-based approach you shared.
    public int colorTheGridHash(int m, int n) {
        int maxState = (int) Math.pow(3, m);
        Set<Integer> valid = new HashSet<>();
        int[] dp = new int[maxState];
        for (int s = 0; s < maxState; s++) {
            if (isValidState(s, m)) {
                valid.add(s);
                dp[s] = 1; // first column
            }
        }

        Map<Integer, List<Integer>> compat = new HashMap<>();
        for (int a : valid) {
            for (int b : valid) {
                if (areCompatible(a, b, m)) {
                    compat.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
                }
            }
        }

        for (int col = 1; col < n; col++) {
            int[] nextDp = new int[maxState];
            for (int curr : valid) {
                List<Integer> adj = compat.getOrDefault(curr, List.of());
                for (int prevState : adj) {
                    nextDp[curr] = (int) ((nextDp[curr] + dp[prevState]) % MOD);
                }
            }
            dp = nextDp;
        }

        int ans = 0;
        for (int v : dp) {
            ans = (ans + v) % MOD;
        }
        return ans;
    }

    private int[][] generateColumnStates(int m) {
        int maxStates = (int) Math.pow(3, m);
        int[][] tmp = new int[maxStates][m];
        int[] colors = new int[m];
        int count = dfsBuild(0, m, colors, tmp, 0);
        int[][] states = new int[count][m];
        System.arraycopy(tmp, 0, states, 0, count);
        return states;
    }

    private int dfsBuild(int row, int m, int[] colors, int[][] out, int count) {
        if (row == m) {
            int[] copy = new int[m];
            System.arraycopy(colors, 0, copy, 0, m);
            out[count++] = copy;
            return count;
        }
        for (int c = 0; c < 3; c++) {
            if (row > 0 && colors[row - 1] == c) continue; // avoid vertical duplicates
            colors[row] = c;
            count = dfsBuild(row + 1, m, colors, out, count);
        }
        return count;
    }

    private int[][] buildCompatibility(int[][] states) {
        int n = states.length;
        int[][] temp = new int[n][n];
        int[] sizes = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isCompatible(states[i], states[j])) {
                    temp[i][sizes[i]++] = j;
                }
            }
        }
        int[][] compat = new int[n][];
        for (int i = 0; i < n; i++) {
            compat[i] = new int[sizes[i]];
            System.arraycopy(temp[i], 0, compat[i], 0, sizes[i]);
        }
        return compat;
    }

    private boolean isCompatible(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) return false;
        }
        return true;
    }

    // Quick sanity run for sample cases.
    public static void main(String[] args) {
        PaintingAGridWithThreeDifferentColours solver = new PaintingAGridWithThreeDifferentColours();
        int[][] samples = {
            {1, 1}, // expect 3
            {1, 2}, // expect 6
            {5, 5}  // expect 580986
        };
        for (int[] s : samples) {
            int m = s[0];
            int n = s[1];
            int ans1 = solver.colorTheGrid(m, n);
            int ans2 = solver.colorTheGridHash(m, n);
            System.out.println("m=" + m + ", n=" + n + " => arrayDP=" + ans1 + ", hashDP=" + ans2);
        }
    }

    private boolean isValidState(int state, int rows) {
        int last = -1;
        int x = state;
        for (int i = 0; i < rows; i++) {
            int c = x % 3;
            if (c == last) return false;
            last = c;
            x /= 3;
        }
        return true;
    }

    private boolean areCompatible(int a, int b, int rows) {
        int x = a;
        int y = b;
        for (int i = 0; i < rows; i++) {
            if (x % 3 == y % 3) return false;
            x /= 3;
            y /= 3;
        }
        return true;
    }
}
