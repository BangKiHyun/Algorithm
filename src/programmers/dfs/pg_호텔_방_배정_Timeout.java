package programmers.dfs;

import java.util.*;

public class pg_호텔_방_배정_Timeout {

    public static void main(String[] args) {
        long k = 10;
        long[] romm_number = {1, 3, 4, 2, 5, 6};
        for (long answer : solution(k, romm_number)) {
            System.out.print(answer + " ");
        }
    }

    public static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        Map<Long, Long> map = new HashMap<>();
        int answerIdx = 0;
        for (long number : room_number) {
            answer[answerIdx++] = getNextNumber(map, number);
        }
        return answer;
    }

    private static Long getNextNumber(Map<Long, Long> map, long number) {
        if (!map.containsKey(number)) {
            map.put(number, number + 1);
            return number;
        }
        Long nextNumber = getNextNumber(map, map.get(number));
        map.put(number, nextNumber);
        return nextNumber;
    }
}
