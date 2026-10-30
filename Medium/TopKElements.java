// Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

 

// Example 1:

// Input: nums = [1,1,1,2,2,3], k = 2

// Output: [1,2]

// Example 2:

// Input: nums = [1], k = 1

// Output: [1]

// Example 3:

// Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2

// Output: [1,2]

 

// Constraints:

// 1 <= nums.length <= 105
// -104 <= nums[i] <= 104
// k is in the range [1, the number of unique elements in the array].
// It is guaranteed that the answer is unique.
 

// Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.



package Medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKElements {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: count frequencies
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Step 2: min-heap ordered by frequency
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> Integer.compare((freq.get(a)), freq.get(b)));

        // Step 3: keep only the k most frequent in the heap
        for (int num : freq.keySet()) {
            heap.offer(num);
            if (heap.size() > k) {
                heap.poll(); // remove the least frequent
            }
        }

        // Step 4: move the heap contents into the result array
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll();
        }

        return result;
    }
}
