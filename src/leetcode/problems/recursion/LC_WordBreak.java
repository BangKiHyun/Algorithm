package leetcode.problems.recursion;

import java.util.Arrays;
import java.util.List;

public class LC_WordBreak {

    public static void main(String[] args) {
        final LC_WordBreak task = new LC_WordBreak();
        String s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen");
        System.out.println(task.wordBreak(s, wordDict));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict.contains(s)) return true;

        for (int idx = 1; idx < s.length(); idx++) {
            String word = s.substring(0, idx);
            if (wordDict.contains(word) && wordBreak(s.substring(idx), wordDict)) {
                return true;
            }
        }
        return false;
    }
}
