package programmers.trie;

import java.util.HashMap;
import java.util.Map;

public class pg_자동완성_Re {

    public static void main(String[] args) {
        String[] words = {"go", "gone", "guild"};
        final pg_자동완성_Re task = new pg_자동완성_Re();
        System.out.println(task.solution(words));
    }

    public int solution(String[] words) {
        final Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int total = 0;
        for (String word : words) {
            total += trie.search(word);
        }
        return total;
    }

    private static class Trie {
        private final Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String word) {
            Node curNode = root;
            for (Character character : word.toCharArray()) {
                curNode = curNode.children.computeIfAbsent(character, c -> new Node());
                curNode.count++;
            }
        }

        public int search(String word) {
            Node curNode = root;
            int depth = 0;
            for (Character character : word.toCharArray()) {
                curNode = curNode.children.get(character);
                depth++;
                if (curNode.count == 1) break;
            }
            return depth;
        }
    }

    private static class Node {
        private Map<Character, Node> children;
        private int count;

        public Node() {
            this.children = new HashMap<>();
            this.count = 0;
        }
    }
}
