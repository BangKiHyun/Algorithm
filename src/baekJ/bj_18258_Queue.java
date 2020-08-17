package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class bj_18258_Queue {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> q = new LinkedList<>();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();

            if (command.equals("push")) {
                q.add(Integer.parseInt(st.nextToken()));
            } else {
                solution(q, command);
            }
        }

        System.out.println(sb);
    }

    private static void solution(final Deque<Integer> q, final String command) {
        switch (command) {
            case "pop":
                if (q.isEmpty()) {
                    sb.append("-1\n");
                    break;
                }
                sb.append(q.poll()).append("\n");
                break;
            case "size":
                sb.append(q.size()).append("\n");
                break;
            case "empty":
                if(q.isEmpty()){
                    sb.append(1 + "\n");
                    break;
                }else{
                    sb.append(0 + "\n");
                    break;
                }
            case "front":
                if (q.isEmpty()) {
                    sb.append(-1 + "\n");
                    break;
                }
                sb.append(q.peek()).append("\n");
                break;
            case "back":
                if (q.isEmpty()) {
                    sb.append(-1 + "\n");
                    break;
                }
                sb.append(q.peekLast()).append("\n");
                break;
        }
    }
}
