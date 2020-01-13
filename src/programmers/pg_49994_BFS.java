package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class pg_49994_BFS {
    private static final int MAX = 11;
    private static boolean[][][][] visit = new boolean[MAX][MAX][MAX][MAX];
    private static Queue<Character> q = new LinkedList<>();

    public static void main(String[] args) {
        String dirs = "LR";
        System.out.println(solution(dirs));
    }

    private static int solution(String dirs) {
        //캐릭터는 좌표평면의 (0, 0) 위치에서 시작합니다. 좌표평면의 경계는 왼쪽 위(-5, 5), 왼쪽 아래(-5, -5), 오른쪽 위(5, 5), 오른쪽 아래(5, -5)로 이루어져 있습니다.
        int[][] board = new int[MAX][MAX];
        q.add(new Character(5, 5));
        int ans = findPath(board, dirs);
        return ans;
    }

    private static int findPath(int[][] board, String dirs) {
        int length = 0; //return 값
        int idx = 0; //dirs 에서 가져올 방향에 대한 idx 위치

        while (idx < dirs.length()) {
            Character now = q.poll();
            char dir = dirs.charAt(idx);
            int[] next_pos = changePos(now.x, now.y, dir);

            if (isRange(next_pos[0], next_pos[1])) {
                if (!visit[now.x][now.y][next_pos[0]][next_pos[1]]) {
                    visit[now.x][now.y][next_pos[0]][next_pos[1]] = true;
                    visit[next_pos[0]][next_pos[1]][now.x][now.y] = true;
                    length++;
                }
                q.add(new Character(next_pos[0], next_pos[1]));
            } else q.add(now);

            idx++;
        }
        return length;
    }

    //게임 캐릭터를 4가지 명령어를 통해 움직이려 합니다. 명령어는 다음과 같습니다.
    //U: 위쪽으로 한 칸 가기
    //D: 아래쪽으로 한 칸 가기
    //R: 오른쪽으로 한 칸 가기
    //L: 왼쪽으로 한 칸 가기
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
        return x >= 0 && y >= 0 && x < MAX && y < MAX;
    }

    private static class Character {
        private int x;
        private int y;

        public Character(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

//LULLLLLLU
//ULURRDLLU