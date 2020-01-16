package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class bj_4949_문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //문자열에 포함되는 괄호는 소괄호("()") 와 대괄호("[]")로 2종류이고, 문자열이 균형을 이루는 조건은 아래와 같다.
        //
        //모든 왼쪽 소괄호("(")는 오른쪽 소괄호(")")와만 짝을 이룰 수 있다.
        //모든 왼쪽 대괄호("[")는 오른쪽 대괄호("]")와만 짝을 이룰 수 있다.
        //모든 오른쪽 괄호들은 자신과 짝을 이룰 수 있는 왼쪽 괄호가 존재한다.
        //모든 괄호들의 짝은 1:1 매칭만 가능하다. 즉, 괄호 하나가 둘 이상의 괄호와 짝지어지지 않는다.
        //짝을 이루는 두 괄호가 있을 때, 그 사이에 있는 문자열도 균형이 잡혀야 한다.

        //하나 또는 여러줄에 걸쳐서 문자열이 주어진다. 각 문자열은 영문 알파벳, 공백, 소괄호("( )") 대괄호("[ ]")등으로 이루어져 있으며, 길이는 100글자보다 작거나 같다.

        while (true) {
            //하나 또는 여러줄에 걸쳐서 문자열이 주어진다. 각 문자열은 영문 알파벳, 공백, 소괄호("( )") 대괄호("[ ]")등으로 이루어져 있으며, 길이는 100글자보다 작거나 같다.
            String line = br.readLine();

            //입력의 종료조건으로 맨 마지막에 점 하나(".")가 들어온다.
            if (line.equals(".")) break;

            int small_bracket = 0;
            int big_bracket = 0;
            Stack<String> recent_bracket = new Stack<>();
            recent_bracket.push(" ");
            for (int i = 0; i < line.length(); i++) {
                String split = line.substring(i, i + 1);
                if (!isBracket(split)) continue;
                else {
                    switch (split) {
                        case "(":
                            small_bracket++;
                            recent_bracket.push(split);
                            break;
                        case ")":
                            if (!recent_bracket.peek().equals("(")) {
                                small_bracket = 0;
                            }
                            small_bracket--;
                            recent_bracket.pop();
                            break;
                        case "[":
                            big_bracket++;
                            recent_bracket.push(split);
                            break;
                        case "]":
                            if (!recent_bracket.peek().equals("[")) {
                                big_bracket = 0;
                            }
                            big_bracket--;
                            recent_bracket.pop();
                            break;
                    }
                }

                if (small_bracket < 0 || big_bracket < 0) break;

            }
            if (isBalanced(small_bracket, big_bracket)) System.out.println("no");
            else System.out.println("yes");
        }
    }

    private static boolean isBracket(String str) {
        return str.equals("(") || str.equals(")") || str.equals("[") || str.equals("]");
    }

    private static boolean isBalanced(int small_bracket, int big_bracket) {
        return small_bracket != 0 || big_bracket != 0;
    }
}
//test case
//So when I die (the [first] I will see in (heaven) is a score list).
//[ first in ] ( first out ).
//Half Moon tonight (At least it is better than no Moon at all].
//A rope may form )( a trail in a maze.
//Help( I[m being held prisoner in a fortune cookie factory)].
//([ (([( [ ] ) ( ) (( ))] )) ]).
// .
//.