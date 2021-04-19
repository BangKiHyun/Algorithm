package leetcode.problems.trie;

import java.util.HashMap;
import java.util.Map;

public class LC_ImplementTrie {

    public static void main(String[] args) {
        String[] input = {"Trie", "insert", "search", "search", "startsWith", "insert", "search"};
        String[] words = {"", "apple", "apple", "app", "app", "app", "app"};
        for (int idx = 0; idx < input.length; idx++) {
            String command = input[idx];
            String word = words[idx];

            Trie trie = new Trie();
            if (command.startsWith("T")) {
                System.out.println("Trie");
                trie = new Trie();
            } else if (command.startsWith("i")) {
                System.out.println("insert");
                trie.insert(word);
            } else if (command.startsWith("se")) {
                System.out.println("search");
                System.out.println(trie.search(word));
            } else {
                System.out.println("startsWith");
                System.out.println(trie.startsWith(word));
            }
        }
    }

    private static class Trie {
        private TrieNode rootNode;

        public Trie() {
            this.rootNode = new TrieNode();
        }

        public void insert(String word) {
            TrieNode curNode = rootNode;
            for (Character character : word.toCharArray()) {
                curNode = curNode.getChildrenNode().computeIfAbsent(character, c -> new TrieNode());
                curNode.plusDepth();
            }
        }

        public boolean search(String word) {
            TrieNode curNode = rootNode;
            for (char character : word.toCharArray()) {
                if (curNode.isNotContains(character)) {
                    return false;
                }
                curNode = curNode.getChildrenNode().get(character);
            }
            return curNode.depth == word.length();
        }

        public boolean startsWith(String prefix) {
            TrieNode curNode = rootNode;
            for (char character : prefix.toCharArray()) {
                if (curNode.isNotContains(character)) {
                    return false;
                }
                curNode = curNode.getChildrenNode().get(character);
            }
            return true;
        }
    }

    private static class TrieNode {
        private Map<Character, TrieNode> childrenNode;
        private int depth;

        public TrieNode() {
            this.childrenNode = new HashMap<>();
            this.depth = 0;
        }

        public Map<Character, TrieNode> getChildrenNode() {
            return childrenNode;
        }

        public void plusDepth() {
            this.depth++;
        }

        public boolean isNotContains(char key) {
            return !this.childrenNode.containsKey(key);
        }
    }
}
