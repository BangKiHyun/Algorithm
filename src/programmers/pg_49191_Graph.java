package programmers;

import java.util.ArrayList;
import java.util.HashSet;

public class pg_49191_Graph {
    static ArrayList<RankNode> list = new ArrayList<>();

    public static void main(String[] args) {
        int n = 5;
        int[][] result = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        int ans = solution(n, result);

        System.out.println(ans);
    }

    public static int solution(int n, int[][] results) {
        int answer = 0;
        list.add(new RankNode(0));

        for (int i = 1; i <= n; i++) {
            list.add(new RankNode(i));
            list.get(i).win = new HashSet<>();
            list.get(i).lose = new HashSet<>();
        }

        for (int r[] : results) {
            list.get(r[0]).win.add(r[1]);
            list.get(r[1]).lose.add(r[0]);
        }

        for (int i = 1; i <= n; i++) {
            RankNode rn = list.get(i);
            HashSet<Integer> tmp = new HashSet<>();
            for (int win : rn.win) {
                for (int w : list.get(win).win) {
                    tmp.add(w);
                }
            }
            rn.win.addAll(tmp);
            tmp.clear();
            for (int lose : rn.lose) {
                for (int l : list.get(lose).lose) {
                    tmp.add(l);
                }
            }
            rn.lose.addAll(tmp);

            int num = rn.win.size();
            num += rn.lose.size();

            if (num == n - 1) {
                answer++;
            }
        }
        return answer;
    }

    static class RankNode {
        int key;
        HashSet<Integer> win;
        HashSet<Integer> lose;

        RankNode(int k) {
            this.key = k;
            this.win = win;
            this.lose = lose;
        }
    }
}
