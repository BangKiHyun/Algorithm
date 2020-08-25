package programmers;

import java.util.ArrayList;
import java.util.HashSet;

public class pg_49191_Graph {
    static ArrayList<Player> list = new ArrayList<>();

    public static void main(String[] args) {
        int n = 5;
        int[][] result = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        int ans = solution(n, result);

        System.out.println(ans);
    }

    public static int solution(int n, int[][] results) {
        int answer = 0;
        list.add(new Player(0));

        for (int i = 1; i <= n; i++) {
            list.add(new Player(i));
            list.get(i).win = new HashSet<>();
            list.get(i).lose = new HashSet<>();
        }

        for (int r[] : results) {
            list.get(r[0]).win.add(r[1]);
            list.get(r[1]).lose.add(r[0]);
        }

        for (int count = 0; count < 2; count++) {

            for (int i = 1; i <= n; i++) {
                Player player = list.get(i);
                HashSet<Integer> tmp = new HashSet<>();
                for (int win : player.win) {
                    tmp.addAll(list.get(win).win);
                }
                player.win.addAll(tmp);
                tmp.clear();
                for (int lose : player.lose) {
                    tmp.addAll(list.get(lose).lose);
                }
                player.lose.addAll(tmp);
            }
        }

        for (int i = 1; i <= n; i++) {
            Player player = list.get(i);
            int winCount = player.win.size();
            int loseCount = player.lose.size();

            if (winCount + loseCount == n - 1) {
                answer++;
            }
        }
        return answer;
    }

    static class Player {
        int key;
        HashSet<Integer> win;
        HashSet<Integer> lose;

        Player(int k) {
            this.key = k;
            this.win = new HashSet<>();
            this.lose = new HashSet<>();
        }
    }
}
