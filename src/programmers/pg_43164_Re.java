package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class pg_43164_Re {
    private static boolean[] visit;
    private static List<String> ansList = new ArrayList<>();

    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        for (String path : solution(tickets)) {
            System.out.print(path + " ");
        }
    }

    public static String[] solution(String[][] tickets) {
        int length = tickets.length;

        for (int i = 0; i < length; i++) {
            visit = new boolean[length];
            StringBuilder sb = new StringBuilder();

            String start = tickets[i][0];
            String end = tickets[i][1];
            if (start.equals("ICN")) {
                visit[i] = true;
                goDFS(tickets, sb.append(start + ","), end, 1);
            }
        }
        Collections.sort(ansList);
        return ansList.get(0).split(",");
    }

    private static void goDFS(String[][] tickets, StringBuilder path, String end, int cnt) {
        path.append(end + ",");
        if (tickets.length == cnt) {
            ansList.add(String.valueOf(path));
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            String start = tickets[i][0];
            if (!visit[i] && start.equals(end)) {
                visit[i] = true;
                goDFS(tickets, path, tickets[i][1], cnt + 1);
                visit[i] = false;
                path.delete(path.length() - 4, path.length());
            }
        }
    }
}
