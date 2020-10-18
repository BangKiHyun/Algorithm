package programmers;

public class pg_12979_그리디 {
    public static void main(String[] args) {
        int n = 16;
        int[] stations = {9};
        int w = 2;
        System.out.println(solution(n, stations, w));
    }

    public static int solution(int n, int[] stations, int w) {
        int outRangeL = 0;
        int outRangeR = 0;
        int innerRangeL = 0;
        int innerRangeR = 0;
        int ans = 0;

        for (int station : stations) {
            outRangeL = innerRangeR;

            innerRangeL = station - w;
            innerRangeR = station + w;

            if (innerRangeL < 0) innerRangeL = 0;
            if (innerRangeR > n) innerRangeR = n;

            outRangeR = innerRangeL - 1;

            if (outRangeR < 0 || outRangeL == outRangeR || outRangeL > outRangeR) continue;
            double distance = (double) (outRangeR - outRangeL) / ((w * 2) + 1);
            ans += (int) distance;
            if (distance % 1 != 0) ans++;
        }

        if (innerRangeR != n) {
            double distance = (double) (n - innerRangeL) / ((w * 2) + 1);
            ans += (int) distance;
            if (distance % 1 != 0) ans++;
        }
        return ans;
    }
}
