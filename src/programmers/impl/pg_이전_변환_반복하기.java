package programmers.impl;

public class pg_이전_변환_반복하기 {

    public static void main(String[] args) {
        final pg_이전_변환_반복하기 task = new pg_이전_변환_반복하기();
        String strBinary = "110010101001";
        for (int answer : task.solution(strBinary)) {
            System.out.print(answer + " ");
        }
    }

    public int[] solution(String strBinary) {
        int count = 0;
        int removedZeroCount = 0;
        while (!strBinary.equals("1")) {
            int removedZeroLength = strBinary.replaceAll("0", "").length();
            removedZeroCount += strBinary.length() - removedZeroLength;
            strBinary = toBinary(removedZeroLength);
            count++;
        }
        return new int[]{count, removedZeroCount};
    }

    private String toBinary(int count) {
        StringBuilder ret = new StringBuilder();
        while (count > 0) {
            ret.append(count % 2);
            count /= 2;
        }
        return ret.reverse().toString();
    }
}
