package programmers;

public class pg_17687_kakao {
    public static void main(String[] args) {
        int n = 16;
        int t = 16;
        int m = 2;
        int p = 1;
        System.out.println(solution(n, t, m, p));
    }

    private static String solution(int n, int t, int m, int p) {
        int len = t * m;
        String output = getOutput(n, len);
        StringBuilder ans = new StringBuilder();

        int cnt = 0;
        for (int i = p - 1; i <= len; i += m) {
            if (cnt == t) break;
            ans.append(output.charAt(i));
            cnt++;
        }

        return String.valueOf(ans);
    }

    private static String getOutput(int n, int len) {
        int num = 1;
        int depth = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(0);

        while (depth <= len) {
            StringBuilder change = new StringBuilder();
            int temp = num;

            while (temp != 0) {
                int measure = temp % n;
                if (isBigger(measure)) change.append((char) (measure + 55));
                else change.append(measure);
                temp /= n;
                depth++;
            }

            sb.append(change.reverse());
            num++;
        }
        return String.valueOf(sb);
    }

    private static boolean isBigger(int num) {
        return num >= 10;
    }
}
