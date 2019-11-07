package programmers;

public class pg_42841_완탐 {
    private static int candidate_num[] = new int[3];

    public static void main(String[] args) {
        int[][] baseball = {{123, 1, 1}, {356, 1, 0}, {327, 2, 0}, {489, 0, 1}};
        int ans = solution(baseball);
        System.out.println(ans);
    }

    private static int solution(int[][] baseball) {
        int answer = 0;
        for (int i = 123; i <= 987; i++) {
            initCandidate(i);
            if (!isValid()) {
                continue;
            }
            if (CheckedCandidateAndBaseball(baseball)) {
                answer++;
            }
        }
        return answer;
    }

    private static void initCandidate(int num) {
        candidate_num[0] = num / 100;
        candidate_num[1] = num % 100 / 10;
        candidate_num[2] = num % 100 % 10;
    }

    private static boolean isValid() {
        if (candidate_num[0] == candidate_num[1] || candidate_num[1] == candidate_num[2] || candidate_num[0] == candidate_num[2] || candidate_num[0] == 0 || candidate_num[1] == 0 || candidate_num[2] == 0) {
            return false;
        }
        return true;
    }

    private static boolean CheckedCandidateAndBaseball(int[][] baseball) {
        for (int j = 0; j < baseball.length; j++) {
            int SB[] = new int[2];
            int num = baseball[j][0];
            int baseball_num[] = {num / 100, num % 100 / 10, num % 100 % 10};
            for (int k = 0; k < 3; k++) {
                for (int m = 0; m < 3; m++) {
                    if (candidate_num[k] == baseball_num[m]) {
                        if (k == m) {
                            SB[0]++;
                            break;
                        } else {
                            SB[1]++;
                            break;
                        }
                    }
                }
            }
            if (SB[0] != baseball[j][1] || SB[1] != baseball[j][2]) {
                return false;
            }
        }
        return true;
    }
}
