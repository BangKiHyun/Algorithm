package programmers.recursion;

public class pg_5주차 {
    private String target;
    private int answer = 0;

    public static void main(String[] args) {
        String word = "I";
        final pg_5주차 task = new pg_5주차();
        System.out.println(task.solution(word));
    }

    public int solution(String word) {
        String[] candidates = {"A", "E", "I", "O", "U"};
        this.target = word;
        recursion(0, "", candidates);
        return answer;
    }

    private boolean recursion(int count, String word, String[] candidates) {
        if (word.equals(target)) return true;
        if (word.length() == 5) return false;
        for (int idx = 0; idx < candidates.length; idx++) {
            String newWord = word + candidates[idx];
            answer++;
            if (recursion(count + 1, newWord, candidates)) return true;
        }
        return false;
    }
}