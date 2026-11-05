// 397. Integer Replacement
// Medium
// Topics
// premium lock icon
// Companies
// Given a positive integer n, you can apply one of the following operations:

// If n is even, replace n with n / 2.
// If n is odd, replace n with either n + 1 or n - 1.
// Return the minimum number of operations needed for n to become 1.

 

// Example 1:

// Input: n = 8
// Output: 3
// Explanation: 8 -> 4 -> 2 -> 1
// Example 2:

// Input: n = 7
// Output: 4
// Explanation: 7 -> 8 -> 4 -> 2 -> 1
// or 7 -> 6 -> 3 -> 2 -> 1
// Example 3:

//21->22->11->10->5->4->2->1
//21->20->10->5->4->2-1

// Input: n = 4
// Output: 2
 

// Constraints:

// 1 <= n <= 231 - 1


package Medium;

public class IntegerReplacement {
    public int integerReplacement(int n) {
        long num =n;
        int count =0;

        while (num!=1){
            if (num%2==0){
                num/=2;
            }
            else{
                if (num==3) num--; 
                else if ((num+1)%4==0){
                    num+=1;
                }else {
                    num-=1;
                }
            }
            count++;
        }
        return count;

    }
}
