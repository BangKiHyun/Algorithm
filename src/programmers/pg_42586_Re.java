package programmers;

import java.util.ArrayList;
import java.util.List;

public class pg_42586_Re {
    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        for (int ans : solution(progresses, speeds)) {
            System.out.print(ans + " ");
        }
    }

    private static int[] solution(int[] progresses, int[] speeds) {
        List<Integer> dayList = new ArrayList<>();

        int length = progresses.length;
        int curDay = getDay(progresses[0], speeds[0]);
        int cnt = 1;
        for (int i = 1; i < length; i++) {
            int nextDay = getDay(progresses[i], speeds[i]);
            if (curDay < nextDay) {
                curDay = nextDay;
                dayList.add(cnt);
                cnt = 0;
            }
            cnt++;
        }

        if(cnt != 0){
            dayList.add(cnt);
        }

        int[] ans = new int[dayList.size()];
        int idx = 0;
        for(int day : dayList){
            ans[idx++] = day;
        }

        return ans;
    }

    private static int getDay(final int progress, final int speed) {
        int remainProgress = 100 - progress;
        int mok = remainProgress / speed;
        if (remainProgress % speed != 0) {
            return mok + 1;
        }

        return mok;
    }
}
