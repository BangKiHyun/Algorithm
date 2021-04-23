package leetcode.problems.trie;

import java.util.HashMap;
import java.util.Map;

public class LC_WordDictionary {

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        String[] inputWords = {"bad", "dad", "mad"};
        for (String word : inputWords) {
            wordDictionary.addWord(word);
        }

        String[] searchWords = {"pad", "bad", ".ad", "b.."};
        for (String word : searchWords) {
            System.out.println(wordDictionary.search(word));
        }
    }

    private static class WordDictionary {
        private DataStructure dataStructure;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            dataStructure = new DataStructure();
        }

        public void addWord(String word) {
            DataStructure dataStructure = this.dataStructure;
            for (char character : word.toCharArray()) {
                dataStructure = dataStructure.getChildDataStructure()
                        .computeIfAbsent(character, c -> new DataStructure());
            }
            dataStructure.changeEndOfWord(true);
        }

        public boolean search(String word) {
            return searchHelper(word, this.dataStructure, 0);
        }

        private boolean searchHelper(String word, DataStructure dataStructure, int pointer) {
            for (int idx = pointer; idx < word.length(); idx++) {
                char character = word.charAt(idx);
                if (character == '.') {
                    for (char nextData : dataStructure.getChildDataStructure().keySet()) {
                        if (searchHelper(word, dataStructure.getChildDataStructure().get(nextData), idx + 1)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    if (dataStructure.isContains(character)) {
                        dataStructure = dataStructure.getChildDataStructure().get(character);
                    } else {
                        return false;
                    }
                }
            }
            return dataStructure.isEndOfWord();
        }
    }

    private static class DataStructure {
        private Map<Character, DataStructure> childDataStructure;
        private boolean endOfWord;

        public DataStructure() {
            this.childDataStructure = new HashMap<>();
            this.endOfWord = false;
        }

        public Map<Character, DataStructure> getChildDataStructure() {
            return childDataStructure;
        }

        public boolean isEndOfWord() {
            return endOfWord;
        }

        public void changeEndOfWord(boolean endOfWord) {
            this.endOfWord = endOfWord;
        }

        public boolean isContains(char character) {
            return this.childDataStructure.containsKey(character);
        }
    }
}
