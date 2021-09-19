package leetcode.problems.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC_WordPattern {

    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog cat cat dog";
        final LC_WordPattern task = new LC_WordPattern();
        System.out.println(task.wordPattern(pattern, s));
    }

    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        String[] stringPattern = s.split(" ");
        if(pattern.length() != stringPattern.length) return false;

        for (int idx = 0; idx < pattern.length(); idx++) {
            char key = pattern.charAt(idx);
            String pat = map.get(key);
            if (pat == null) {
                if (set.contains(stringPattern[idx])) return false;
                map.put(key, stringPattern[idx]);
                set.add(stringPattern[idx]);
            } else {
                if (!pat.equals(stringPattern[idx])) return false;
            }
        }
        return true;
    }
}

