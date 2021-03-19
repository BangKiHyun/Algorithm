package programmers.trie;

import java.util.*;

public class pg_자동완성 {

    public static void main(String[] args) {
        String[] words = {"go", "gone", "guild"};
        System.out.println(solution(words));
    }

    public static int solution(String[] words) {
        Arrays.sort(words);
        Trie rootNode = new Trie(new TrieNode());
        for (String word : words) {
            rootNode.insert(word);
        }

        return getInputCount(rootNode, words);
    }

    private static int getInputCount(Trie rootNode, String[] words) {
        int inputCount = 0;
        for (String word : words) {
            inputCount += rootNode.getInputCount(word);
        }
        return inputCount;
    }

    private static class Trie {

        private TrieNode rootNode;

        public Trie(TrieNode rootNode) {
            this.rootNode = rootNode;
        }

        public void insert(String word) {
            TrieNode currentNode = this.rootNode;
            for (Character character : word.toCharArray()) {
                currentNode = currentNode.getChildren()
                        .computeIfAbsent(character, c -> new TrieNode());
                currentNode.plusDuplicateCount();
            }
        }

        public int getInputCount(String word) {
            int inputCount = 0;
            TrieNode currentNode = rootNode;
            for (Character character : word.toCharArray()) {
                currentNode = currentNode.getChildren().get(character);
                inputCount++;

                if (currentNode.duplicateCount == 1) {
                    break;
                }
            }
            return inputCount;
        }
    }

    private static class TrieNode {
        private Map<Character, TrieNode> children;
        private int duplicateCount;

        public TrieNode() {
            this.children = new HashMap<>();
            this.duplicateCount = 0;
        }

        public Map<Character, TrieNode> getChildren() {
            return this.children;
        }

        public void plusDuplicateCount() {
            this.duplicateCount++;
        }
    }
}
