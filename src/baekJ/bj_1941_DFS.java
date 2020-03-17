package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//이름이 이름인 만큼, 7명의 여학생들로 구성되어야 한다.
//강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
//화합과 번영을 위해, 반드시 ‘이다솜파’의 학생들로만 구성될 필요는 없다.
//그러나 생존을 위해, ‘이다솜파’가 반드시 우위를 점해야 한다. 따라서 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.
//여학생반의 자리 배치도가 주어졌을 때, ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수를 구하는 프로그램을 작성하시오.
public class bj_1941_DFS {
    private static final int LENGTH = 5;
    private static final int MAX = LENGTH * LENGTH;
    private static final int S = 1;
    private static final int Y = 2;

    private static int ans = 0;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int[][] map = new int[LENGTH][LENGTH];
    private static boolean[][] visit = new boolean[LENGTH][LENGTH];
    private static boolean[][] check;

    public static void main(String[] args) throws IOException {
        //'S'(이다‘솜’파의 학생을 나타냄) 또는 'Y'(임도‘연’파의 학생을 나타냄)을 값으로 갖는 5*5 행렬이 공백 없이 첫째 줄부터 다섯 줄에 걸쳐 주어진다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < 5; j++) {
                if ("Y".equals(input[j])) map[i][j] = Y;
                else map[i][j] = S;
            }
        }

        goDFS(0, 0, 0);

        System.out.println(ans);
    }

    private static void goDFS(int start, int depth, int som) {
        if (depth == 7) {
            if (som < 4) return;

            check = new boolean[LENGTH][LENGTH];
            int x = start / LENGTH;
            int y = start % LENGTH;
            if (isConnected(x, y, 1)) ans++;

            return;
        }

        for (int i = start; i < MAX; i++) {
            int x = i / LENGTH;
            int y = i % LENGTH;
            if (!visit[x][y]) {
                visit[x][y] = true;
                if (map[x][y] == S) goDFS(i, depth + 1, som + 1);
                else goDFS(i, depth + 1, som);
                visit[x][y] = false;
            }
        }
    }

    private static boolean isConnected(int x, int y, int depth) {
        if (depth == 7) return true;

        check[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValue(nx, ny)) {
                depth++;
                if (isConnected(nx, ny, depth)) return true;
            }
        }
        return false;
    }

    private static boolean isValue(int x, int y) {
        return x >= 0 && y >= 0 && x < LENGTH && y < LENGTH && visit[x][y] && !check[x][y];
    }
}
/*
false false false false false
true true true true true
false true false false false
false true false false false
false false false false false
 */