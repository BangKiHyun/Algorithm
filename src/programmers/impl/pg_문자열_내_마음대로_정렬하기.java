package programmers.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class pg_문자열_내_마음대로_정렬하기 {
    public static void main(String[] args) {
        String[] strings = {"abce", "abcd", "cdx"};
        int n = 1;
        for (String answer : solution(strings, n)) {
            System.out.print(answer + " ");
        }
    }

    public static String[] solution(String[] strings, int n) {
        List<Text> textList = new ArrayList<>();
        int idx = 0;
        for (String string : strings) {
            final char alphabet = string.charAt(n);
            textList.add(new Text(idx++, alphabet, string));
        }

        textList.sort(new TextComparator());
        String[] answer = new String[idx];
        for (int i = 0; i < idx; i++) {
            answer[i] = textList.get(i).original;
        }
        return answer;
    }

    private static class Text {
        private final int idx;
        private final char alphabet;
        private final String original;

        public Text(int idx, char alphabet, String original) {
            this.idx = idx;
            this.alphabet = alphabet;
            this.original = original;
        }
    }

    private static class TextComparator implements Comparator<Text> {

        @Override
        public int compare(Text first, Text second) {
            if (first.alphabet == second.alphabet) {
                first.original.compareTo(second.original);
            }
            return first.alphabet - second.alphabet;
        }
    }
}
