package programmers;

import java.util.*;
import java.util.stream.Collectors;

public class pg_42579_Hash {
    public static void main(String[] args) {
        String[] genres = {"classic", "classic", "classic", "classic", "pop"};
        //{"classic", "pop", "classic", "pop", "classic", "classic"};
        int[] plays = {500, 150, 800, 800, 2500};

        final int[] answers = solution(genres, plays);
        for (int ans : answers) {
            System.out.print(ans + " ");
        }
    }

    public static int[] solution(String[] genres, int[] plays) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        int length = genres.length;
        for (int i = 0; i < length; i++) {
            if (!map.containsKey(genres[i])) {
                map.put(genres[i], plays[i]);
            } else {
                int play = map.get(genres[i]) + plays[i];
                map.put(genres[i], play);
            }
        }
        final LinkedHashMap<String, Integer> sortMap = sortMapByValue(map);

        List<Integer> ans = new ArrayList<>();
        for (String genre : sortMap.keySet()) {
            List<Play> tempList = new ArrayList<>();

            for (int i = 0; i < length; i++) {
                if (genres[i].equals(genre)) {
                    tempList.add(new Play(i, plays[i]));
                }
            }
            tempList.sort(new PlayComparator());

            if(tempList.size() == 1){
                ans.add(tempList.get(0).idx);
            }else{
                ans.add(tempList.get(0).idx);
                ans.add(tempList.get(1).idx);
            }
        }

        int[] ret = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ret[i] = ans.get(i);
        }
        return ret;
    }

    private static LinkedHashMap<String, Integer> sortMapByValue(LinkedHashMap<String, Integer> map) {
        List<Map.Entry<String, Integer>> entries = new LinkedList<>(map.entrySet());
        entries.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

        return entries.stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new)
                );
    }

    private static class Play {
        private int idx;
        private int cnt;

        public Play(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    private static class PlayComparator implements Comparator<Play> {

        @Override
        public int compare(Play o1, Play o2) {
            if (o1.cnt - o2.cnt < 0) {
                return 1;
            } else if (o1.cnt == o2.cnt) {
                return o1.idx - o2.idx;
            }
            return -1;
        }
    }
}
