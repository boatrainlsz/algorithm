package com.lsz.dualpointer;

import java.util.HashMap;

public class MinimumWindowSubstring {
    char[] targetChars;
    private HashMap<Character, Integer> targetFreq = new HashMap<>();
    private HashMap<Character, Integer> windowFreq = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(new MinimumWindowSubstring().minWindow(
                "abcdefghijklmn", "z"
        ));
    }

    public String minWindow(String s, String t) {
        for (char c : t.toCharArray()) {
            targetFreq.merge(c, 1, Integer::sum);
        }
        targetChars = t.toCharArray();
        int minLen = Integer.MAX_VALUE;
        String ans = "";
        int left = 0, right = 0;
        while (right < s.length()) {
            while (!windowContainsT() && right < s.length()) {
                windowFreq.merge(s.charAt(right), 1, Integer::sum);
                right++;
            }
            while (windowContainsT()) {
                windowFreq.merge(s.charAt(left), -1, Integer::sum);
                left++;
            }
            String substring = s.substring(left == 0 ? 0 : left - 1, right);
            if (substring.length() < minLen && substring.length() >= t.length()) {
                minLen = substring.length();
                ans = substring;
            }
            if (right == s.length()) break;
            right = left;
            windowFreq.clear();
        }
        if (left == 0) {
            return "";
        }
        return ans;
    }


    private boolean windowContainsT() {
        for (char c : targetChars) {
            if (!windowFreq.containsKey(c)) {
                return false;
            }
            if (windowFreq.get(c) < targetFreq.get(c)) {
                return false;
            }
        }
        return true;
    }
}
