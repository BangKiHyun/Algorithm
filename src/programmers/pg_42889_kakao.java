package programmers;

import java.util.*;

public class pg_42889_kakao {
    public static void main(String[] args) {
        int n = 4;
        int[] stages = {4, 4, 4, 4, 4};
        for (int i : solution(n, stages)) {
            System.out.print(i + " ");
        }
    }

    private static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];

        Arrays.sort(stages);
        ArrayList<Stage> list = new ArrayList<>();

        int challenger = stages.length;
        int start_idx = 0;
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = start_idx; j < stages.length; j++) {
                if (i == stages[j]) cnt++;
                else {
                    start_idx = j;
                    break;
                }
            }
            list.add(new Stage(i, (double) cnt / challenger));
            challenger -= cnt;
            cnt = 0;
        }

        Collections.sort(list, new StageComparator());

        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).idx;
        }

        return answer;
    }

    private static class Stage {
        private int idx;
        private double failureRate;

        public Stage(int idx, double failureRate) {
            this.idx = idx;
            this.failureRate = failureRate;
        }
    }

    private static class StageComparator implements Comparator<Stage> {

        @Override
        public int compare(Stage o1, Stage o2) {
            double failure = o1.failureRate - o2.failureRate;
            if (failure > 0) {
                return -1;
            } else if (failure < 0)
                return 1;
            else {
                return o1.idx - o2.idx;
            }
        }
    }
}
