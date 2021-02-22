package programmers.dfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class pg_두_개_뽑아서_더하기 {

    public static void main(String[] args) {
        int[] numbers = {2, 1, 3, 4, 1};
        for (int ans : solution(numbers)) {
            System.out.print(ans + " ");
        }
    }

    public static int[] solution(int[] numbers) {
        Set<Integer> answerSet = new HashSet<>();
        int length = numbers.length;
        boolean[] visit = new boolean[length];

        goDFS(numbers, 0, 0, 0, visit, answerSet);

        return getAnswer(answerSet);
    }

    private static void goDFS(int[] numbers, int number, int depth, int startIdx, boolean[] visit, Set<Integer> answerSet) {
        if (depth == 2) {
            answerSet.add(number);
            return;
        }

        for (int i = startIdx; i < numbers.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                goDFS(numbers, number + numbers[i], depth + 1, ++startIdx, visit, answerSet);
                visit[i] = false;
            }
        }
    }

    private static int[] getAnswer(Set<Integer> answerSet) {
        int[] answer = new int[answerSet.size()];
        int idx = 0;
        for (Integer ans : answerSet) {
            answer[idx++] = ans;
        }
        Arrays.sort(answer);
        return answer;
    }
}
