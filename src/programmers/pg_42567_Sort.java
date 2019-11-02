package programmers;

import java.util.Arrays;

public class pg_42567_Sort {
    public static void main(String[] args) {
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        solution(participant, completion);
    }

    private static String solution(String[] participant, String[] completion) {
        sort(participant, completion);
        String ans = findLoser(participant, completion);
        return ans;
    }

    private static void sort(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);
    }

    private static String findLoser(String[] participant, String[] completion) {
        for (int i = 0; i < completion.length; i++) {
            if (!participant[i].equals(completion[i])) {
                return participant[i];
            }
        }
        return participant[participant.length - 1];
    }
}
