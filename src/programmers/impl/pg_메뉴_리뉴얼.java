package programmers.impl;

import java.util.*;

public class pg_메뉴_리뉴얼 {

    private static Map<String, CourseMenuInfo> menuMap = new HashMap<>();
    private static List<String> answer = new ArrayList<>();
    private static boolean[] visit;
    private static int max = 2;

    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};
        for (String ans : solution(orders, course)) {
            System.out.print(ans + " ");
        }
    }

    public static String[] solution(String[] orders, int[] course) {
        for (int courseCount : course) {
            for (String order : orders) {
                List<String> orderList = Arrays.asList(order.split(""));
                Collections.sort(orderList);
                visit = new boolean[orderList.size()];
                findCourseMenu(orderList, 0, courseCount, "", 0);
            }
            filterCourseMenuInMaxCount();
            max = 2;
        }

        return getAnswer();
    }

    private static void findCourseMenu(List<String> orderList, int curCount, int targetCount, String courseMenu, int startIdx) {
        if (curCount == targetCount) {
            if (menuMap.containsKey(courseMenu)) {
                CourseMenuInfo courseMenuInfo = menuMap.get(courseMenu);
                courseMenuInfo.plusOrderCnt();
                max = Math.max(max, courseMenuInfo.orderCnt);
            } else {
                menuMap.put(courseMenu, new CourseMenuInfo(1));
            }
            return;
        }

        for (int i = startIdx; i < orderList.size(); i++) {
            if (!visit[i]) {
                visit[i] = true;
                findCourseMenu(orderList, curCount + 1, targetCount, courseMenu + orderList.get(i), ++startIdx);
                visit[i] = false;
            }
        }
    }

    private static void filterCourseMenuInMaxCount() {
        for (String key : menuMap.keySet()) {
            final CourseMenuInfo courseMenuInfo = menuMap.get(key);
            if (courseMenuInfo.orderCnt == max) {
                answer.add(key);
            }
        }

        menuMap.clear();
    }

    private static String[] getAnswer() {
        Collections.sort(answer);

        String[] newAnswer = new String[answer.size()];
        int idx = 0;
        for (String ans : answer) {
            newAnswer[idx++] = ans;
        }
        return newAnswer;
    }

    private static class CourseMenuInfo {
        private int orderCnt;

        public CourseMenuInfo(int orderCnt) {
            this.orderCnt = orderCnt;
        }

        public void plusOrderCnt() {
            this.orderCnt++;
        }
    }
}
