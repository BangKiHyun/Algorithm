package programmers;

import java.util.Arrays;

public class pg_43238_Binaray {
    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
        long res = solution(n, times);
        System.out.println(res);
    }

    public static int solution(int n, int[] times) {
        int len = times.length;
        Arrays.sort(times);
        long max = times[len - 1] * n;
        long min = 1;

        while (min + 1 < max) {
            long mid = (max + min) / 2;
            if (passCount(mid, times, n)) max = mid;
            else min = mid;
        }
        if(passCount(min, times, n)){
            return (int)min;
        }
        return (int)max;
    }

    private static boolean passCount(long mid, int[] times, int n) {
        long cnt = 0;
        for (int time : times) {
            if(time > mid) continue;
            cnt += mid / time;
            if (cnt >=  n) {
                return true;
            }
        }
        return false;
    }
}
