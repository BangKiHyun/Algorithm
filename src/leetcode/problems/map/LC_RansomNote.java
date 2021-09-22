package leetcode.problems.map;

import java.util.HashMap;
import java.util.Map;

public class LC_RansomNote {

    public static void main(String[] args) {
        String ransomNote = "aa";
        String magazine = "ab";
        final LC_RansomNote task = new LC_RansomNote();
        System.out.println(task.canConstruct(ransomNote, magazine));
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (char key : magazine.toCharArray()) {
            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
        }

        for (char key : ransomNote.toCharArray()) {
            if(!map.containsKey(key) || map.get(key) == 0) return false;
            map.put(key, map.get(key) - 1);
        }
        return true;
    }
}
