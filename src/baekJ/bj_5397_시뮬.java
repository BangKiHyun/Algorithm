package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//창영이는 강산이의 비밀번호를 훔치기 위해서 강산이가 사용하는 컴퓨터에 키로거를 설치했다.
//며칠을 기다린 끝에 창영이는 강산이가 비밀번호 창에 입력하는 글자를 얻어냈다.
//
//키로거는 사용자가 키보드를 누른 명령을 모두 기록한다. 따라서, 강산이가 비밀번호를 입력할 때,
//화살표나 백스페이스를 입력해도 정확한 비밀번호를 알아낼 수 있다.
//
//강산이가 비밀번호 창에서 입력한 키가 주어졌을 때, 강산이의 비밀번호를 알아내는 프로그램을 작성하시오.
public class bj_5397_시뮬 {
    private static final String LEFT = "<";
    private static final String RIGHT = ">";
    private static final String DELETE = "-";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            List<String> list = Arrays.asList(
                    br.readLine().split(""));

            Stack<String> left = new Stack<>();
            Stack<String> right = new Stack<>();

            for (String text : list) {
                if (text.equals(LEFT) && !left.empty()) {
                    right.push(left.pop());
                } else if (text.equals(RIGHT) && !right.empty()) {
                    left.push(right.pop());
                } else if (text.equals(DELETE) && !left.empty()) {
                    left.pop();
                } else if (!text.equals(LEFT) && !text.equals(RIGHT) && !text.equals(DELETE)) {
                    left.push(text);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (String text : left) {
                sb.append(text);
            }
            while (!right.empty()) {
                sb.append(right.pop());
            }

            System.out.println(sb);
        }
    }
}
