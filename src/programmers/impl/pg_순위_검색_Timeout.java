package programmers.impl;

import java.util.ArrayList;
import java.util.List;

public class pg_순위_검색_Timeout {

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};

        for (int ans : solution(info, query)) {
            System.out.print(ans + " ");
        }
    }

    public static int[] solution(String[] info, String[] query) {
        List<Info> engineerInfoList = new ArrayList<>();
        for (String engineerInfo : info) {
            String[] engineerSpec = engineerInfo.split(" ");
            engineerInfoList.add(new Info(engineerSpec[0], engineerSpec[1], engineerSpec[2], engineerSpec[3], Integer.parseInt(engineerSpec[4])));
        }

        List<Info> queryInfoList = new ArrayList<>();
        for (String queryInfo : query) {
            String[] querySpec = queryInfo.split(" ");
            queryInfoList.add(new Info(querySpec[0], querySpec[2], querySpec[4], querySpec[6], Integer.parseInt(querySpec[7])));
        }

        int[] answer = new int[queryInfoList.size()];
        int idx = 0;
        for (Info queryInfo : queryInfoList) {
            int satisfyCount = 0;
            for (Info engineerInfo : engineerInfoList) {
                if (queryInfo.isSatisfyCondition(engineerInfo)) {
                    satisfyCount++;
                }
            }
            answer[idx++] = satisfyCount;
        }
        return answer;
    }

    private static class Info {
        private String languege;
        private String job;
        private String career;
        private String soulFood;
        private int score;

        public Info(String language, String job, String career, String soulFood, int score) {
            this.languege = language;
            this.job = job;
            this.career = career;
            this.soulFood = soulFood;
            this.score = score;
        }

        public boolean isSatisfyCondition(Info engineerInfo) {
            return isEqualsOrAny(languege, engineerInfo.languege) &&
                    isEqualsOrAny(job, engineerInfo.job) &&
                    isEqualsOrAny(career, engineerInfo.career) &&
                    isEqualsOrAny(soulFood, engineerInfo.soulFood) &&
                    score <= engineerInfo.score;
        }

        private boolean isEqualsOrAny(String queryInfo, String engineerInfo) {
            return queryInfo.equals(engineerInfo) || queryInfo.equals("-");
        }
    }
}
