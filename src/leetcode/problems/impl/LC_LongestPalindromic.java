package leetcode.problems.impl;

// Palindrome 앞뒤 순서를 바꿔도 같음
public class LC_LongestPalindromic {
    private static int startIdx = 0;
    private static int endIdx = 1;
    private static int maxLength = -1;

    public static void main(String[] args) {
        String s = "ac";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String text) {
        for (int idx = 0; idx < text.length() - 1; idx++) {
            findMaxSubstringPalindrome(text, idx, idx);
            findMaxSubstringPalindrome(text, idx, idx + 1);
        }
        String answer = text.substring(startIdx, endIdx);
        startIdx = 0;
        endIdx = 1;
        maxLength = -1;
        return answer;
    }

    private static void findMaxSubstringPalindrome(String text, int start, int end) {
        while (isRange(start, end, text.length()) && (text.charAt(start) == text.charAt(end))) {
            start--;
            end++;
        }

        int length = end - ++start;
        if (length > maxLength) {
            startIdx = start;
            endIdx = end;
            maxLength = length;
        }
    }

    private static boolean isRange(int start, int end, int length) {
        return start >= 0 && end < length;
    }
}
