package leetcode.problems.impl;

public class LC_ZigZagConversion {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRow = 4;

        System.out.println(convert(s, numRow));
    }

    public static String convert(String targetText, int numRow) {
        if (numRow == 1) {
            return targetText;
        }

        StringBuilder[] sbs = new StringBuilder[numRow];
        for (int idx = 0; idx < numRow; idx++) {
            sbs[idx] = new StringBuilder();
        }

        int row = 0;
        int dir = 0;
        for (char text : targetText.toCharArray()) {
            row += dir;
            sbs[row].append(text);
            if (row == 0) {
                dir = 1;
            } else if (row == numRow - 1) {
                dir = -1;
            }
        }
        return getAnswer(sbs);
    }

    private static String getAnswer(StringBuilder[] sbs) {
        StringBuilder answerStringBuilder = new StringBuilder();
        for (StringBuilder sb : sbs) {
            answerStringBuilder.append(sb);
        }
        return String.valueOf(answerStringBuilder);
    }
}
