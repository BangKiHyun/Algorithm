package leetcode.problems.dfs;

import java.util.*;

public class LC_LetterCombinations {
    private static Map<Character, List<Character>> letterMap = new HashMap<>();
    private static List<String> answerList;

    public static void main(String[] args) {
        String digits = "234";
        System.out.println(letterCombinations(digits));
    }

    public static List<String> letterCombinations(String digits) {
        answerList = new ArrayList<>();
        if (digits.isEmpty()) {
            return answerList;
        }

        initLetterMap();
        char[] charDigits = digits.toCharArray();
        dfs(new StringBuilder(), charDigits, 0);
        return answerList;
    }

    private static void initLetterMap() {
        char key = 'a';
        for (char letter = '2'; letter <= '9'; letter++) {
            if (letter == '7' || letter == '9') {
                letterMap.put(letter, Arrays.asList(key++, key++, key++, key++));
            } else {
                letterMap.put(letter, Arrays.asList(key++, key++, key++));
            }
        }
    }

    private static void dfs(StringBuilder sb, char[] charDigits, int depth) {
        if (depth == charDigits.length) {
            answerList.add(sb.toString());
            return;
        }

        List<Character> characterList = letterMap.get(charDigits[depth]);
        for (char value : characterList) {
            sb.append(value);
            dfs(sb, charDigits, depth + 1);
            sb.setLength(sb.length() - 1);
        }
    }
}
