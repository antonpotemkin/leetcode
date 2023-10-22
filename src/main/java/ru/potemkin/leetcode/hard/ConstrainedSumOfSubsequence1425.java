package ru.potemkin.leetcode.hard;

import java.util.PriorityQueue;
import java.util.Queue;

public class ConstrainedSumOfSubsequence1425 {

    public static void main(String[] args) {
        var starter = new ConstrainedSumOfSubsequence1425();
        System.out.println(starter.constrainedSubsetSum(new int[]{10, 2, -10, 5, 20}, 2));
        System.out.println(starter.constrainedSubsetSum(new int[]{-1, -2, -3}, 1));
        System.out.println(starter.constrainedSubsetSum(new int[]{10, -2, -10, -5, 20}, 2));
    }

    public int constrainedSubsetSum(int[] nums, int k) {
        Queue<int[]> heap = new PriorityQueue<>((a,b) -> b[0] - a[0]);
        int ans = nums[0];
        heap.add(new int[]{nums[0], 0});
        for (int i = 1; i < nums.length; i++) {
            while (i - heap.peek()[1] > k) heap.remove();
            int cur = nums[i] + Integer.max(0, heap.peek()[0]);
            ans = Integer.max(ans, cur);
            heap.add(new int[]{cur,i});
        }
        return ans;
    }

    int[] memo;

    // not optimal  approach
    public int constrainedSubsetSumV2(int[] nums, int k) {
        memo = new int[nums.length];
        //dp[i] = nums[i] + Max(0,dp[i-k],...dp[i-1])
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int cur = dp(nums, k, i);
            System.out.println("cur = " + cur + "for i = " + i);
            if (cur > max) {
                max = cur;
            }
        }
        return max;
    }

    private int dp(int[] nums, int k, int i) {
        if (memo[i] != Integer.MIN_VALUE) {
            return memo[i];
        }
        int current = nums[i];
        int prevMax = 0;
        for (int j = i - k; j <= i - 1; j++) {
            if (j >= 0) {
                prevMax = Integer.max(prevMax, dp(nums, k, j));
            }
        }
        current = current + prevMax;

        memo[i] = current;
        return current;
    }


}
