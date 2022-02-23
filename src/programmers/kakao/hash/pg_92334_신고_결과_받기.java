package programmers.kakao.hash;

import java.util.*;

public class pg_92334_신고_결과_받기 {

    private static Map<String, Integer> accumulatedReportMap = new HashMap<>();
    private static Map<String, List<String>> reportUserMap = new HashMap<>();
    private static Map<String, Boolean> sendTargetMap = new HashMap<>();
    private static Map<String, Integer> answerIdxMap = new HashMap<>();

    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;
        final pg_92334_신고_결과_받기 task = new pg_92334_신고_결과_받기();
        for (int answer : task.solution(id_list, report, k)) {
            System.out.print(answer + " ");
        }
    }

    public int[] solution(String[] id_list, String[] report, int k) {
        initMap(id_list);

        for (String reportInfo : report) {
            String[] fromTo = reportInfo.split(" ");
            String from = fromTo[0];
            String to = fromTo[1];
            if (reportUserMap.get(from) == null) {
                reportUserMap.put(from, new ArrayList<>());
            } else if (reportUserMap.get(from).contains(to)) {
                continue;
            }

            reportUserMap.get(from).add(to);
            accumulatedReportMap.put(to, accumulatedReportMap.get(to) + 1);
        }

        return getAnswer(k, report);
    }

    private int[] getAnswer(int k, String[] report) {
        for (String key : accumulatedReportMap.keySet()) {
            if (accumulatedReportMap.get(key) >= k) {
                sendTargetMap.put(key, true);
            }
        }

        int[] answer = new int[accumulatedReportMap.size()];
        for(String reportInfo : report) {
            String[] fromTo = reportInfo.split(" ");
            String from = fromTo[0];
            String to = fromTo[1];
            if(sendTargetMap.get(to)) {
                answer[answerIdxMap.get(from)] += 1;
            }
        }
        return answer;
    }

    private void initMap(String[] id_list) {
        int idx = 0;
        for (String id : id_list) {
            accumulatedReportMap.put(id, 0);
            sendTargetMap.put(id, false);
            answerIdxMap.put(id, idx++);
        }
    }
}
