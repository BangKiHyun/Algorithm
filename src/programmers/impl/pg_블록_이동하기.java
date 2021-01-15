package programmers.impl;

import java.util.*;

public class pg_블록_이동하기 {
    private static int n;
    private static Set<Node> visitSet = new HashSet<>();

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1,},
                {0, 0, 1, 0, 0, 0, 0}};

        System.out.println(solution(board));
    }

    public static int solution(int[][] board) {
        n = board.length;
        Node startNode = new Node(0, 0, 0, 1, 0);

        Queue<Node> q = new LinkedList<>();
        q.offer(startNode);
        visitSet.add(startNode);

        return goBFS(q, board);
    }

    private static int goBFS(Queue<Node> q, int[][] board) {
        int[][] moveDir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[] rotateDir = {-1, 1};

        while (!q.isEmpty()) {
            final Node curNode = q.poll();

            if ((curNode.x1 == n - 1 && curNode.y1 == n - 1) || (curNode.x2 == n - 1 && curNode.y2 == n - 1)) {
                return curNode.cnt;
            }

            // 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nx1 = curNode.x1 + moveDir[i][0];
                int ny1 = curNode.y1 + moveDir[i][1];
                int nx2 = curNode.x2 + moveDir[i][0];
                int ny2 = curNode.y2 + moveDir[i][1];

                Node nextNode = new Node(nx1, ny1, nx2, ny2, curNode.cnt + 1);
                if (!visitSet.contains(nextNode) && isRange(nx1, ny1, nx2, ny2) && isNotBlocking(nx1, ny1, nx2, ny2, board)) {
                    q.offer(nextNode);
                    visitSet.add(nextNode);
                }
            }

            if (curNode.isHorizontal()) {
                // 로봇이 수직일 때 양 옆이 0이면 회전 가능
                for (int rotate : rotateDir) {
                    int nx1 = curNode.x1;
                    int ny1 = curNode.y1 + rotate;
                    int nx2 = curNode.x2;
                    int ny2 = curNode.y2 + rotate;

                    if (isRange(nx1, ny1, nx2, ny2) && isNotBlocking(nx1, ny1, nx2, ny2, board)) {
                        Node nextNode1 = new Node(curNode.x1, curNode.y1, nx1, ny1, curNode.cnt + 1);
                        Node nextNode2 = new Node(curNode.x2, curNode.y2, nx2, ny2, curNode.cnt + 1);

                        if (!visitSet.contains(nextNode1)) {
                            q.offer(nextNode1);
                            visitSet.add(nextNode1);
                        }

                        if (!visitSet.contains(nextNode2)) {
                            q.offer(nextNode2);
                            visitSet.add(nextNode2);
                        }
                    }
                }
            } else {
                // 로봇이 수평일 때 양 옆이 0이면 회전 가능
                for (int rotate : rotateDir) {
                    int nx1 = curNode.x1 + rotate;
                    int ny1 = curNode.y1;
                    int nx2 = curNode.x2 + rotate;
                    int ny2 = curNode.y2;

                    if (isRange(nx1, ny1, nx2, ny2) && isNotBlocking(nx1, ny1, nx2, ny2, board)) {
                        Node nextNode1 = new Node(curNode.x1, curNode.y1, nx1, ny1, curNode.cnt + 1);
                        Node nextNode2 = new Node(curNode.x2, curNode.y2, nx2, ny2, curNode.cnt + 1);

                        if (!visitSet.contains(nextNode1)) {
                            q.offer(nextNode1);
                            visitSet.add(nextNode1);
                        }

                        if (!visitSet.contains(nextNode2)) {
                            q.offer(nextNode2);
                            visitSet.add(nextNode2);
                        }
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isRange(int nx1, int ny1, int nx2, int ny2) {
        return nx1 >= 0 && ny1 >= 0 && nx2 >= 0 && ny2 >= 0 &&
                nx1 < n && ny1 < n && nx2 < n && ny2 < n;

    }

    private static boolean isNotBlocking(int nx1, int ny1, int nx2, int ny2, int[][] board) {
        return board[nx1][ny1] == 0 && board[nx2][ny2] == 0;
    }

    private static class Node {
        private int x1;
        private int y1;
        private int x2;
        private int y2;
        private int cnt;

        public Node(int x1, int y1, int x2, int y2, int cnt) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.cnt = cnt;
        }

        public boolean isHorizontal() {
            return y1 == y2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return x1 == node.x1 &&
                    y1 == node.y1 &&
                    x2 == node.x2 &&
                    y2 == node.y2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x1, y1, x2, y2);
        }
    }
}
