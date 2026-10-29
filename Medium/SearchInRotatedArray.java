// There is an integer array nums sorted in ascending order (with distinct values).

// Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].

// Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

// You must write an algorithm with O(log n) runtime complexity.

 

// Example 1:

// Input: nums = [4,5,6,7,0,1,2], target = 0
// Output: 4
// Example 2:

// Input: nums = [4,5,6,7,0,1,2], target = 3
// Output: -1
// Example 3:

// Input: nums = [1], target = 0
// Output: -1
 

// Constraints:

// 1 <= nums.length <= 5000
// -104 <= nums[i] <= 104
// All values of nums are unique.
// nums is an ascending array that is possibly rotated.
// -104 <= target <= 104
 



package Medium;

public class SearchInRotatedArray {
        // Compute mid as the halfway point.
        // If nums[mid] is the target, return it.
        // Use nums[l] <= nums[mid] to decide which half is sorted.
        // Check whether the target fits within that sorted half’s endpoints. If yes, keep that half. If not, keep the other one.
            public int search(int[] nums, int target) {
        
        int left =0;
        int right = nums.length-1;
  
        
        while (left <=right ){
              int middle = left + (right-left)/2;
              if (nums[middle]==target){
                return middle;
              }
                //left is sorted 
              if (nums[left]<=nums[middle]){
                    //find if target falls betweeens the sorted array; if it does ok we must adjust this one, if not, we adjust it by moving left to the middle.
                if (nums[left]<=target && target<=nums[middle]){
                    right= middle-1;
                }
                else{
                    left = middle+1;
                }
              }
              // right is sorted
              else{
                if (nums[right]>=target && target>=nums[middle]){
                    left = middle +1;
                }
                else{
                    right = middle -1;
                }
              }
        }
        return -1;
    }
}

