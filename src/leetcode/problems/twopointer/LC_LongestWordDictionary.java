package leetcode.problems.twopointer;

import java.util.*;

public class LC_LongestWordDictionary {

    public static void main(String[] args) {
        String s = "abpcplea";
        List<String> dictionary = Arrays.asList("ale", "apple", "monkey", "plea");
        final LC_LongestWordDictionary task = new LC_LongestWordDictionary();
        System.out.println(task.findLongestWord(s, dictionary));
    }

    public String findLongestWord(String s, List<String> dictionary) {
        if (s == null || s.length() == 0 || dictionary == null || dictionary.isEmpty()) return "";
        Map<Character, List<Integer>> indexMap = buildIndexMap(s);
        String answer = "";
        for (String word : dictionary) {
            if (isSubsequence(word, indexMap) &&
                    (answer.length() < word.length() ||
                            (answer.length() == word.length() && answer.compareTo(word) > 0))) {
                answer = word;
            }
        }
        return answer;
    }

    private Map<Character, List<Integer>> buildIndexMap(String s) {
        final int M = s.length();
        Map<Character, List<Integer>> indexMap = new HashMap<>(26);
        for (int i = 0; i < M; i++) {
            Character ch = s.charAt(i);
            List<Integer> list = indexMap.getOrDefault(ch, new ArrayList<>());
            if (list.isEmpty()) {
                indexMap.put(ch, list);
            }
            list.add(i);
        }
        return indexMap;
    }

    private boolean isSubsequence(String word, Map<Character, List<Integer>> indexMap) {
        final int L = word.length();
        int prev = 0;
        System.out.println(word);
        for (int i = 0; i < L; i++) {
            Character ch = word.charAt(i);
            List<Integer> list = indexMap.get(ch);
            System.out.println(list);
            if (list == null) return false;
            int idx = Collections.binarySearch(list, prev);
            System.out.println(idx);
            if (idx < 0) idx = -idx - 1;
            if (idx >= list.size()) return false;
            prev = list.get(idx) + 1;
        }
        return true;
    }
}
