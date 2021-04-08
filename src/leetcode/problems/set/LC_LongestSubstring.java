package leetcode.problems.set;

import java.util.HashSet;
import java.util.Set;

public class LC_LongestSubstring {

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length < 2) {
            return length;
        }

        char[] characters = s.toCharArray();
        Set<Character> characterSet = new HashSet<>();
        int max = 0;
        int endIdx = 0;
        int startIdx = 0;
        while (endIdx < length) {
            char alphabet = characters[endIdx];
            if (!characterSet.contains(alphabet)) {
                characterSet.add(alphabet);
                endIdx++;
                max = Math.max(max, endIdx - startIdx);
                continue;
            }
            characterSet.remove(characters[startIdx++]);
        }
        return max;
    }
}
