package programmers;

import java.util.*;

public class pg_42579_Re {
    public static void main(String[] args) {
        String[] g = //{"classic", "pop", "classic", "classic", "pop"};
                //{"classic", "pop", "classic", "pop", "classic", "classic"};
                //{"a", "b", "a", "b", "c"};
                {"a"};

        int[] p = //{500, 600, 150, 800, 2500};
                //{400, 600, 150, 2500, 500, 500};
                //{100, 200, 300, 400, 500};
                {1};

        int[] ans = solution(g, p);
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    private static int[] solution(String[] genres, int[] plays) {
        int length = genres.length;

        if (length == 1) {
            return new int[]{0};
        }

        Set<String> gSet = new HashSet<>();
        for (int i = 0; i < length; i++) {
            gSet.add(genres[i]);
        }

        TreeMap<Integer, String> treeMap = new TreeMap<>(Collections.reverseOrder());

        Iterator<String> iterator = gSet.iterator();
        while (iterator.hasNext()) {
            int cnt = 0;
            String name = iterator.next();
            for (int i = 0; i < length; i++) {
                if (name.equals(genres[i])) {
                    cnt += plays[i];
                }
            }
            treeMap.put(cnt, name);
        }

        ArrayList<Integer> find_idx = new ArrayList<>();
        int[] temp = new int[length];
        int k = 0;
        int cnt = 0;

        for (int idx : treeMap.keySet()) {
            String name = treeMap.get(idx);
            for (int i = 0; i < length; i++) {
                if (name.equals(genres[i])) {
                    temp[k] = plays[i];
                    k++;
                }
            }

            Arrays.sort(temp);
            for (int i = length - 1; i >= length - 2; i--) {
                if (temp[i] == 0) break;
                cnt++;

                for (int j = 0; j < length; j++) {
                    if (name.equals(genres[j]) && temp[i] == plays[j]) {
                        find_idx.add(j);
                        plays[j] = 0;
                        break;
                    }
                }
            }
            k = 0;
            Arrays.fill(temp, 0);
        }

        int[] answer = new int[cnt];

        for (int i = 0; i < cnt; i++) {
            answer[i] = find_idx.get(i);
        }

        return answer;
    }
}
