package com.alg.dp;

import org.junit.Assert;

public class MinDistance {
    public int minDistance(String word1, String word2) {
        int l1 = word1.length(), l2 = word2.length();
        //md[i][j]表示编辑word1的前i个字符和word2的前j个字符最少操作次数
        int[][] md = new int[l1 + 1][l2 + 1];

        //word2为空，word1要将所有字符串都删除两边才匹配，有几个字符操作几次
        for (int i = 0; i <= l1; i++) md[i][0] = i;
        for (int i = 0; i <= l2; i++) md[0][i] = i;//同上

        //遍历word1的第1..n个字符，这里的i指的是第几个
        for (int i = 1; i <=l1; i++) {
            for (int j = 1; j <= l2; j++) {
                //word[i-1][j-1]已经编辑相同了，下面看第i、j位置

                //两个字符的第i、j位相同，不用编辑
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    md[i][j] = md[i - 1][j - 1];
                } else {
                    //word1[i-1]增加一个字母,word2不变
                    //word2删除一个字母，子结构和 md[i][j-1]相同
                    //word1和word2将i位置的字母替换，子结构和md[i - 1][j - 1]相同
                    //取这三种操作步骤最少的 + 1(本次操作)
                    md[i][j] = 1 + min(md[i - 1][j], md[i][j - 1], md[i - 1][j - 1]);
//                    md[i][j] = 1 + Math.min(md[i - 1][j], Math.min(md[i][j - 1], md[i - 1][j - 1]));
                }
            }
        }
        return md[word1.length()][word2.length()];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";

        MinDistance minDistance = new MinDistance();
        int result = minDistance.minDistance(word1, word2);
        Assert.assertEquals(3, result);


        word1 = "";
        word2 = "a";
        result = minDistance.minDistance(word1, word2);
        Assert.assertEquals(1, result);
    }
}
