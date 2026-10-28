// You are given an array nums consisting of positive integers.

// Return the total frequencies of elements in nums such that those elements all have the maximum frequency.

// The frequency of an element is the number of occurrences of that element in the array.

 

// Example 1:

// Input: nums = [1,2,2,3,1,4]
// Output: 4
// Explanation: The elements 1 and 2 have a frequency of 2 which is the maximum frequency in the array.
// So the number of elements in the array with maximum frequency is 4.
// Example 2:

// Input: nums = [1,2,3,4,5]
// Output: 5
// Explanation: All elements of the array have a frequency of 1 which is the maximum.
// So the number of elements in the array with maximum frequency is 5.

import java.util.HashMap;
import java.util.Map;

public class MaxFrequencyElements {
    public int maxFrequencyElements(int[] nums) {
        //Method 1: Create hashset
        Map<Integer,Integer> max = new HashMap<>();
        for (int num: nums){         
            max.put(num, max.getOrDefault(num,0)+1);
        }

        //Iterate through map, and look at the value and see which one has the most value
        int currentMax = 1;
        for (int frequency:max.values()){
            currentMax= Math.max(currentMax, frequency);
        }

        int count=0;
        for (int frequency:max.values()){
            if (frequency==currentMax)
                count++;
        }


        //add up max value times count
        return count*currentMax;
    }
}
