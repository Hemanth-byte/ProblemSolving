package LCHards;

/*
Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and return them.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.



Example 1:

Input: nums = [1,2,1,2,6,7,5,1], k = 2
Output: [0,3,5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
Example 2:

Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
Output: [0,2,4]


Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i] < 216
1 <= k <= floor(nums.length / 3)

 */



public class MaximumSumOfNonOverlappingSubarrays {

    public long getVal(int i, int j, long[] prefixSum){
        if(i == 0){
            return prefixSum[j];
        }
        return prefixSum[j] - prefixSum[i-1];
    }

    public long memo(int i, long[] prefixSum, int[] nums, int k, int count, int n, long[][] dp){
        if(i >= n){
            return 0;
        }
        if(dp[i][count] != -1){
            return dp[i][count];
        }
        long ans = memo(i+1, prefixSum, nums, k, count, n, dp);
        if(count < 3 && (i+k-1 < n)){
            ans = Math.max(ans, memo(i+k, prefixSum, nums, k, count+1, n, dp) + getVal(i, i+k-1, prefixSum));
        }
        dp[i][count] = ans;
        return ans;
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n];
        long[][] dp = new long[n][4];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 4; j++){
                dp[i][j] = -1;
            }
        }

        for(int i = 0; i < n; i++){
            if(i == 0){
                prefix[i] = nums[i];
            }
            else{
                prefix[i] = prefix[i-1] + nums[i];
            }
        }
        long ans = memo(0, prefix, nums, k, 0, n, dp);
        int[] ansInd = new int[3];
        long tot = 0;
        int count = 0, i = 0, ind = 0;
        while(ind < 3 && i < n){
            if(i+k-1 < n){
                long y = 0;
                if(i+k < n){
                    y = dp[i+k][count+1];
                }
                if(tot + y + getVal(i, i+k-1, prefix) == ans){
                    ansInd[ind++] = i;
                    tot += getVal(i, i+k-1, prefix);
                    i = i+k;
                    count++;
                }
                else{
                    i++;
                }
            }
            else{
                i++;
            }
        }
        return ansInd;
    }
}
