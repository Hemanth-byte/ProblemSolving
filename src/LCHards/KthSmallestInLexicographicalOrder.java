package LCHards;

/*
Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].



Example 1:

Input: n = 13, k = 2
Output: 10
Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
Example 2:

Input: n = 1, k = 1
Output: 1


Constraints:

1 <= k <= n <= 109

 */
public class KthSmallestInLexicographicalOrder {
    public int count(int n, long num1, long num2){
        int steps = 0;
        while(num1 <= n){
            steps += Math.min(n+1, num2) - num1;
            num1 *= 10;
            num2 *= 10;
        }
        return steps;
    }
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k --;
        while(k > 0){
            int steps = count(n, curr, curr+1);
            if(steps <= k){
                curr++;
                k -= steps;
            }
            else{
                curr = curr * 10;
                k --;
            }
        }
        return curr;
    }
}
