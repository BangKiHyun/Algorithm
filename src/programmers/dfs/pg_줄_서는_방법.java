package programmers.dfs;

public class pg_줄_서는_방법 {
    private static boolean[] visit;
    private static int cnt = 0;
    private static int[] ans;

    public static void main(String[] args) {
        int n = 3;
        int k = 5;

        for (int ans : solution(n, k)) {
            System.out.print(ans + " ");
        }
    }

    public static int[] solution(int n, long k) {
        int[] candidate = new int[n];
        visit = new boolean[n + 1];
        goDFS(n, k, 0, candidate);
        return ans;
    }

    private static boolean goDFS(int n, long k, int depth, int[] candidate) {
        if (n == depth) {
            if (++cnt == k) {
                ans = candidate;
                return true;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                candidate[depth] = i;
                if (goDFS(n, k, depth + 1, candidate)) {
                    return true;
                }
                visit[i] = false;
            }
        }

        return false;
    }
}
