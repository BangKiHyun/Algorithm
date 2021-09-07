package leetcode.problems.trie;

import java.util.HashMap;
import java.util.Map;

public class LC_MapSumPairs {

    public static void main(String[] args) {
        final MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("apple", 5);
        mapSum.insert("apple", 1);
        System.out.println(mapSum.sum("apple"));
    }

    private static class MapSum {
        private final TrieNode rootNode;
        private final HashMap<String, Integer> pair;

        public MapSum() {
            this.rootNode = new TrieNode();
            this.pair = new HashMap<>();
        }

        public void insert(String key, int val) {
            int originVal = val;
            if (pair.containsKey(key)) {
                val -= pair.get(key);
            }
            pair.put(key, originVal);

            TrieNode curNode = rootNode;
            for (Character character : key.toCharArray()) {
                curNode = curNode.getChildrenNode().computeIfAbsent(character, c -> new TrieNode());
                curNode.value += val;
            }
        }

        public int sum(String prefix) {
            TrieNode curNode = rootNode;
            for (char character : prefix.toCharArray()) {
                if (curNode.isNotContains(character)) {
                    return 0;
                }
                curNode = curNode.getChildrenNode().get(character);
            }
            return curNode.value;
        }
    }

    private static class TrieNode {
        private Map<Character, TrieNode> childrenNode;
        private int value;

        public TrieNode() {
            this.childrenNode = new HashMap<>();
            this.value = 0;
        }

        public Map<Character, TrieNode> getChildrenNode() {
            return childrenNode;
        }

        public boolean isNotContains(char key) {
            return !this.childrenNode.containsKey(key);
        }
    }
}
