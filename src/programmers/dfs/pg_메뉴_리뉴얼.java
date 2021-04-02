package programmers.dfs;

import java.util.*;

public class pg_메뉴_리뉴얼 {
    private static Map<String, Integer> courseMenuMap = new HashMap<>();
    private static boolean[] visit;
    private static int maxCount;

    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};
        for (String ans : solution(orders, course)) {
            System.out.print(ans + " ");
        }
    }

    public static String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        for (int courseCount : course) {
            maxCount = 2;
            for (String order : orders) {
                List<String> menus = Arrays.asList(order.split(""));
                Collections.sort(menus);
                visit = new boolean[menus.size()];
                findCourseMenu(courseCount, menus, "", 0);
            }
            findNewCourseMenu(answer);
            courseMenuMap.clear();
        }

        return getAnswer(answer);
    }

    private static void findCourseMenu(int courseCount, List<String> menus, String courseMenu, int startIdx) {
        if (courseCount == courseMenu.length()) {
            if (courseMenuMap.containsKey(courseMenu)) {
                courseMenuMap.put(courseMenu, courseMenuMap.get(courseMenu) + 1);
                maxCount = Math.max(maxCount, courseMenuMap.get(courseMenu));
                return;
            }
            courseMenuMap.put(courseMenu, 1);
        }

        for (int idx = startIdx; idx < menus.size(); idx++) {
            if (!visit[idx]) {
                visit[idx] = true;
                findCourseMenu(courseCount, menus, courseMenu + menus.get(idx), ++startIdx);
                visit[idx] = false;
            }
        }
    }

    private static void findNewCourseMenu(List<String> answer) {
        for (String menu : courseMenuMap.keySet()) {
            int orderCount = courseMenuMap.get(menu);
            if (orderCount == maxCount) {
                answer.add(menu);
            }
        }
    }

    private static String[] getAnswer(List<String> answer) {
        String[] newAnswer = new String[answer.size()];
        answer.sort(Comparator.naturalOrder());

        int idx = 0;
        for (String courseMenu : answer) {
            newAnswer[idx++] = courseMenu;
        }
        return newAnswer;
    }
}
