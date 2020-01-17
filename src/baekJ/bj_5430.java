package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//선영이는 주말에 할 일이 없어서 새로운 언어 AC를 만들었다. AC는 정수 배열에 연산을 하기 위해 만든 언어이다. 이 언어에는 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.
//
//함수 R은 배열에 있는 숫자의 순서를 뒤집는 함수이고, D는 첫 번째 숫자를 버리는 함수이다. 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
public class bj_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 테스트 케이스의 개수 T가 주어진다. T는 최대 100이다.
        int test_case = Integer.parseInt(br.readLine());

        //각 테스트 케이스의 첫째 줄에는 수행할 함수 p가 주어진다. p의 길이는 1보다 크거나 같고, 100,000보다 작거나 같다.
        //다음 줄에는 배열에 들어있는 수의 개수 n이 주어진다. (0 ≤ n ≤ 100,000)
        //다음 줄에는 [x1,...,xn]과 같은 형태로 배열에 들어있는 수가 주어진다. (1 ≤ xi ≤ 100)
        //전체 테스트 케이스에 주어지는 p의 길이의 합과 n의 합은 70만을 넘지 않는다.
        for (int i = 0; i < test_case; i++) {
            String op = br.readLine();
            char[] arr_op = op.toCharArray();

            int size = Integer.parseInt(br.readLine());

            String str_arr = br.readLine();
            Queue<Integer> q = new LinkedList<>();
            if (size != 0) {
                int start = 1;
                for (int j = 2; j < str_arr.length(); j++) {
                    String split = str_arr.substring(j, j + 1);
                    if (split.equals(",") || split.equals("]")) {
                        q.add(Integer.parseInt(str_arr.substring(start, j)));
                        if(split.equals("]")) break;
                        start = j + 1;
                    }
                }
            }

            int reverse_cnt = 0;
            boolean flag = true;

            for (int j = 0; j < arr_op.length; j++) {
                if (arr_op[j] == 'R') {
                    reverse_cnt++;
                } else {
                    if (q.isEmpty()) {
                        flag = false;
                        break;
                    }
                    if (reverse_cnt % 2 == 0) q.poll();
                    else ((LinkedList<Integer>) q).pollLast();
                }
            }

            if (!flag) System.out.println("error");
            else {
                StringBuilder ans = new StringBuilder();
                ans.append("[");
                if (reverse_cnt % 2 == 0) {
                    while (!q.isEmpty()) {
                        ans.append(q.poll());
                        if (!q.isEmpty()) ans.append(",");
                    }
                } else {
                    while (!q.isEmpty()) {
                        ans.append(((LinkedList<Integer>) q).pollLast());
                        if (!q.isEmpty()) ans.append(",");
                    }
                }
                ans.append("]");
                System.out.println(ans);
            }
        }
    }
}