import java.util.HashSet;
import java.util.Set;

public class MaxFrequencyElements {
    public int maxFrequencyElements(int[] nums) {
        //Method 1: Create hashset
        Set<Integer> max = new HashSet<>();
        for (int num: nums){
            max.add(num);
        }
        return max.size();
    }
}
