package leetcode.problems.impl;

public class LC_ReverseWords {

    public static void main(String[] args) {
        final LC_ReverseWords task = new LC_ReverseWords();
        String s = "      a good   example    ";
        System.out.println(task.reverseWords(s));
    }

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        final String words = s.trim();
        final String[] wordArr = words.split(" ");
        for (int idx = wordArr.length - 1; idx >= 0; idx--) {
            if (!wordArr[idx].isEmpty()) {
                sb.append(wordArr[idx]).append(" ");
            }
        }
        final String answer = sb.toString();
        return answer.substring(0, answer.length() - 1);
    }
}
