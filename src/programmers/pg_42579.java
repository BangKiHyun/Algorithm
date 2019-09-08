package programmers;

import java.util.*;

public class pg_42579 {
    public static void main(String[] args) {
        String[] g = {"classic", "pop", "classic", "classic", "pop"};
        int[] p = {500, 600, 150, 800, 2500};

        int[] ans = solution(g, p);
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(String[] generes, int[] plays) {
        HashMap<Integer, String> gMap = new HashMap<>();
        HashMap<Integer, Integer> pMap = new HashMap<>();
        HashSet<String> gSet = new HashSet<>();

        for (int i = 0; i < generes.length; i++) {
            gMap.put(i, generes[i]);
            pMap.put(i, plays[i]);
            gSet.add(generes[i]);
        }

        HashMap<Integer, String> cntPlay = new HashMap<>();
        for (String s : gSet) {
            int cnt = 0;
            for (int i = 0; i < generes.length; i++) {
                if (gMap.get(i).equals(s)) {
                    cnt += pMap.get(i);
                }
            }
            cntPlay.put(cnt, s);
        }

        TreeMap sort = new TreeMap(cntPlay);
        String[] sortGenre = new String[gSet.size()];
        int index = 0;
        for (Object o : sort.keySet()) {
            sortGenre[index] = sort.get(o).toString();
            index++;
        }

        ArrayList<Integer> fIndex = new ArrayList<Integer>();
        for (int i = sortGenre.length - 1; i >= 0; i--) {
            int count = 0;
            for (int p1 : gMap.keySet()) {
                if (sortGenre[i].equals(gMap.get(p1))) {
                    count++;
                }
            }

            int[] temp = new int[count];
            int k = 0;
            for (int p2 : gMap.keySet()) {
                if (sortGenre[i].equals(gMap.get(p2))) {
                    temp[k] = pMap.get(p2);
                    k++;
                }
            }

            if (temp.length != 1) {
                Arrays.sort(temp);
                for (int j = temp.length - 1; j >= temp.length - 2; j--) {
                    for (int p : pMap.keySet()) {
                        if (temp[j] == pMap.get(p)) {
                            fIndex.add(p);
                            pMap.put(p, 0);
                            break;
                        }
                    }
                }
            } else {
                for (int p : pMap.keySet()) {
                    if (temp[0] == pMap.get(p)) {
                        fIndex.add(p);
                        pMap.put(p, 0);
                        break;
                    }
                }
            }
        }

        int[] answer = new int[fIndex.size()];

        for (int i = 0; i < fIndex.size(); i++) {
            answer[i] = fIndex.get(i);
        }

        return answer;
    }
}
