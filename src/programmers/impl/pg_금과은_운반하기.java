package programmers.impl;

public class pg_금과은_운반하기 {

    public static void main(String[] args) {
        final pg_금과은_운반하기 task = new pg_금과은_운반하기();
        int a = 90;
        int b = 500;
        int[] g = {70, 70, 0};
        int[] s = {0, 0, 500};
        int[] w = {100, 100, 2};
        int[] t = {4, 8, 1};
        System.out.println(task.solution(a, b, g, s, w, t));
    }

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = Long.MAX_VALUE;

        long startTime = 0;
        long endTime = (long) (10e5 * 4 * 10e9);
        while (startTime <= endTime) {
            long mid = (long) (Math.floor(startTime + endTime) / 2);
            int gold = 0;
            int silver = 0;
            int goldAndSilver = 0;

            for (int idx = 0; idx < g.length; idx++) {
                int curGold = g[idx];
                int curSilver = s[idx];
                int curWeight = w[idx];
                int curTime = t[idx];

                long moveCnt = (long) (Math.floor(mid / (curTime * 2)));
                if (mid % (curTime * 2) >= curTime) moveCnt++;

                gold += Math.min(curGold, moveCnt * curWeight);
                silver += Math.min(curSilver, moveCnt * curWeight);
                goldAndSilver += Math.min(curGold + curSilver, moveCnt * curWeight);
            }

            if (gold >= a && silver >= b && goldAndSilver >= a + b) {
                endTime = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                startTime = mid + 1;
            }
        }
        return answer;
    }
}
