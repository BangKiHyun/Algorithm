package programmers.binarysearch;

import java.util.*;

public class pg_순위_검색 {
    private static final String ANYTHING = "-";

    private static Map<String, List<Integer>> infoScoresMap = new HashMap<>();

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150",
                "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};

        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250", "- and backend and senior and - 150",
                "- and - and - and chicken 100", "- and - and - and - 150"};

        for (int ans : solution(info, query)) {
            System.out.print(ans + " ");
        }
    }

    public static int[] solution(String[] info, String[] query) {
        initInfoScoresMap(info);
        return getAnswer(query);
    }

    private static void initInfoScoresMap(String[] info) {
        for (String developerInfo : info) {
            goDFS(developerInfo.split(" "), 0, "");
        }

        for (String key : infoScoresMap.keySet()) {
            List<Integer> scores = infoScoresMap.get(key);
            Collections.sort(scores);
        }
    }

    private static void goDFS(String[] developerInfo, int depth, String info) {
        if (depth == 4) {
            int score = Integer.parseInt(developerInfo[4]);
            if (infoScoresMap.containsKey(info)) {
                List<Integer> scores = infoScoresMap.get(info);
                scores.add(score);
            } else {
                List<Integer> newScores = new ArrayList<>();
                newScores.add(score);
                infoScoresMap.put(info, newScores);
            }
            return;
        }

        goDFS(developerInfo, depth + 1, info + ANYTHING);
        goDFS(developerInfo, depth + 1, info + developerInfo[depth]);
    }

    private static int[] getAnswer(String[] query) {
        int[] answer = new int[query.length];
        int idx = 0;
        for (String queryInfo : query) {
            StringBuilder key = new StringBuilder();
            String[] info = queryInfo.split(" ");
            for (int i = 0; i < info.length - 1; i += 2) {
                key.append(info[i]);
            }
            answer[idx++] = getSatisfyCount(key.toString(), Integer.parseInt(info[info.length - 1]));
        }
        return answer;
    }

    private static int getSatisfyCount(String key, int standardScore) {
        if (!infoScoresMap.containsKey(key)) return 0;

        final List<Integer> scores = infoScoresMap.get(key);
        int startIdx = 0;
        int endIdx = scores.size() - 1;
        while (startIdx <= endIdx) {
            int midIdx = (startIdx + endIdx) / 2;
            if (scores.get(midIdx) < standardScore) {
                startIdx = midIdx + 1;
            } else {
                endIdx = midIdx - 1;
            }
        }
        return scores.size() - startIdx;
    }
}
