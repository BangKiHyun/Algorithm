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
    private static final int EMPTY = 0;
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    private static int max = -1;

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
                board[i][j] = Integer.parseInt(line[j]);
            }
        }

        move(board, n, 1);

        System.out.println(max);
    }

    private static void move(int[][] board, int n, int depth) {
        if (depth == 5) {
            checkMaxValue(board, n);
            return;
        }

        int[][] copy;
        copy = up(board, n);
        move(copy, n, depth + 1);

        copy = right(board, n);
        move(copy, n, depth + 1);

        copy = left(board, n);
        move(copy, n, depth + 1);

        copy = down(board, n);
        move(copy, n, depth + 1);
    }


    private static void checkMaxValue(int[][] board, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, board[i][j]);
            }
        }
    }

    private static int[][] up(int[][] board, int n) {
        int[][] copy = deleteEmpty(board, n, UP);
        mergeBoard(copy, n, UP);
        copy = deleteEmpty(copy, n, UP);

        return copy;
    }

    private static int[][] down(int[][] board, int n) {
        int[][] copy = deleteEmpty(board, n, DOWN);
        mergeBoard(copy, n, DOWN);
        copy = deleteEmpty(copy, n, DOWN);

        return copy;
    }

    private static int[][] left(int[][] board, int n) {
        int[][] copy = deleteEmpty(board, n, LEFT);
        mergeBoard(copy, n, LEFT);
        copy = deleteEmpty(copy, n, LEFT);

        return copy;
    }

    private static int[][] right(int[][] board, int n) {
        int[][] copy = deleteEmpty(board, n, RIGHT);
        mergeBoard(copy, n, RIGHT);
        copy = deleteEmpty(copy, n, RIGHT);

        return copy;
    }

    private static int[][] deleteEmpty(int[][] original, int n, int direction) {
        int[][] copy = new int[n][n];
        int temp_x, temp_y;

        switch (direction) {
            case UP:
                for (int j = 0; j < n; j++) {
                    temp_x = 0;
                    for (int i = 0; i < n; i++) {
                        if (original[i][j] != EMPTY) {
                            copy[temp_x++][j] = original[i][j];
                        }
                    }
                }
                break;
            case DOWN:
                for (int j = 0; j < n; j++) {
                    temp_x = n - 1;
                    for (int i = n - 1; i >= 0; i--) {
                        if (original[i][j] != EMPTY) {
                            copy[temp_x--][j] = original[i][j];
                        }
                    }
                }
                break;
            case LEFT:
                for (int i = 0; i < n; i++) {
                    temp_y = 0;
                    for (int j = 0; j < n; j++) {
                        if (original[i][j] != EMPTY) {
                            copy[i][temp_y++] = original[i][j];
                        }
                    }
                }
                break;
            case RIGHT:
                for (int i = 0; i < n; i++) {
                    temp_y = n - 1;
                    for (int j = n - 1; j >= 0; j--) {
                        if (original[i][j] != EMPTY) {
                            copy[i][temp_y--] = original[i][j];
                        }
                    }
                }
            default:
                break;
        }
        return copy;
    }

    private static void mergeBoard(int[][] board, int n, int direction) {
        switch (direction) {
            case UP:
                for (int j = 0; j < n; j++) {
                    for (int i = 0; i < n - 1; i++) {
                        if (isSame(board[i][j], board[i + 1][j])) {
                            board[i][j] += board[i + 1][j];
                            board[i + 1][j] = EMPTY;
                        }
                    }
                }
                break;
            case DOWN:
                for (int j = 0; j < n; j++) {
                    for (int i = n - 1; i > 0; i--) {
                        if (board[i][j] == EMPTY) continue;
                        if (isSame(board[i][j], board[i - 1][j])) {
                            board[i][j] += board[i - 1][j];
                            board[i - 1][j] = EMPTY;
                        }
                    }
                }
                break;
            case LEFT:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n - 1; j++) {
                        if (board[i][j] == EMPTY) continue;
                        if (isSame(board[i][j], board[i][j + 1])) {
                            board[i][j] += board[i][j + 1];
                            board[i][j + 1] = EMPTY;
                        }
                    }
                }
                break;
            case RIGHT:
                for (int i = 0; i < n; i++) {
                    for (int j = n - 1; j > 0; j--) {
                        if (isSame(board[i][j], board[i][j - 1])) {
                            board[i][j] += board[i][j - 1];
                            board[i][j - 1] = EMPTY;
                        }
                    }
                }
            default:
                break;
        }
    }

    private static boolean isSame(int first, int second) {
        return first == second;
    }
}

//상하좌우 순으로 board를 움직인다. 이때 DFS를 활용한다
//board 를 움직이기 전에 숫자가 없는 부분을 지워준다
//그 다음 병합해준다

/*
4
2 0 2 8
0 0 2 2
0 0 0 0
0 0 0 0

4
2 4 8 16
4 8 16 32
8 16 32 64
16 32 64 128

9
4 2 0 0 0 0 0 0 0
4 8 0 0 0 0 0 0 0
4 0 0 0 0 0 0 0 0
8 0 0 0 0 0 0 0 0
8 0 0 0 0 0 0 0 0
8 0 0 0 0 0 0 0 0
8 0 0 0 0 0 0 0 0
8 4 0 0 0 0 0 0 0
8 2 0 0 0 0 0 0 0

3
16 2 2
2 2 2
2 2 16

10
8 8 4 16 32 0 0 8 8 8
8 8 4 0 0 8 0 0 0 0
16 0 0 16 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 16
0 0 0 0 0 0 0 0 0 2
*/

