package programmers;

import java.util.Arrays;

public class pg_42748 {
    static public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int idx = 0;
            int n1 = commands[i][0];
            int n2 = commands[i][1];
            int n3 = commands[i][2];
            int temp[] = new int[n2 - n1 + 1];
            for (int j = n1 - 1; j < n2; j++) {
                temp[idx] = array[j];
                idx++;
            }
            Arrays.sort(temp);
            answer[i] = temp[n3 - 1];
        }
        return answer;
    }

    public static void main(String[] args) {
        int a[] = {1, 5, 2, 6, 3, 7, 4};
        int c[][] = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        int result[] = solution(a, c);
    }
}
