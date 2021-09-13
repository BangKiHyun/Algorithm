package programmers.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pg_단체사진_찍기 {
    private List<Condition> conditionList = new ArrayList<>();
    private Map<Integer, String> posNameMap = new HashMap<>();
    private boolean[] visit;
    private int answer = 0;
    private static final int N = 8;

    public static void main(String[] args) {
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
        final pg_단체사진_찍기 task = new pg_단체사진_찍기();
        System.out.println(task.solution(n, data));
    }

    public int solution(int n, String[] data) {
        for (String condition : data) {
            final String[] conditionInfo = condition.split("");
            conditionList.add(new Condition(conditionInfo[0], conditionInfo[2],
                    conditionInfo[3], Integer.parseInt(conditionInfo[4])));
        }
        initPosNameMap();
        visit = new boolean[N + 1];
        recursion(new ArrayList<>());
        return answer;
    }

    private void recursion(ArrayList<String> list) {
        if (N == list.size()) {
            if (isSatisfied(list)) answer++;
            return;
        }

        for (int idx = 1; idx <= N; idx++) {
            if (!visit[idx]) {
                visit[idx] = true;
                list.add(posNameMap.get(idx));
                recursion(list);
                list.remove(posNameMap.get(idx));
                visit[idx] = false;
            }
        }
    }

    private boolean isSatisfied(ArrayList<String> list) {
        for (Condition condition : conditionList) {
            if (!condition.satisfied(list)) return false;
        }
        return true;
    }

    private void initPosNameMap() {
        posNameMap.put(1, "A");
        posNameMap.put(2, "C");
        posNameMap.put(3, "F");
        posNameMap.put(4, "J");
        posNameMap.put(5, "M");
        posNameMap.put(6, "N");
        posNameMap.put(7, "R");
        posNameMap.put(8, "T");
    }

    private static class Condition {
        private String self;
        private String other;
        private String command;
        private int distance;

        public Condition(String self, String other, String command, int distance) {
            this.self = self;
            this.other = other;
            this.command = command;
            this.distance = distance + 1;
        }

        public boolean satisfied(ArrayList<String> list) {
            int firstPos = findPos(self, list);
            int secondPos = findPos(other, list);
            final int distance = Math.abs(firstPos - secondPos);
            if (command.equals("=")) return distance == this.distance;
            if (command.equals("<")) return distance < this.distance;
            if (command.equals(">")) return distance > this.distance;
            return false;
        }

        private int findPos(String target, ArrayList<String> list) {
            int pos = 0;
            for (String name : list) {
                if (name.equals(target)) break;
                pos++;
            }
            return pos;
        }
    }
}
