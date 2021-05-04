package leetcode.problems.dfs;

import java.util.ArrayList;
import java.util.List;

public class LC_GenerateParenthesis {
    private static final String LEFT_BLOCK = "(";
    private static final String RIGHT_BLOCK = ")";

    public static void main(String[] args) {
        int n = 3;
        System.out.println(generateParenthesis(n));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> parenthesisList = new ArrayList<>();
        generateParenthesis(n, 0, 0, new StringBuilder(), parenthesisList);
        return parenthesisList;
    }

    private static void generateParenthesis(int depth, int left, int right, StringBuilder sb, List<String> parenthesisList) {
        if (left == depth && right == depth) {
            parenthesisList.add(sb.toString());
        }

        if (left < depth) {
            sb.append(LEFT_BLOCK);
            generateParenthesis(depth, left + 1, right, sb, parenthesisList);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (right < left) {
            sb.append(RIGHT_BLOCK);
            generateParenthesis(depth, left, right + 1, sb, parenthesisList);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
