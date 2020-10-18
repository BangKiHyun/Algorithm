package programmers;

public class pg_12979_구현 {
    public static void main(String[] args) {
        int n = 11;
        int[] stations = {4, 11};
        int w = 1;
        System.out.println(solution(n, stations, w));
    }

    public static int solution(int n, int[] stations, int w) {
        boolean[] check = new boolean[n + 1];
        for (int station : stations) {
            int start = station - w;
            int end = station + w;
            checkStation(start, end, check);
        }

        int curLength = 0;
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (!check[i]) {
                if (curLength < w) {
                    curLength++;
                } else {
                    i += w;
                    curLength = 0;
                    ans++;
                }
            } else {
                if (curLength != 0) {
                    i += w;
                    curLength = 0;
                    ans++;
                }
            }
        }
        return curLength == 0 ? ans : ans + 1;
    }

    private static void checkStation(int start, int end, boolean[] check) {
        if (end >= check.length) {
            end = check.length - 1;
        }
        if (start < 1) {
            start = 1;
        }
        for (int idx = start; idx <= end; idx++) {
            check[idx] = true;
        }
    }
}
