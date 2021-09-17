package programmers.map;

import java.util.*;

public class pg_4주차 {

    public static void main(String[] args) {
        String[] table = {"SI JAVA JAVASCRIPT SQL PYTHON C#",
                "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++",
                "HARDWARE C C++ PYTHON JAVA JAVASCRIPT",
                "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP",
                "GAME C++ C# JAVASCRIPT C JAVA"};
        String[] languages = {"PYTHON", "C++", "SQL"};
        int[] preference = {7, 5, 5};
        final pg_4주차 task = new pg_4주차();
        System.out.println(task.solution(table, languages, preference));
    }

    public String solution(String[] table, String[] languages, int[] preference) {
        List<Job> jobs = new ArrayList<>();
        for (String info : table) {
            String[] jobAndLanguages = info.split(" ");
            jobs.add(new Job(jobAndLanguages[0], Arrays.asList(new Language(jobAndLanguages[1], 5),
                    new Language(jobAndLanguages[2], 4),
                    new Language(jobAndLanguages[3], 3),
                    new Language(jobAndLanguages[4], 2),
                    new Language(jobAndLanguages[5], 1))));
        }

        Map<String, Integer> jobAndScoreMap = new HashMap<>();
        int maxValue = -1;
        for (Job job : jobs) {
            int amount = 0;
            for (int idx = 0; idx < languages.length; idx++) {
                String language = languages[idx];
                int score = job.findScore(language);
                amount += (preference[idx] * score);
            }
            jobAndScoreMap.put(job.job, amount);
            maxValue = Math.max(maxValue, amount);
        }
        List<String> answerList = new ArrayList<>();
        for (String key : jobAndScoreMap.keySet()) {
            if (jobAndScoreMap.get(key) == maxValue) answerList.add(key);
        }
        Collections.sort(answerList);
        return answerList.get(0);
    }

    private static class Job {
        private String job;
        private List<Language> languages;

        public Job(String job, List<Language> languages) {
            this.job = job;
            this.languages = languages;
        }

        public int findScore(String targetLanguage) {
            for (Language language : languages) {
                if (language.equalsLanguage(targetLanguage)) return language.score;
            }
            return 0;
        }
    }

    private static class Language {
        private String language;
        private int score;

        public Language(String language, int score) {
            this.language = language;
            this.score = score;
        }

        public boolean equalsLanguage(String targetLanguage) {
            return this.language.equals(targetLanguage);
        }
    }
}
