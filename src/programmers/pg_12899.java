package programmers;

import java.util.HashMap;
import java.util.Map;

public class pg_12899 {
    public static void main(String[] args) {
        int n = 9;
        System.out.println(solution(n));
    }

    private static String solution(int n) {
        StringBuilder sb = new StringBuilder();

        Map<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(0, 4);

        while (n != 0) {
            int remainder = n % 3;
            n /= 3;

            if (remainder == 0) {
                n--;
            }
            sb.append(hashMap.get(remainder));
        }

        return String.valueOf(sb.reverse());
    }
}

//4, 5, 6
//7, 8, 9
//10, 11, 12

// 20
// mok na
// 6  2
// 2  2
// 0  2
// 2 2 2
//1, 2, 4
//11, 12, 14
//21, 22, 24
//41, 42, 44
//111 112, 114
//121, 122, 124
//141, 142