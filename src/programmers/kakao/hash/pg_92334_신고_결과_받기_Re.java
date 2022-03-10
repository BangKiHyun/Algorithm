package programmers.kakao.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pg_92334_신고_결과_받기_Re {

    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;
        final pg_92334_신고_결과_받기 task = new pg_92334_신고_결과_받기();
        for (int answer : task.solution(id_list, report, k)) {
            System.out.print(answer + " ");
        }
    }

    public int[] solution(String[] id_list, String[] reportList, int k) {
        Map<String, List<String>> reportFromToMap = new HashMap<>();
        Map<String, Integer> reportedCntMap = new HashMap<>();

        initMap(id_list, reportFromToMap, reportedCntMap);

        for (String report : reportList) {
            String[] fromTo = report.split(" ");
            String from = fromTo[0];
            String to = fromTo[1];

            if (!reportFromToMap.get(from).contains(to)) {
                reportFromToMap.get(from).add(to);
                reportedCntMap.put(to, reportedCntMap.get(to) + 1);
            }
        }

        int[] answer = new int[id_list.length];
        for (String id : id_list) {
            if (reportedCntMap.get(id) >= k) {
                int answerIdx = 0;
                for (String secondId : id_list) {
                    if (reportFromToMap.get(secondId).contains(id)) {
                        answer[answerIdx]++;
                    }
                    answerIdx++;
                }
            }
        }

        return answer;
    }

    private void initMap(String[] id_list, Map<String, List<String>> reportFromToMap, Map<String, Integer> reportedCntMap) {
        for (String id : id_list) {
            reportFromToMap.put(id, new ArrayList<>());
            reportedCntMap.put(id, 0);
        }
    }
}


