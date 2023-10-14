package ru.potemkin.leetcode.hard;

import java.util.Arrays;

public class PaintingTheWalls2742 {
    public static void main(String[] args) {
        System.out.println(paintWalls(new int[]{1,2,3,2}, new int[]{1,2,3,2}));
        System.out.println(paintWalls(new int[]{2,3,4,2}, new int[]{1,1,1,1}));
    }

    private static int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[][] dp = new int[n+1][n+1];
        for (int i = 1; i < n+1; i++) dp[n][i] = Integer.MAX_VALUE/2;
        for (int i = n-1; i >= 0; i--) {
            for (int j = 1; j < n+1; j++) {
                int paint = cost[i] + dp[i+1][Integer.max(0,j - 1 - time[i])];
                int dontPain = dp[i+1][j];
                dp[i][j] = Integer.min(paint, dontPain);
            }
        }
        return dp[0][n];
    }
}
