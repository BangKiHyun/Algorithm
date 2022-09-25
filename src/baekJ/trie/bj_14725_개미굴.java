package baekJ.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_14725_개미굴 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());
        final TrieNode trieNode = new TrieNode();
        for (int n = 0; n < cnt; n++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            int depth = Integer.parseInt(st.nextToken());
            final ArrayList<String> words = new ArrayList<>();
            for (int i = 0; i < depth; i++) {
                words.add(st.nextToken());
            }
            trieNode.insert(words);
        }

        String division = "";
        trieNode.print(trieNode.root, division);
    }

    private static class TrieNode {
        private Map<String, Node> root;

        public TrieNode() {
            this.root = new TreeMap<>();
        }

        public void insert(List<String> words) {
            Map<String, Node> curNode = root;
            for (String word : words) {
                if (!curNode.containsKey(word)) {
                    curNode.put(word, new Node(word));
                }
                curNode = curNode.get(word).children;

            }
        }

        public void print(Map<String, Node> node, String division) {
            for (String key : node.keySet()) {
                System.out.println(division + "" + key);
                print(node.get(key).children, division + "--");
            }
        }
    }

    private static class Node {
        private String idx;
        private Map<String, Node> children;

        public Node(String word) {
            this.idx = word;
            this.children = new TreeMap<>();
        }
    }
}
