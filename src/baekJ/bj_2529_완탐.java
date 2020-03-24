package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//두 종류의 부등호 기호 ‘<’와 ‘>’가 k개 나열된 순서열  A가 있다.
//우리는 이 부등호 기호 앞뒤에 서로 다른 한 자릿수 숫자를 넣어서 모든 부등호 관계를 만족시키려고 한다.

//여러분은 제시된 k개의 부등호 순서를 만족하는 (k+1)자리의 정수 중에서 최댓값과 최솟값을 찾아야 한다.
//앞서 설명한 대로 각 부등호의 앞뒤에 들어가는 숫자는 { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }중에서 선택해야 하며 선택된 숫자는 모두 달라야 한다.
public class bj_2529_완탐 {
    private static int n;
    private static String[] sign;
    private static boolean visit[] = new boolean[10];
    private static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sign = new String[n];
        sign = br.readLine().split(" ");

        for (int i = 0; i < 10; i++) {
            visit[i] = true;
            goDFS(i, 0, i + "");
            visit[i] = false;
        }

        System.out.println(list.get(list.size() - 1) + "\n" + list.get(0));
    }

    private static void goDFS(int num, int depth, String StrNum) {
        if (depth == n) {
            list.add(StrNum);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!visit[i]) {
                if (sign[depth].equals("<")) {
                    if (num >= i) continue;
                } else {
                    if (num <= i) continue;
                }
                visit[i] = true;
                goDFS(i, depth + 1, StrNum + i);
                visit[i] = false;
            }
        }
    }
}
