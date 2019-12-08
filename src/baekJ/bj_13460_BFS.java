package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_13460_BFS {
    private static int r, c;
    private static int[] dx = {0, 0, 1, -1}; //동 서 남 북
    private static int[] dy = {1, -1, 0, 0};
    private static String[][] board;
    private static Queue<Node> q = new LinkedList<>();
    private static boolean[][][][] visit = new boolean[10][10][10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        //첫 번째 줄에는 보드의 세로, 가로 크기를 의미하는 두 정수 N, M (3 ≤ N, M ≤ 10)이 주어진다. 다음 N개의 줄에 보드의 모양을 나타내는 길이 M의 문자열이 주어진다.
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new String[r][c];

        Node node = new Node();
        //이 문자열은 '.', '#', 'O', 'R', 'B' 로 이루어져 있다. '.'은 빈 칸을 의미하고, '#'은 공이 이동할 수 없는 장애물 또는 벽을 의미하며, 'O'는 구멍의 위치를 의미한다. 'R'은 빨간 구슬의 위치, 'B'는 파란 구슬의 위치이다.
        //
        //입력되는 모든 보드의 가장자리에는 모두 '#'이 있다. 구멍의 개수는 한 개 이며, 빨간 구슬과 파란 구슬은 항상 1개가 주어진다.
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            board[i] = line.split("");
            for (int j = 0; j < c; j++) {
                if (board[i][j].equals("R")) {
                    node.rx = i;
                    node.ry = j;
                } else if (board[i][j].equals("B")) {
                    node.bx = i;
                    node.by = j;
                }
            }
        }
        node.cnt = 0;
        q.add(node);
        goBFS();

        br.close();
    }

    private static void goBFS() {
        while (!q.isEmpty()) {
            Node now = q.poll();
            visit[now.rx][now.ry][now.bx][now.by] = true;

            if (now.cnt > 10) break;

            //동 서 남 북 순서로 확인한다
            for (int i = 0; i < 4; i++) {
                int[] blue = move(now.bx, now.by, dx[i], dy[i]);
                int[] red = move(now.rx, now.ry, dx[i], dy[i]);

                //파란공이 구멍에 들어갔으면 넘긴다
                //파란공은 들어가지 않고 빨간공만 들어갔으면 return한다
                if (board[blue[0]][blue[1]].equals("O")) {
                    continue;
                } else if (board[red[0]][red[1]].equals("O")) {
                    System.out.println(now.cnt + 1);
                    return;
                }

                //파란구슬과 빨간구슬이 같은 위치에있으면
                //처음위치를 비교해서 조정해준다
                if (blue[0] == red[0] && blue[1] == red[1]) {
                    switch (i) {
                        case 0:
                            if (now.by > now.ry) {
                                red[1] -= 1;
                            } else {
                                blue[1] -= 1;
                            }
                            break;
                        case 1:
                            if (now.by > now.ry) {
                                blue[1] += 1;
                            } else {
                                red[1] += 1;
                            }
                            break;
                        case 2:
                            if (now.bx > now.rx) {
                                red[0] -= 1;
                            } else {
                                blue[0] -= 1;
                            }
                            break;
                        case 3:
                            if (now.bx > now.rx) {
                                blue[0] += 1;
                            } else {
                                red[0] += 1;
                            }
                            break;
                    }
                }

                //두 구슬을 움직인 후 각각의 위치가 처음 방문한 곳이면 Queue에 넣는다
                if (!visit[red[0]][red[1]][blue[0]][blue[1]]) {
                    q.add(new Node(red[0], red[1], blue[0], blue[1], now.cnt + 1));
                }
            }
        }
        System.out.println(-1);
    }

    private static int[] move(int nx, int ny, int move_x, int move_y) {
        while (!board[nx][ny].equals("#")) {
            nx += move_x;
            ny += move_y;

            if (board[nx][ny].equals("O")) {
                return new int[]{nx, ny};
            }
        }
        return new int[]{nx - move_x, ny - move_y};
    }

    private static class Node {
        int rx, ry, bx, by;
        int cnt;

        public Node() {
        }

        public Node(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }
}
