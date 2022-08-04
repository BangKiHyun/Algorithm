package programmers.impl;

public class pg_문자열_압축_60057 {

    public static void main(String[] args) {
        final pg_문자열_압축_60057 task = new pg_문자열_압축_60057();
        String text = "ababcdcdababcdcd";
        System.out.println(task.solution(text));
    }

    public int solution(String text) {
        int n = text.length();
        int minCount = n;

        for (int cutSize = 1; cutSize <= n / 2; cutSize++) {
            int count = 0;
            int curPoint = 0;

            while (curPoint + cutSize <= n) {
                String targetText = text.substring(curPoint, curPoint + cutSize);
                curPoint += cutSize;
                int duplicateCount = findDuplicateCount(text, targetText, curPoint, cutSize);
                count += calculateCount(duplicateCount, cutSize);
                curPoint += duplicateCount * cutSize;
            }

            if (curPoint < n) {
                count += n - curPoint;
            }
            minCount = Math.min(minCount, count);
        }

        return minCount;
    }

    private int findDuplicateCount(String originText, String targetText, int curPoint, int cutSize) {
        int duplicateCount = 0;
        for (int idx = curPoint; idx <= originText.length() - cutSize; idx += cutSize) {
            String newText = originText.substring(idx, idx + cutSize);
            if (targetText.equals(newText)) duplicateCount++;
            else break;
        }
        return duplicateCount;
    }

    private int calculateCount(int duplicateCount, int cutSize) {
        if (duplicateCount == 0) return cutSize;
        if (duplicateCount < 9) return cutSize + 1;
        if (duplicateCount < 99) return cutSize + 2;
        if (duplicateCount < 999) return cutSize + 3;
        return cutSize + 4;
    }
}
