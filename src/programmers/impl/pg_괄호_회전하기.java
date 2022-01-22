package programmers.impl;

import java.util.*;

public class pg_괄호_회전하기 {

    private Map<Character, Character> map = new HashMap<>();

    public static void main(String[] args) {
        final pg_괄호_회전하기 task = new pg_괄호_회전하기();
        String s = "[](){}";
        System.out.println(task.solution(s));
    }

    public int solution(String s) {
        int answer = 0;
        initMap();
        Queue<Character> q = new LinkedList<>();
        for (char c : s.toCharArray()) {
            q.add(c);
        }

        for (int idx = 0; idx < s.length() - 1; idx++) {
            if (isCorrect(q)) {
                answer++;
            }
            final Character c = q.remove();
            q.add(c);
        }
        return answer;
    }

    private void initMap() {
        map.put('[', ']');
        map.put('(', ')');
        map.put('{', '}');
    }

    private boolean isCorrect(Queue<Character> q) {
        int leftBlockCnt = 0;
        Stack<Character> stack = new Stack<>();
        for (char item : q) {
            if (item == '[' || item == '(' || item == '{') {
                leftBlockCnt++;
                stack.push(item);
            } else {
                if (--leftBlockCnt < 0) return false;
                if (map.get(stack.pop()) != item) return false;
            }
            if (leftBlockCnt < 0) return false;
        }
        return leftBlockCnt == 0;
    }
}
