package programmers.trie;

import java.util.*;

public class pg_가사_검색 {
    private static final int MAX = 100001;
    private static final char WILD_CARD = '?';

    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

        for (int answer : solution(words, queries)) {
            System.out.print(answer + " ");
        }
    }

    public static int[] solution(String[] words, String[] queries) {
        Trie[] tries = new Trie[MAX];
        for (String word : words) {
            int length = word.length();
            if (tries[length] == null) tries[length] = new Trie();
            tries[length].insert(word);
        }

        int[] answer = new int[queries.length];
        int answerIdx = 0;
        for (String query : queries) {
            int length = query.length();
            if (tries[length] == null) {
                answer[answerIdx++] = 0;
                continue;
            }
            answer[answerIdx++] = tries[length].getDuplicateCount(query);
        }
        return answer;
    }

    private static class Trie {
        private TrieNode frontNode;
        private TrieNode backNode;

        public Trie() {
            this.frontNode = new TrieNode();
            this.backNode = new TrieNode();
        }

        public void insert(String word) {
            insertFrontNode(word);
            insertBackNode(word);
        }

        private void insertFrontNode(String word) {
            TrieNode currentNode = frontNode;
            for (Character character : word.toCharArray()) {
                currentNode.plusCount();
                currentNode = currentNode.getChildNodes().computeIfAbsent(character, c -> new TrieNode());
            }
        }

        private void insertBackNode(String word) {
            TrieNode currentNode = backNode;
            for (int idx = word.length() - 1; idx >= 0; idx--) {
                currentNode.plusCount();
                currentNode = currentNode.getChildNodes().computeIfAbsent(word.charAt(idx), c -> new TrieNode());
            }
        }

        public int getDuplicateCount(String word) {
            if (word.charAt(0) == WILD_CARD) {
                return getDuplicateCountOfBack(word);
            }
            return getDuplicateCountOfFront(word);
        }

        private int getDuplicateCountOfBack(String word) {
            TrieNode currentNode = backNode;
            for (int idx = word.length() - 1; idx >= 0; idx--) {
                Character character = word.charAt(idx);
                if (character == WILD_CARD) break;
                if (currentNode.isNotContainsChildNodes(character)) return 0;
                currentNode = currentNode.findChildNodesOf(character);
            }
            return currentNode.getDuplicateCount();
        }

        private int getDuplicateCountOfFront(String word) {
            TrieNode currentNode = frontNode;
            for (Character character : word.toCharArray()) {
                if (character == WILD_CARD) break;
                if (currentNode.isNotContainsChildNodes(character)) return 0;
                currentNode = currentNode.findChildNodesOf(character);
            }
            return currentNode.getDuplicateCount();
        }
    }

    private static class TrieNode {
        private Map<Character, TrieNode> childNodes;
        private int duplicateCount;

        public TrieNode() {
            this.childNodes = new HashMap<>();
            this.duplicateCount = 0;
        }

        public Map<Character, TrieNode> getChildNodes() {
            return this.childNodes;
        }

        public boolean isNotContainsChildNodes(Character character) {
            return !this.childNodes.containsKey(character);
        }

        public TrieNode findChildNodesOf(Character character) {
            return this.childNodes.get(character);
        }

        public void plusCount() {
            this.duplicateCount++;
        }

        public int getDuplicateCount() {
            return this.duplicateCount;
        }
    }
}
