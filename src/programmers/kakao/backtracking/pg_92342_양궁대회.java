package programmers.kakao.backtracking;

public class pg_92342_양궁대회 {

    private static final int LENGTH = 11;

    private int[] res = {-1};
    private int maxPoint = -1;

    public static void main(String[] args) {
        int n = 5;
        int[] info = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};
        final pg_92342_양궁대회 task = new pg_92342_양궁대회();
        for (int answer : task.solution(n, info)) {
            System.out.print(answer + " ");
        }
    }

    public int[] solution(int n, int[] info) {
        goBackTracking(info, new int[LENGTH], 0, n);
        return res;
    }

    private void goBackTracking(int[] peachInfo, int[] lionInfo, int depth, int n) {
        if (depth == n) {
            int[] peachAndLionPoint = calculatePoint(peachInfo, lionInfo);
            int peachPoint = peachAndLionPoint[0];
            int lionPoint = peachAndLionPoint[1];
            if (lionPoint > peachPoint && lionPoint - peachPoint >= maxPoint) {
                res = lionInfo.clone();
                maxPoint = lionPoint - peachPoint;
            }
            return;
        }

        for (int idx = 0; idx < LENGTH && lionInfo[idx] <= peachInfo[idx]; idx++) {
            lionInfo[idx]++;
            goBackTracking(peachInfo, lionInfo, depth + 1, n);
            lionInfo[idx]--;
        }
    }

    private int[] calculatePoint(int[] peachInfo, int[] lionInfo) {
        int[] peachAndLionPoint = new int[2];
        for (int idx = 0; idx < LENGTH; idx++) {
            if (peachInfo[idx] == 0 && lionInfo[idx] == 0) continue;
            if (peachInfo[idx] >= lionInfo[idx]) {
                peachAndLionPoint[0] += 10 - idx;
                continue;
            }
            peachAndLionPoint[1] += 10 - idx;
        }
        return peachAndLionPoint;
    }
}
