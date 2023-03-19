package programmers.greedy;

public class pg_덧칠하기 {

    public static void main(String[] args) {
        int n = 8;
        int m = 2;
        int[] section = {2, 6};
        final pg_덧칠하기 task = new pg_덧칠하기();
        System.out.println(task.solution(n, m, section));
    }

    public int solution(int n, int m, int[] section) {
        if (m == 1) {
            return section.length;
        }

        int cnt = 0;
        int curPos = 0;
        for (int pos = 0; pos < section.length; pos++) {
            if (curPos < section[pos]) {
                curPos = section[pos] + m - 1;
                cnt++;
            }
        }
        return cnt;
    }
}
