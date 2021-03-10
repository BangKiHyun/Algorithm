package programmers.impl;

public class pg_가사_검색_Timeout {
    private static final String WILD_CARD = "?";

    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

        for (int answer : solution(words, queries)) {
            System.out.print(answer + " ");
        }
    }

    public static int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        int answerIdx = 0;
        for (String query : queries) {
            Query queryObject;
            if (query.startsWith(WILD_CARD)) {
                int lastIndexOfWildcard = query.lastIndexOf(WILD_CARD);
                String text = query.substring(lastIndexOfWildcard + 1);
                queryObject = new Query(text, query.length(), false);
            } else {
                int startIndexOfWildcard = query.indexOf(WILD_CARD);
                String text = query.substring(0, startIndexOfWildcard);
                queryObject = new Query(text, query.length(), true);
            }

            answer[answerIdx++] = getMatchCount(words, queryObject);
        }
        return answer;
    }

    private static int getMatchCount(String[] words, Query query) {
        int count = 0;
        for (String word : words) {
            if (query.isMatching(word)) {
                count++;
            }
        }
        return count;
    }

    private static class Query {
        private String text;
        private int length;
        private boolean startStatus;

        public Query(String text, int length, boolean startStatus) {
            this.text = text;
            this.length = length;
            this.startStatus = startStatus;
        }

        public boolean isMatching(String word) {
            if (word.length() != length) {
                return false;
            }

            if (startStatus) {
                return word.startsWith(text);
            }
            return word.endsWith(text);
        }
    }
}
