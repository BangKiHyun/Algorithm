package programmers.impl;

public class pg_2개_이하로_다른비트 {

    public static void main(String[] args) {
        final pg_2개_이하로_다른비트 task = new pg_2개_이하로_다른비트();
        long[] numbers = {1001, 337, 333, 673, 343, 221, 997, 121, 1015, 665, 779, 891, 421, 222, 256, 512, 128, 100};
        for (long answer : task.solution(numbers)) {
            System.out.print(answer + ", ");
        }
    }

    public long[] solution(long[] numbers) {
        int length = numbers.length;
        long[] answer = new long[length];
        int answerIdx = 0;
        for (long number : numbers) {
            final String binaryStr = Long.toBinaryString(number);
            answer[answerIdx++] = covertBiggerToNumeric(binaryStr);
        }
        return answer;
    }

    private long covertBiggerToNumeric(String binaryStr) {
        int zeroIdx = findZeroIdxOfBinary(binaryStr);
        if (zeroIdx == -1) {
            return Long.parseLong("10" + binaryStr.substring(1), 2);
        }
        if (zeroIdx == binaryStr.length() - 1) {
            return Long.parseLong(binaryStr.substring(0, zeroIdx) + "1", 2);
        }
        return Long.parseLong(binaryStr.substring(0, zeroIdx) + "1" + "0" + binaryStr.substring(zeroIdx + 2), 2);
    }

    private int findZeroIdxOfBinary(String binaryStr) {
        int zeroIdx = -1;
        final char[] binaries = binaryStr.toCharArray();
        for (int binaryIdx = binaries.length - 1; binaryIdx >= 0; binaryIdx--) {
            if (binaries[binaryIdx] == '0') {
                zeroIdx = binaryIdx;
                break;
            }
        }
        return zeroIdx;
    }
}
