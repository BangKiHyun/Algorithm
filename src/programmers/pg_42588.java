package programmers;

import java.util.Stack;

public class pg_42588 {
    static public int[] solution(int[] heights) {
        int[] answer = new int[heights.length];
        for (int i = heights.length - 1; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (heights[j] > heights[i]) {
                    answer[i] = j+1;
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] a = {5,4,3,2,1};
        int[] r = solution(a);
        for(int i : r){
            System.out.print(i + " ");
        }
    }
}
