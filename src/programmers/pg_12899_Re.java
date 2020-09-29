package programmers;


import java.util.HashMap;

public class pg_12899_Re {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(solution(n));
    }

    public static String solution(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(0, 4);

        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int remain = n % 3;
            n /= 3;

            if (remain == 0) {
                n--;
            }

            sb.append(map.get(remain));
        }

        return String.valueOf(sb.reverse());
    }
}
