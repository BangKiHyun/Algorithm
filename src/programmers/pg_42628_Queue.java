package programmers;

import java.util.PriorityQueue;

public class pg_42628_Queue {
    static PriorityQueue q = new PriorityQueue();

    public static void main(String[] args) {
        String[] op = {"I 7", "I 5", "I -5", "D -1"};
        //String[] op = {"I 16" , "D 1"};
        int[] ans = solution(op);
        if (ans.length == 0){
            System.out.println("0 0");
        }else{
            System.out.println(ans[ans.length-1]+ " " + ans[0]);
        }
    }

    static public int[] solution(String[] operations) {
        String text, num = null;
        for (int i = 0; i < operations.length; i++) {
            text = operations[i].substring(0, 1);
            if (operations[i].length() == 3) {
                num = operations[i].substring(2, 3);
            } else if (operations[i].length() == 4) {
                num = operations[i].substring(2, 4);
            }
            if (text.equals("I")) {
                q.offer(num);
            } else if (text.equals("D")) {
                if (operations[i].substring(2, 3).equals("-")) {
                    q.remove(q.peek());
                } else {
                    q = removelast(q);
                }
            }
        }
        int size = q.size();
        int[] answer = new int[size];
        for (int i = 0; i < size; i++) {
            answer[i] = Integer.parseInt((String) q.poll());
        }
        return answer;
    }

    static PriorityQueue removelast(PriorityQueue pq)
    {

        PriorityQueue pqnew = new PriorityQueue();

        while(pq.size() > 1)
        {
            pqnew.add(pq.poll());
        }

        pq.clear();
        return pqnew;
    }
}
