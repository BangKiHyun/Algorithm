package programmers;

public class pg_42885_Greedy_TimeOut {
    private static int min = Integer.MAX_VALUE;
    private static int length;
    private static boolean visit[];

    public static void main(String[] args) {
        int[] people = {70, 80, 50, 50};
        int limit = 100;

        int ans = solution(people, limit);
        System.out.println(ans);
    }

    private static int solution(int[] people, int limit) {
        length = people.length;
        visit = new boolean[length];

        for (int i = 0; i < length; i++) {
            visit[i] = true;
            goDFS(people[i], 1, 1, people, limit);
            visit[i] = false;
        }
        return min;
    }

    private static void goDFS(int sum, int depth, int cnt, int[] people, int limit) {
        if (depth == length) {
            min = Math.min(min, cnt);
            return;
        }

        for (int i = 0; i < length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                if (sum + people[i] > limit) {
                    goDFS(people[i], depth + 1, cnt + 1, people, limit);
                } else {
                    goDFS(sum + people[i], depth + 1, cnt, people, limit);
                }
                visit[i] = false;
            }
        }
    }
}
