package programmers;

public class pg_42883_Re {
    public static void main(String[] args) {
        String number = "4177252841";
        int k = 4;
        System.out.println(solution(number, k));
    }

    public static String solution(String number, int k) {
        int length = number.length();
        if (length == k) return number;

        StringBuilder sb = new StringBuilder();
        int curIdx = 0;
        for (int i = 0; i < length - k; i++) {
            int max = 0;
            for (int j = curIdx; j <= i + k; j++) {
                int num = number.charAt(j) - '0';
                if (max < num) {
                    max = num;
                    curIdx = j + 1;
                }
            }
            sb.append(max);
        }

        return String.valueOf(sb);
    }
}
