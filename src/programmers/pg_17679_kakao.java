package programmers;

public class pg_17679_kakao {
    private static boolean[][] visit;
    private static String[][] map;
    private static int[] deleteCnt;
    private static int ans = 0;

    public static void main(String[] args) {
        int m = 6;
        int n = 6;
        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        System.out.println(solution(m, n, board));
    }

    private static int solution(int m, int n, String[] board) {
        visit = new boolean[m][n];
        map = new String[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].split("");
        }

        while (true) {
            deleteCnt = new int[n];
            boolean flag = false;
            for (int i = m - 1; i > 0; i--) {
                for (int j = 0; j < n - 1; j++) {
                    if (isDelete(i, j)) {
                        flag = true;
                        changeDeleteCnt(i, j);
                    }
                }
            }
            if (!flag) break;

            for (int i = m - 1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    if (visit[i][j]) {
                        int row = i - deleteCnt[j];
                        if (row >= 0) {
                            map[i][j] = map[row][j];
                            visit[row][j] = true;
                            visit[i][j] = false;
                        }
                    }
                }
            }
        }
        return ans;
    }

    private static boolean isDelete(int x, int y) {
        return map[x][y].equals(map[x][y + 1]) && map[x][y].equals(map[x - 1][y]) && map[x][y].equals(map[x - 1][y + 1]);
    }

    private static void changeDeleteCnt(int x, int y) {
        if (!visit[x][y]) {
            visit[x][y] = true;
            deleteCnt[y]++;
            ans++;
        }
        if (!visit[x][y + 1]) {
            visit[x][y + 1] = true;
            deleteCnt[y + 1]++;
            ans++;
        }
        if (!visit[x - 1][y]) {
            visit[x - 1][y] = true;
            deleteCnt[y]++;
            ans++;
        }
        if (!visit[x - 1][y + 1]) {
            visit[x - 1][y + 1] = true;
            deleteCnt[y + 1]++;
            ans++;
        }
    }
}

//visit[][]변수를 만들어서 삭제할수있는 block이면 true로 만들어줌
//deleteCnt[]는 col을 가준으로 cnt를 ++시켜줌
//아래서부터 찾다가 visit[][] true를 만나면 deleteCnt[col] count만큼 위에있는 block을 현위치로 끌어다내림
//끌어다 내린 부분도 visit[][] true로 바꿔줌 그래야 그 위에있는것도 계속 내릴수 있음
//끌어다 내렸으면 현 위치 다시 false로 바꿔줌
//모두 끌어다 내렸으면 다시 첫번째부터 반복