package programmers.recursion;

import java.util.ArrayList;

public class pg_하노이의_탑 {

    private ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) {
        int n = 2;
        final pg_하노이의_탑 task = new pg_하노이의_탑();
        for (int[] answer : task.solution(n)) {
            for (int a : answer) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }

    public int[][] solution(int n) {
        hanoi(1, 2, 3, n);
        int[][] answer = new int[list.size()][2];
        for (int idx = 0; idx < list.size(); idx++) {
            answer[idx][0] = list.get(idx)[0];
            answer[idx][1] = list.get(idx)[1];
        }
        return answer;
    }

    public void hanoi(int from, int via, int to, int n) {
        int[] move = {from, to};
        if (n == 1) {
            list.add(move);
            return;
        }
        hanoi(from, to, via, n - 1);
        list.add(move);
        hanoi(via, from, to, n - 1);
    }
}
