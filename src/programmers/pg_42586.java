/*
package programmers;

import java.util.LinkedList;

public class pg_42586 {
    static public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int sum = progresses[i];
            int cnt = 0;
            while (sum < 100) {
                cnt++;
                sum += speeds[i];
            }
            q.push(cnt);
        }
        int n = q.poll();
        int count = 0;
        while (!q.isEmpty()){
            ++count;
            int n1 = q.poll();
            if(n1 <= n){
                count++;
                n = q.poll();
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] p = {93, 30, 55};
        int[] s = {1, 30, 5};
        int a[] = new int[p.length];
        a = solution(p, s);
    }
}
*/
