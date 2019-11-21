package programmers;

import java.util.Arrays;

public class pg_42748_Sort {
    static public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int idx = 0;
            int startNum = commands[i][0];
            int endNum = commands[i][1];
            int findIdx = commands[i][2];
            int temp[] = new int[endNum - startNum + 1];

            for (int j = startNum - 1; j < endNum; j++) {
                temp[idx] = array[j];
                idx++;
            }

            Arrays.sort(temp);
            answer[i] = temp[findIdx - 1];
        }
        return answer;
    }

    public static void main(String[] args) {
        int a[] = {1, 5, 2, 6, 3, 7, 4};
        int c[][] = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        int result[] = solution(a, c);
    }
}
