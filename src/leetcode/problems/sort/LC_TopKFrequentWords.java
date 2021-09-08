package leetcode.problems.sort;

import java.util.*;

public class LC_TopKFrequentWords {

    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        final LC_TopKFrequentWords task = new LC_TopKFrequentWords();
        for (String answer : task.topKFrequent(words, k)) {
            System.out.print(answer + " ");
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        List<String> answer = new ArrayList<>();
        if (words == null || words.length == 0) {
            return answer;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String i : words) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        Queue<String> prq = new PriorityQueue<>(k, (s1, s2) ->
                map.get(s1).equals(map.get(s2)) ? s2.compareTo(s1) : map.get(s1) - map.get(s2));

        for (String i : map.keySet()) {
            prq.add(i);
            if (prq.size() > k) {
                prq.poll();
            }
        }
        while (!prq.isEmpty()) {
            answer.add(prq.poll());
        }
        Collections.reverse(answer);
        return answer;
    }
}
