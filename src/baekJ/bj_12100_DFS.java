package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//이 게임에서 한 번의 이동은 보드 위에 있는 전체 블록을 상하좌우 네 방향 중 하나로 이동시키는 것이다.
//이때, 같은 값을 갖는 두 블록이 충돌하면 두 블록은 하나로 합쳐지게 된다. 한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다. (실제 게임에서는 이동을 한 번 할 때마다 블록이 추가되지만, 이 문제에서 블록이 추가되는 경우는 없다)
//마지막으로, 똑같은 수가 세 개가 있는 경우에는 이동하려고 하는 쪽의 칸이 먼저 합쳐진다.
//예를 들어, 위로 이동시키는 경우에는 위쪽에 있는 블록이 먼저 합쳐지게 된다. <그림 14>의 경우에 위로 이동하면 <그림 15>를 만든다.
//
//이 문제에서 다루는 2048 게임은 보드의 크기가 N×N 이다.
// 보드의 크기와 보드판의 블록 상태가 주어졌을 때, 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구하는 프로그램을 작성하시오.
public class bj_12100_DFS {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int max = 0;
    private static final int EMPTY = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에 보드의 크기 N (1 ≤ N ≤ 20)이 주어진다.
        int n = Integer.parseInt(br.readLine());

        //둘째 줄부터 N개의 줄에는 게임판의 초기 상태가 주어진다.
        // 0은 빈 칸을 나타내며, 이외의 값은 모두 블록을 나타낸다.
        // 블록에 쓰여 있는 수는 2보다 크거나 같고, 1024보다 작거나 같은 2의 제곱꼴이다.
        // 블록은 적어도 하나 주어진다.
        int[][] board = new int[n][n];
        String[] line;
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(line[0]);
                max = Math.max(max, board[i][j]);
            }
        }

        move(board, n, 0);
    }

    private static void move(int[][] board, int n, int depth) {
        if (depth == 5) return;


        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                up(board, n);
            } else if (i == 1) {
                down(board, n);
            } else if (i == 2) {
                left(board, n);
            } else {
                right(board, n);
            }

            move(board, n, depth + 1);
        }
    }

    private static void up(int[][] board, int n) {
        Node first, second;
        int temp = 0;
        int cnt;

        for (int j = 0; j < n; j++) {
            cnt = 0;
            first = null;
            second = null;
            for (int i = 0; i < n; i++) {
                if (board[i][j] != EMPTY) {
                    if (cnt % 2 == 0) {
                        first = new Node(i, j, board[i][j]);
                    } else if (cnt % 2 == 1) {
                        second = new Node(i, j, board[i][j]);

                        if (first.num == second.num) {
                            board[first.x][first.y] = first.num * 2;
                            board[second.x][second.y] = EMPTY;
                            second = null;
                            first = null;
                        }
                    }
                    cnt++;
                }
            }
        }
    }

    private static void down(int[][] board, int n) {
        int now, next;

        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > 0; j--) {
                now = board[i][j];
                next = board[i][j - 1];

                if (isSame(now, next)) {
                    board[i][j] = combine(now, next);
                    board[i][j - 1] = EMPTY;
                    j--;
                } else if (now == EMPTY) {

                }
            }
        }
    }

    private static void left(int[][] board, int n) {

    }

    private static void right(int[][] board, int n) {

    }

    private static boolean isSame(int now, int next) {
        return now == next && now != EMPTY && next != EMPTY;
    }

    private static int combine(int now, int next) {
        return now + next;
    }

    private static class Node {
        private int x;
        private int y;
        private int num;

        public Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}

//상하좌우 순으로 board를 움직인다.
//움직인 방향에 따라 now와 next의 값이 같으면 now값에 합쳐준다.
//now와 next의 값이 다르면 그다음값으로 넘어간다
//next의 값이 n의 범위를 벗어나면 끝
//상하좌우로 5번씩 다 반복하면서 max값을 계속 갱신해준다

//EMPTY 가 아닌 값을 찾으면 cnt++
//다 돌린 후 cnt==0 이면 그냥 return
//cnt = 1이면 그전 EMPTY 였던 값에 대입
//cnt = 2이면 cnt==1 이였던 곳에 cnt==2 였던 놈을 비교 한 후 합침
