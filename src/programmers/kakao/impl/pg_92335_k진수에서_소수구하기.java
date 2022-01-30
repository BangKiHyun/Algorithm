package programmers.kakao.impl;

public class pg_92335_k진수에서_소수구하기 {

    private static final int MAX = 1_000_000;

    public static void main(String[] args) {
        final pg_92335_k진수에서_소수구하기 task = new pg_92335_k진수에서_소수구하기();
        int n = 437674;
        int k = 3;
        System.out.println(task.solution(n, k));
    }

    public int solution(int n, int k) {
        return findPrimeNumber(Integer.toUnsignedString(n, k));
    }

    private int findPrimeNumber(String convertNumber) {
        int answer = 0;
        int startIdx = 0;
        int endIdx = convertNumber.indexOf("0");
        while (startIdx < convertNumber.length()) {
            if (endIdx == -1) endIdx = convertNumber.length();
            if (isPrimeNumber(Long.parseLong(convertNumber.substring(startIdx, endIdx)))) {
                answer++;
            }
            startIdx = endIdx + 1;
            endIdx = convertNumber.indexOf("0", startIdx);
            while (startIdx == endIdx) {
                startIdx++;
                endIdx = convertNumber.indexOf("0", startIdx);
            }
        }
        return answer;
    }

    private boolean isPrimeNumber(long maybePrimeNumber) {
        if (maybePrimeNumber == 1) return false;

        long lastIdx = (long) Math.sqrt(maybePrimeNumber);
        for (long idx = 2; idx <= lastIdx; idx++) {
            if (maybePrimeNumber % idx == 0) return false;
        }
        return true;
    }
}
