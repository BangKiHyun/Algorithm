package programmers.impl;

import java.util.LinkedList;
import java.util.Queue;

public class pg_방문_길이 {
    public static void main(String[] args) {
        String dirs = "ULURRDLLU";
        System.out.println(solution(dirs));
    }

    public static int solution(String dirs) {
        int[][] board = new int[11][11];
        Queue<Node> q = new LinkedList<>();
        return findPath(q, board, dirs);
    }

    private static int findPath(Queue<Node> q, int[][] board, String dirs) {
        boolean[][][][] visit = new boolean[11][11][11][11];
        q.offer(new Node(5, 5));

        int ans = 0;
        for (char dir : dirs.toCharArray()) {
            final Node curNode = q.poll();
            int[] nextPos = changePos(curNode.x, curNode.y, dir);

            if (isRange(nextPos[0], nextPos[1])) {
                // 현 위치에서 다음 위치로 가는 길을 아직 방문한 적 없다면
                if (!visit[curNode.x][curNode.y][nextPos[0]][nextPos[1]]) {
                    //현 위치에서 다음 위치, 다음 위치에서 현 위치로 오는 길 모두 visit check
                    visit[curNode.x][curNode.y][nextPos[0]][nextPos[1]] = true;
                    visit[nextPos[0]][nextPos[1]][curNode.x][curNode.y] = true;
                    ans++;
                }
                q.offer(new Node(nextPos[0], nextPos[1]));
            } else {
                q.offer(curNode);
            }
        }

        return ans;
    }

    private static int[] changePos(int x, int y, char dir) {
        switch (dir) {
            case 'U':
                x--;
                break;
            case 'D':
                x++;
                break;
            case 'L':
                y--;
                break;
            case 'R':
                y++;
                break;
            default:
                break;
        }
        return new int[]{x, y};
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < 11 && y < 11;
    }

    private static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
