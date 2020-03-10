package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

//N×M 모양의 게시판에 구멍이 뚫려 있다. 이를 폭이 1인 테이프를 이용하여 막으려 한다.
//테이프의 길이는 무한하다고 생각해도 좋지만, 테이프를 끊어내는 횟수를 최소로 하려 한다.
//테이프를 붙일 때에는 구멍이 뚫려 있지 않은 부분을 막아서는 안 된다.
//하지만 테이프가 한 번 붙은 곳에 테이프를 또 붙여도 된다. 또한, 테이프를 붙일 때에는 가로나 세로로 붙이는 경우만 허용한다.
public class bj_2414_이분매칭 {
    private static final String HOLE = "*";

    private static int[] row;
    private static boolean[] check;
    private static ArrayList<Integer>[] connected_list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 N, M(1≤N, M≤50)이 주어진다. 다음 N개의 줄에는 M개의 문자로 게시판의 모양이 주어진다.
        //각각의 문자는 붙어 있으며, 구멍이 없는 부분은 '.', 구멍이 있는 부분은 '*'으로 주어진다.
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        row = new int[m * n];
        check = new boolean[m * n];
        connected_list = new ArrayList[m * n];
        for (int i = 0; i < m * n; i++) {
            connected_list[i] = new ArrayList<>();
        }

        int[][] standard_row = new int[n][m];
        int[][] standard_col = new int[n][m];

        //Numbering Row
        int cnt_row = 0;
        for (int i = 0; i < n; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                if (HOLE.equals(input[j])) {
                    if (j != 0 && standard_row[i][j - 1] != 0) {
                        standard_row[i][j] = standard_row[i][j - 1];
                    } else standard_row[i][j] = ++cnt_row;
                }
            }
        }

        //Numbering Col
        int cnt_col = 0;
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (standard_row[i][j] != 0) {
                    if (i != 0 && standard_col[i - 1][j] != 0) {
                        standard_col[i][j] = standard_col[i - 1][j];
                    } else standard_col[i][j] = ++cnt_col;
                    connected_list[standard_row[i][j]].add(standard_col[i][j]);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= cnt_row; i++) {
            Arrays.fill(check, false);
            if (canAttached(i)) ans++;
        }

        System.out.println(ans);
    }

    private static boolean canAttached(int col) {
        for (int tape : connected_list[col]) {
            if (check[tape]) continue;
            check[tape] = true;

            if (row[tape] == 0 || canAttached(row[tape])) {
                row[tape] = col;
                return true;
            }
        }
        return false;
    }
}
