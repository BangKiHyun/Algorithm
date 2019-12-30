package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class pg_42890_kakao {
    private static boolean[] visit;
    private static boolean[] check;
    private static int ans = 0;
    private static boolean flag;
    private static ArrayList<Integer> list;

    public static void main(String[] args) {
        String[][] relation = {{"a","1","4"},{"2","1","5"},{"a","2","4"}};
        System.out.println(solution(relation));
    }

    private static int solution(String[][] relation) {
        visit = new boolean[relation[0].length];
        check = new boolean[relation[0].length];

        for (int i = 0; i < relation.length; i++) {
            flag = false;
            list = new ArrayList<>();
            goDFS(i, relation, 0, 0);
            if (flag) {
                for (int n : list) {
                    visit[n] = true;
                }
            }
        }

        return ans;
    }

    private static void goDFS(int depth, String[][] relation, int cnt, int start) {
        if (depth == cnt) {
            if (isCandidateKey(relation)) {
                ans++;
                flag = true;
                for (int i = 0; i < relation[0].length; i++) {
                    if (check[i]){
                        list.add(i);
                    }
                }
            }
            return;
        }

        for (int i = start; i < relation[0].length; i++) {
            if (!visit[i] && !check[i]) {
                check[i] = true;
                goDFS(depth, relation, cnt + 1, i + 1);
                check[i] = false;
            }
        }
    }

    private static boolean isCandidateKey(String[][] relation) {
        Set<String> set = new HashSet<>();
        String[] key = new String[relation.length];
        Arrays.fill(key, "");

        for (int i = 0; i < relation.length; i++) {
            for (int j = 0; j < relation[0].length; j++) {
                if (check[j]) {
                    key[i] += relation[i][j];
                }
            }
            if (!set.contains(key[i])) set.add(key[i]);
            else return false;
        }
        return true;
    }
}

/*
{{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};*/

//{{"a","b","c"},{"1","b","c"},{"a","b","4"},{"a","5","c"}};
//{{"a","a","a"},{"a","a","a"},{"a","a","a"},{"a","a","a"}};
//{{"a","1","4"},{"2","1","5"},{"a","2","4"}};