package programmers;

import java.util.*;

public class pg_42862 {
    static public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;

        List<Integer> lostList = new ArrayList<Integer>();
        for (int a : lost) lostList.add(a);

        List<Integer> reserveList = new ArrayList<Integer>();
        for (int b : reserve) reserveList.add(b);

        for (int c = 0; c < lostList.size(); c++) {
            for (int d = 0; d < reserveList.size(); d++) {
                if (lostList.get(c) == reserveList.get(d)) {
                    lostList.remove(c);
                    reserveList.remove(d);
                    answer++;
                    break;
                }
            }
        }

        for (int i = 0; i < lostList.size(); i++) {
            int lostNum = lostList.get(i);
            for (int j = 0; j < reserveList.size(); j++) {
                int reserveNum = reserveList.get(j);

                if (lostNum == reserveNum || lostNum == reserveNum - 1 || lostNum == reserveNum + 1) {
                    reserveList.remove(j);
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 5;
        int l[] = {1, 2, 4};
        int r[] = {1, 3, 5};
        int ans = solution(n, l, r);
        System.out.println(ans);
    }
}
