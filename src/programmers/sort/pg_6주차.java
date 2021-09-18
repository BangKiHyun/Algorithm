package programmers.sort;

import java.util.ArrayList;
import java.util.List;

public class pg_6주차 {

    public static void main(String[] args) {
        int[] weights = {60, 70, 60};
        String[] head2head = {"NNN", "NNN", "NNN"};
        final pg_6주차 task = new pg_6주차();
        for (int answer : task.solution(weights, head2head)) {
            System.out.print(answer + " ");
        }
    }

    public int[] solution(int[] weights, String[] head2head) {
        int n = weights.length;
        List<Boxer> boxerList = new ArrayList<>();
        for (int idx = 0; idx < n; idx++) {
            double winCount = 0;
            int loseCount = 0;
            int winningCntOfHeavierThanMe = 0;
            final String[] records = head2head[idx].split("");
            for (int otherIdx = 0; otherIdx < n; otherIdx++) {
                if (records[otherIdx].equals("W")) {
                    if (weights[idx] < weights[otherIdx]) winningCntOfHeavierThanMe++;
                    winCount++;
                } else if (records[otherIdx].equals("L")) loseCount++;
            }
            double winningRate = 0;
            if (winCount + loseCount != 0) winningRate = winCount / (winCount + loseCount);
            boxerList.add(new Boxer(idx + 1, weights[idx], winningCntOfHeavierThanMe, winningRate));
        }
        boxerList.sort((o1, o2) -> {
            if (o1.winningRate == o2.winningRate) {
                if (o1.winningCntOfHeavierThanMe == o2.winningCntOfHeavierThanMe) {
                    if (o1.weight == o2.weight) {
                        return o1.idx - o2.idx;
                    }
                    return o2.weight - o1.weight;
                }
                return o2.winningCntOfHeavierThanMe - o1.winningCntOfHeavierThanMe;
            }
            return Double.compare(o2.winningRate, o1.winningRate);
        });
        int[] answer = new int[n];
        for (int idx = 0; idx < n; idx++) {
            answer[idx] = boxerList.get(idx).idx;
        }
        return answer;
    }

    private static class Boxer {
        private int idx;
        private int weight;
        private int winningCntOfHeavierThanMe;
        private double winningRate;

        public Boxer(int idx, int weight, int winningCntOfHeavierThanMe, double winningRate) {
            this.idx = idx;
            this.weight = weight;
            this.winningCntOfHeavierThanMe = winningCntOfHeavierThanMe;
            this.winningRate = winningRate;
        }
    }
}
