package programmers.impl;

public class pg_문자열_압축_Re {
    private int n;
    private String text;

    public static void main(String[] args) {
        String text = "xxxxxxxxxxyyy";
        final pg_문자열_압축_Re task = new pg_문자열_압축_Re();
        System.out.println(task.solution(text));
    }

    public int solution(String text) {
        n = text.length();
        if (n <= 1) {
            return 1;
        }
        this.text = text;
        int minValue = Integer.MAX_VALUE;
        for (int cutLength = 1; cutLength <= n / 2; cutLength++) {
            int startLength = 0;
            int count = 0;
            while (startLength + cutLength <= n) {
                String targetText = text.substring(startLength, startLength + cutLength);
                startLength += cutLength;
                int duplicatedCount = findDuplicatedCount(targetText, cutLength, startLength);
                startLength += duplicatedCount * cutLength;
                count += findNewTextLength(cutLength, duplicatedCount);
            }
            if (startLength < n) {
                count += n - startLength;
            }
            minValue = Math.min(minValue, count);
        }
        return minValue;
    }

    private int findNewTextLength(int cutLength, int duplicatedCount) {
        if (duplicatedCount == 0) return cutLength;
        if (duplicatedCount < 9) return cutLength + 1;
        if (duplicatedCount < 99) return cutLength + 2;
        return cutLength + 3;
    }

    private int findDuplicatedCount(String targetText, int cutLength, int startLength) {
        int count = 0;
        for (int idx = startLength; idx <= n - cutLength; idx += cutLength) {
            String newText = text.substring(idx, idx + cutLength);
            if (targetText.equals(newText)) {
                count++;
            } else break;
        }
        return count;
    }
}
