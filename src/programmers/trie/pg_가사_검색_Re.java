package programmers.trie;

import java.util.HashMap;
import java.util.Map;

public class pg_가사_검색_Re {
    private static final int MAX = 100_001;
    private static final String WILD_CARD = "?";

    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

        for (int answer : solution(words, queries)) {
            System.out.print(answer + " ");
        }
    }


    // Trie 알고리즘을 사용
    // 문자 길이와 ?의 시작 위치에 따라 Trie 노드를 구현
    public static int[] solution(String[] words, String[] queries) {
        Trie[] tries = new Trie[MAX];
        for (String word : words) {
            int length = word.length();
            if (tries[length] == null) {
                tries[length] = new Trie();
            }
            tries[length].insert(word);
        }

        int[] answer = new int[queries.length];
        int answerIdx = 0;
        for (String query : queries) {
            if (tries[query.length()] == null) {
                answer[answerIdx++] = 0;
                continue;
            }
            answer[answerIdx++] = tries[query.length()].findMatchCount(query);
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
            TrieNode trieNode = frontNode;
            for (char alphabet : word.toCharArray()) {
                trieNode.addDuplicateCount();
                trieNode = trieNode.childNodes.computeIfAbsent(alphabet, c -> new TrieNode());
            }
        }

        private void insertBackNode(String word) {
            TrieNode trieNode = backNode;
            char[] alphabets = word.toCharArray();
            for (int idx = alphabets.length - 1; idx >= 0; idx--) {
                char alphabet = alphabets[idx];
                trieNode.addDuplicateCount();
                trieNode = trieNode.childNodes.computeIfAbsent(alphabet, c -> new TrieNode());
            }
        }

        public int findMatchCount(String word) {
            if (word.startsWith(WILD_CARD)) {
                return findMatchCountOfBack(word);
            }
            return findMatchCountOfFront(word);
        }

        private int findMatchCountOfFront(String word) {
            TrieNode trieNode = frontNode;
            for (char alphabet : word.toCharArray()) {
                if (WILD_CARD.equals(String.valueOf(alphabet))) {
                    break;
                }
                if (trieNode.isNotContains(alphabet)) {
                    return 0;
                }
                trieNode = trieNode.findChildNode(alphabet);
            }
            return trieNode.duplicateCount;
        }

        private int findMatchCountOfBack(String word) {
            TrieNode trieNode = backNode;
            char[] alphabets = word.toCharArray();
            for (int idx = alphabets.length - 1; idx >= 0; idx--) {
                char alphabet = alphabets[idx];
                if (WILD_CARD.equals(String.valueOf(alphabet))) {
                    break;
                }
                if (trieNode.isNotContains(alphabet)) {
                    return 0;
                }
                trieNode = trieNode.findChildNode(alphabet);
            }
            return trieNode.duplicateCount;
        }
    }

    private static class TrieNode {
        private Map<Character, TrieNode> childNodes;
        private int duplicateCount;

        public TrieNode() {
            childNodes = new HashMap<>();
            duplicateCount = 0;
        }

        public void addDuplicateCount() {
            this.duplicateCount++;
        }

        public boolean isNotContains(char alphabet) {
            return !childNodes.containsKey(alphabet);
        }

        public TrieNode findChildNode(char alphabet) {
            return childNodes.get(alphabet);
        }
    }
}
