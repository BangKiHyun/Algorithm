package leetcode.problems.impl;

import java.util.*;

public class LC_MinimumTimeDifference {
    private static final int MAX_MINUTES = 24 * 60;

    public static void main(String[] args) {
        final List<String> timePoints = Arrays.asList("23:59", "00:00");
        final LC_MinimumTimeDifference task = new LC_MinimumTimeDifference();
        System.out.println(task.findMinDifference(timePoints));
    }

    public int findMinDifference(List<String> timePoints) {
        List<Integer> timeOfMinutes = convertToTimeOfMinutes(timePoints);
        Collections.sort(timeOfMinutes);
        return findAnswer(timeOfMinutes);
    }

    private List<Integer> convertToTimeOfMinutes(List<String> timePoints) {
        List<Integer> ret = new ArrayList<>();
        for (String time : timePoints) {
            String[] hhmm = time.split(":");
            ret.add(getMinutes(hhmm[0], hhmm[1]));
        }
        return ret;
    }

    private Integer getMinutes(String hour, String minute) {
        return Integer.parseInt(hour) * 60 + Integer.parseInt(minute);
    }

    private int findAnswer(List<Integer> timeOfMinutes) {
        int answer = MAX_MINUTES - timeOfMinutes.get(timeOfMinutes.size() - 1) + timeOfMinutes.get(0);
        for (int idx = 1; idx < timeOfMinutes.size(); idx++) {
            int diff = timeOfMinutes.get(idx) - timeOfMinutes.get(idx - 1);
            answer = Math.min(answer, diff);
        }
        return answer;
    }
}
