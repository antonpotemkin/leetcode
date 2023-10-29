package ru.potemkin.leetcode.medium;

public class LongestPalindromSubstring5 {
    public static void main(String[] args) {
        var starter = new LongestPalindromSubstring5();
        System.out.println(starter.longestPalindrome("babad"));
        System.out.println(starter.longestPalindrome("cbbd"));
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] isPalindroms = new boolean[n][n];
        int[] ans = new int[2];
        for (int i = 0; i < n; i++)
            isPalindroms[i][i] = true;
        for (int i = 0; i < n - 1; i++)
            if (s.charAt(i) == s.charAt(i + 1)) {
                isPalindroms[i][i + 1] = true;
                ans[0] = i;
                ans[1] = i + 1;
            }
        for (int diff = 2; diff < n; diff++) {
            for (int i = 0; i < n - diff; i++) {
                int j = i + diff;
                if (s.charAt(i) == s.charAt(j) && isPalindroms[i+1][j-1]) {
                    isPalindroms[i][j] = true;
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }

        return s.substring(ans[0], ans[1]+1);
    }
}
