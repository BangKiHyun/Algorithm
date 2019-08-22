package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class pg_43164_DFS {
    static boolean visit[];
    static String route = "";
    static List<String> a = new ArrayList<>();

    public static void main(String[] args) {
        String[][] t = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        String ans[] = solution(t);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }

    static void dfs(String[][] ticket, String end, int cnt) {
        route += end + ",";
        if (cnt == ticket.length) {
            a.add(route);
            return;
        }

        for (int i = 0; i < ticket.length; i++) {
            String start = ticket[i][0];
            if (start.equals(end) && !visit[i]) {
                visit[i] = true;
                dfs(ticket, ticket[i][1], cnt + 1);
                visit[i] = false;
                route = route.substring(0, route.length() - 4);
            }
        }
    }

    static public String[] solution(String[][] tickets) {
        for (int i = 0; i < tickets.length; i++) {
            visit = new boolean[tickets.length];
            String start = tickets[i][0];
            String end = tickets[i][1];

            if (start.equals("ICN")) {
                route = start + ",";
                visit[i] = true;
                dfs(tickets, end, 1);
            }
        }

        Collections.sort(a);
        String[] answer = a.get(0).split(",");

        return answer;
    }
}
