package programmers;

import java.util.*;

public class pg_42587 {
    static public int solution(int[] priorities, int location) {
        int answer = 1;

        PriorityQueue priority = new PriorityQueue<>(Collections.reverseOrder());

        for(int task : priorities){
            priority.add(task);
        }

        while(!priority.isEmpty()){
            for(int i=0; i<priorities.length; i++){
                if(priorities[i] == (int)priority.peek()) {
                    if(i == location){
                        return answer;
                    }
                    priority.poll();
                    answer++;
                }
            }
        }
        return answer;
    }


    public static void main(String[] args) {
        int p[] = {1, 1, 9, 1, 1, 1};
        int l = 0;
        int result = solution(p, l);
        System.out.println(result);
    }
}

