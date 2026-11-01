// There is a directed graph consisting of n nodes numbered from 0 to n - 1 and n directed edges.

// You are given a 0-indexed array edges where edges[i] indicates that there is an edge from node i to node edges[i].

// Consider the following process on the graph:

// You start from a node x and keep visiting other nodes through edges until you reach a node that you have already visited before on this same process.
// Return an array answer where answer[i] is the number of different nodes that you will visit if you perform the process starting from node i.

 

// Example 1:


// Input: edges = [1,2,0,0]
// Output: [3,3,3,4]
// Explanation: We perform the process starting from each node in the following way:
// - Starting from node 0, we visit the nodes 0 -> 1 -> 2 -> 0. The number of different nodes we visit is 3.
// - Starting from node 1, we visit the nodes 1 -> 2 -> 0 -> 1. The number of different nodes we visit is 3.
// - Starting from node 2, we visit the nodes 2 -> 0 -> 1 -> 2. The number of different nodes we visit is 3.
// - Starting from node 3, we visit the nodes 3 -> 0 -> 1 -> 2 -> 0. The number of different nodes we visit is 4.
// Example 2:


// Input: edges = [1,2,3,4,0]
// Output: [5,5,5,5,5]
// Explanation: Starting from any node we can visit every node in the graph in the process.
 

// Constraints:

// n == edges.length
// 2 <= n <= 105
// 0 <= edges[i] <= n - 1
// edges[i] != i

package Hard;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountedVisitedNodesDirectedGraph {

      public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        int[] ans = new int[n];      // 0 means "not computed yet"
        int[] pos = new int[n];      // position in the current path, -1 if not in it
        Arrays.fill(pos, -1);

        for (int start = 0; start < n; start++) {
            if (ans[start] != 0) continue;   // already solved by an earlier walk

            List<Integer> path = new ArrayList<>();
            int cur = start;

            // walk until we hit a node in this path (cycle) or one already solved
            while (ans[cur] == 0 && pos[cur] == -1) {
                pos[cur] = path.size();
                path.add(cur);
                cur = edges.get(cur);
            }

            int end = path.size();

            // case 1: we looped back into this path, so there is a cycle
            if (ans[cur] == 0) {
                int cycleStart = pos[cur];
                int cycleLen = end - cycleStart;
                for (int i = cycleStart; i < end; i++) {
                    ans[path.get(i)] = cycleLen;
                }
                end = cycleStart;   // only the tail remains
            }

            // case 2 (and the tail of case 1): fill backwards
            for (int i = end - 1; i >= 0; i--) {
                int node = path.get(i);
                ans[node] = ans[edges.get(node)] + 1;
            }

        }

        return ans;
    }
    
}
