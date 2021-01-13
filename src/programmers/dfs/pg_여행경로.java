package programmers.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class pg_여행경로 {
    private static final String DEPARTURE_AIRPORT = "ICN";

    private static List<String> ansList = new ArrayList<>();
    private static boolean[] visit;

    public static void main(String[] args) {
        String[][] tickets = {
                {"ICN", "SFO"},
                {"ICN", "ATL"},
                {"SFO", "ATL"},
                {"ATL", "ICN"},
                {"ATL", "SFO"}};

        for (String ans : solution(tickets)) {
            System.out.print(ans + " ");
        }
    }

    public static String[] solution(String[][] tickets) {
        visit = new boolean[tickets.length];

        for (int i = 0; i < tickets.length; i++) {
            StringBuilder sb = new StringBuilder();
            String departureAirport = tickets[i][0];
            String destinationAirport = tickets[i][1];

            if (DEPARTURE_AIRPORT.equals(departureAirport)) {
                visit[i] = true;
                goDFS(destinationAirport, tickets, 1, sb.append(departureAirport));
                visit[i] = false;
            }
        }

        return getAnswer();
    }

    private static void goDFS(String destinationAirport, String[][] tickets, int depth, StringBuilder travelRoute) {
        travelRoute.append(",").append(destinationAirport);
        if (tickets.length == depth) {
            ansList.add(String.valueOf(travelRoute));
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visit[i]) {
                String curDepartureAirport = tickets[i][0];
                String curDestinationAirport = tickets[i][1];
                if (destinationAirport.equals(curDepartureAirport)) {
                    visit[i] = true;
                    goDFS(curDestinationAirport, tickets, depth + 1, travelRoute);
                    visit[i] = false;
                    travelRoute.delete(travelRoute.length() - 4, travelRoute.length());
                }
            }
        }
    }

    private static String[] getAnswer() {
        Collections.sort(ansList);
        return ansList.get(0).split(",");
    }
}
