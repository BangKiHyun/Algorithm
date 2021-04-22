package programmers.impl;

public class pg_문자열_압축 {

    public static void main(String[] args) {
        String s = "ababcdcdababcdcd";
        System.out.println(solution(s));
    }

    public static int solution(String text) {
        int length = text.length();
        if (length == 1) {
            return 1;
        }

        int minLength = Integer.MAX_VALUE;
        for (int cutLength = 1; cutLength <= length / 2; cutLength++) {
            int curPos = 0;
            int curLength = length;
            while (cutLength + curPos <= length) {
                String targetText = text.substring(curPos, curPos + cutLength);
                curPos += cutLength;
                int duplicateCount = findDuplicateCount(curPos, cutLength, targetText, text);
                curPos += duplicateCount * cutLength;
                curLength = findNewLength(curLength, cutLength, duplicateCount);
                minLength = Math.min(minLength, curLength);
            }
        }
        return minLength;
    }

    private static int findDuplicateCount(int curPos, int cutLength, String targetText, String text) {
        int duplicateCount = 0;
        while (curPos + cutLength <= text.length()) {
            String newText = text.substring(curPos, curPos + cutLength);
            if (newText.equals(targetText)) {
                duplicateCount++;
                curPos += cutLength;
            } else break;
        }
        return duplicateCount;
    }

    private static int findNewLength(int length, int cutLength, int duplicateCount) {
        if (duplicateCount == 0) return length;
        if (duplicateCount < 9) length++;
        else if (duplicateCount < 99) length += 2;
        else if (duplicateCount < 999) length += 3;
        else length += 4;

        return length - (cutLength * duplicateCount);
    }
}
