package leetcode.problems.recursion;

import java.util.ArrayList;

public class LC_NumberOfBoomerangs_TimeOut {
    private int length;
    private int answer = 0;
    private boolean[] visit;

    public static void main(String[] args) {
        final LC_NumberOfBoomerangs_TimeOut task = new LC_NumberOfBoomerangs_TimeOut();
        int[][] points = {{1, 1},
                {2, 2},
                {3, 3}};
        System.out.println(task.numberOfBoomerangs(points));
    }

    public int numberOfBoomerangs(int[][] points) {
        this.length = points.length;
        if (length < 3) {
            return 0;
        }

        visit = new boolean[length];
        recursion(0, 3, points, new ArrayList<>());
        return answer;
    }

    private void recursion(int depth, int size, int[][] points, ArrayList<Integer> list) {
        if (depth == size) {
            checkBoomerangNumber(list, points);
            return;
        }

        for (int idx = 0; idx < length; idx++) {
            if (!visit[idx]) {
                visit[idx] = true;
                list.add(idx);
                recursion(depth + 1, size, points, list);
                visit[idx] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    private void checkBoomerangNumber(ArrayList<Integer> list, int[][] points) {
        if (calculateSize(list.get(0), list.get(1), points) == calculateSize(list.get(0), list.get(2), points)) {
            answer++;
        }
    }

    private int calculateSize(int first, int second, int[][] points) {
        int x = points[first][0] - points[second][0];
        int y = points[first][1] - points[second][1];
        return x * x + y * y;
    }
}
