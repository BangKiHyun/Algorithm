package programmers.permutation;

import java.util.ArrayList;
import java.util.List;

public class pg_외벽_점검 {
    private int n;
    private int[] weak, dist;
    private int answer = -1;
    private List<Integer> circleWeakList = new ArrayList<>();

    public static void main(String[] args) {
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        final pg_외벽_점검 task = new pg_외벽_점검();
        System.out.println(task.solution(n, weak, dist));
    }

    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        initCircleWeakList();

        for (int count = 1; count <= dist.length; count++) {
            if (answer != -1) break;
            permutation(0, count, new boolean[dist.length], new ArrayList<>());
        }
        return answer;
    }

    private void permutation(int depth, int count, boolean[] visit, ArrayList<Integer> list) {
        if (answer != -1) return;
        if (depth == count) {
            checkCanCover(list);
            return;
        }

        for (int idx = 0; idx < dist.length; idx++) {
            if (!visit[idx]) {
                visit[idx] = true;
                list.add(dist[idx]);
                permutation(depth + 1, count, visit, list);
                visit[idx] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    private void checkCanCover(ArrayList<Integer> list) {
        for (int start = 0; start < weak.length; start++) {
            int left = start;
            boolean flag = true;
            for (int idx = 0; idx < list.size(); idx++) {
                for (int right = start; right < start + weak.length; right++) {
                    if (circleWeakList.get(right) - circleWeakList.get(left) > list.get(idx)) {
                        left = right;
                        if (++idx == list.size()) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    answer = idx + 1;
                    return;
                }
            }
        }
    }

    private void initCircleWeakList() {
        for (int value : weak) {
            circleWeakList.add(value);
        }
        for (int value : weak) {
            circleWeakList.add(value + n);
        }
    }
}

