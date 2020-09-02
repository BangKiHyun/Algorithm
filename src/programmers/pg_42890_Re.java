package programmers;

import java.util.*;

public class pg_42890_Re {
    private static boolean[] visit;
    private static boolean[] check;
    private static Deque<Integer> visitQueue;
    private static List<Integer> list;
    private static int ans = 0;

    public static void main(String[] args) {
        String[][] relation = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};
        System.out.println(solution(relation));
    }

    private static int solution(String[][] relation) {
        visit = new boolean[relation[0].length];
        check = new boolean[relation[0].length];

        for (int i = 1; i <= relation[0].length; i++) {
            visitQueue = new LinkedList<>();
            list = new ArrayList<>();
            goDFS(relation, i, 0, 0);

            for(int candidate : list){
                check[candidate] = true;
            }
        }

        return ans;
    }

    private static void goDFS(String[][] relation, int depth, int cnt, int startIdx) {
        if (depth == cnt) {
            if (isCandidate(relation)) {
                ans++;
                list.addAll(visitQueue);
                return;
            }
        }

        for (int i = startIdx; i < relation[0].length; i++) {
            if (!check[i] && !visit[i]) {
                visit[i] = true;
                visitQueue.offer(i);
                goDFS(relation, depth, cnt + 1, i + 1);
                visitQueue.pollFirst();
                visit[i] = false;
            }
        }
    }

    private static boolean isCandidate(final String[][] relation) {
        Set<String> set = new HashSet<>();
        String[] key = new String[relation.length];
        Arrays.fill(key, "");

        for (int candidate : visitQueue) {
            for (int i = 0; i < relation.length; i++) {
                key[i] += relation[i][candidate];
            }
        }

        for (int i = 0; i < relation.length; i++) {
            if (!set.contains(key[i])) {
                set.add(key[i]);
            } else return false;
        }
        return true;
    }
}
