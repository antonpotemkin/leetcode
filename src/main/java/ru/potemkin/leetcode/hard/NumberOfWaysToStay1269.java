package ru.potemkin.leetcode.hard;

public class NumberOfWaysToStay1269 {
    private int[][] memo;
    private int modulo = (int) Math.pow(10,9) + 7;
    public static void main(String[] args) {
        NumberOfWaysToStay1269 number = new NumberOfWaysToStay1269();
        System.out.println(number.numWays(3, 2));
        System.out.println(number.numWays(2, 4));
        System.out.println(number.numWays(4, 2));
        System.out.println(number.numWays(27, 7));
        System.out.println(number.numWays(430, 148488));
    }

    private int numWays(int steps, int arrLen) {
        arrLen = Integer.min(steps, arrLen);
        memo = new int[arrLen][steps+1];
        for (int i = 0; i < arrLen; i++)
            for (int j = 0; j < steps+1; j++)
                memo[i][j] = -1;
        return dp(0, steps, arrLen);
    }

    private int dp(int currentPos, int steps, int arrLen) {
        if (currentPos == 0 && steps == 0) return 1;
        if (steps == 0) return 0;
        if (memo[currentPos][steps] != -1) return memo[currentPos][steps];
        int res = dp(currentPos, steps - 1, arrLen);
        if (currentPos > 0)
            res = (res + dp(currentPos - 1, steps - 1, arrLen))% modulo;
        if (currentPos < arrLen - 1)
            res = (res + dp(currentPos + 1, steps - 1, arrLen))% modulo;
        memo[currentPos][steps] = res;
        return res;
    }
}
