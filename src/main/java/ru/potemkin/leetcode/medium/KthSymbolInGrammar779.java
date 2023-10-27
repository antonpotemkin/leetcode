package ru.potemkin.leetcode.medium;

import java.util.Arrays;

public class KthSymbolInGrammar779 {
    public static void main(String[] args) {
        var starter = new KthSymbolInGrammar779();
        System.out.println(starter.kthGrammar(1,1));
        System.out.println(starter.kthGrammar(2,1));
        System.out.println(starter.kthGrammar(2,2));
        System.out.println(starter.kthGrammar(10,10));
        System.out.println(starter.kthGrammarN2(10,10));
    }


    public int kthGrammar(int n, int k) {
        if (n == 1) return 0;
        if (k == 1) return 0;
        int prevK = k %2 == 1 ? k/2 + 1 : k/2;
        int preKth = kthGrammar(n - 1, prevK);
        if (k %2 == 1) {
            if (preKth % 2 == 1) return 1;
            else return 0;
        } else {
            if (preKth % 2 == 1) return 0;
            else return 1;
        }
    }

    public int kthGrammarN2(int n, int k) {
        int maxK = (int) Math.pow(2, n - 1);
        int[][] rows = new int[n][maxK];
        int prevSize = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < prevSize; j++) {
                if (rows[i - 1][j] == 0) {
                    rows[i][j*2] = 0;
                    rows[i][j*2+1] = 1;
                } else {
                    rows[i][j*2] = 1;
                    rows[i][j*2+1] = 0;
                }
            }
            prevSize = prevSize*2;
        }
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(rows[i]));
            System.out.println("----");
        }
        return rows[n-1][k-1];
    }
}
