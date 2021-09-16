package programmers.impl;

import java.util.ArrayList;
import java.util.List;

public class pg_2주차 {

    public static void main(String[] args) {
        int[][] scores = {{100, 90, 98, 88, 65},
                {50, 45, 99, 85, 77},
                {47, 88, 95, 80, 67},
                {61, 57, 100, 80, 65},
                {24, 90, 94, 75, 65}};
        final pg_2주차 task = new pg_2주차();
        System.out.println(task.solution(scores));
    }

    public String solution(int[][] scores) {
        List<Double> avgList = new ArrayList<>();
        int n = scores.length;
        for (int col = 0; col < n; col++) {
            int minValue = 101;
            int maxValue = -1;
            int myValue = scores[col][col];
            int duplicatedCount = 0;
            double score = 0;
            for (int raw = 0; raw < n; raw++) {
                score += scores[raw][col];
                minValue = Math.min(minValue, scores[raw][col]);
                maxValue = Math.max(maxValue, scores[raw][col]);
                if (myValue == scores[raw][col]) duplicatedCount++;
            }
            int num = n;
            if (minValue == myValue && duplicatedCount < 2) {
                score -= myValue;
                num--;
            }
            if (maxValue == myValue && duplicatedCount < 2) {
                score -= myValue;
                num--;
            }
            avgList.add(score / num);
        }
        StringBuilder sb = new StringBuilder();
        for (double avg : avgList) {
            sb.append(findScore(avg));
        }
        return sb.toString();
    }

    private String findScore(double avgScore) {
        if (avgScore >= 90) return "A";
        else if (avgScore >= 80) return "B";
        else if (avgScore >= 70) return "C";
        else if (avgScore >= 50) return "D";
        else return "F";
    }
}
