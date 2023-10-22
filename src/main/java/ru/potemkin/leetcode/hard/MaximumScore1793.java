package ru.potemkin.leetcode.hard;

public class MaximumScore1793 {
    public static void main(String[] args) {
        MaximumScore1793 starter = new MaximumScore1793();
        System.out.println(starter.maximumScore(new int[]{1,4,3,7,4,5}, 3));
        System.out.println(starter.maximumScore(new int[]{5,5,4,5,4,1,1,1}, 0));
        System.out.println(starter.maximumScore(new int[]{1,1,5,1,1}, 2));
        System.out.println(starter.maximumScore(new int[]{6569,9667,3148,7698,1622,2194,793,9041,1670,1872}, 5));
        System.out.println(starter.maximumScore(new int[]{1,1,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, 2));
    }

    public int maximumScore(int[] nums, int k) {
        int score = solve(nums,k);
        for (int i = 0; i < nums.length/2; i++) {
            int temp = nums[i];
            nums[i] = nums[nums.length-1-i];
            nums[nums.length-1-i] = temp;
        }
        return Integer.max(score, solve(nums, nums.length-1-k));
    }

    private int solve(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        for (int i = k-1; i >=0; i--) {
            if (min > nums[i]) min = nums[i];
            else nums[i] = min;
        }
        min = nums[k];
        for (int i = k+1; i < nums.length; i++) {
            if (min > nums[i]) min = nums[i];
            else nums[i] = min;
        }
        int maxScore = nums[k];
        for (int i = k+1; i < nums.length; i++) {
            int cur = nums[i];
            int leftI = binarySearch(nums,k,cur);
            int curScore = cur*(i - leftI +1);
            if (curScore > maxScore) maxScore = curScore;
        }
        return maxScore;
    }

    private int binarySearch(int[] nums, int end, int num) {
        int start = 0;
        while (start < end) {
            int mid = (start + end)/2;
            if (nums[mid] >= num) end = mid - 1;
            else start = mid + 1;
        }
        return start;
    }
}
