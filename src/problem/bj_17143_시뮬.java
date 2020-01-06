package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_17143_시뮬 {
    private static int r, c, m;
    private static int[][] visit;
    private static int[][] check;
    private static boolean overlap = false;
    private static Map<Integer, Shark> map = new HashMap<>();
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //첫째 줄에 격자판의 크기 R, C와 상어의 수 M이 주어진다. (2 ≤ R, C ≤ 100, 0 ≤ M ≤ R×C)
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new int[r + 1][c + 1];

        //둘째 줄부터 M개의 줄에 상어의 정보가 주어진다. 상어의 정보는 다섯 정수 r, c, s, d, z (1 ≤ r ≤ R, 1 ≤ c ≤ C, 0 ≤ s ≤ 1000, 1 ≤ d ≤ 4, 1 ≤ z ≤ 10000) 로 이루어져 있다.
        //(r, c)는 상어의 위치, s는 속력, d는 이동 방향, z는 크기이다. d가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽을 의미한다.
        //두 상어가 같은 크기를 갖는 경우는 없고, 하나의 칸에 둘 이상의 상어가 있는 경우는 없다.
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            visit[r][c] = i;
            Shark shark = new Shark(i, r, c, speed, dir, size);
            map.put(i, shark);
        }

        int kingPosition = 0;
        while (kingPosition != c) {
            kingPosition++;
            findShark(kingPosition);
            overlap = false;
            check = new int[r + 1][c + 1];
            moveShark();
            if (overlap) {
                for (int i = 1; i <= r; i++) {
                    for (int j = 1; j <= c; j++) {
                        if (check[i][j] >= 2) {
                            Shark origin = null;
                            //int size = 0;
                            ArrayList<Integer> remove_list = new ArrayList<>();
                            for (int idx : map.keySet()) {
                                Shark now = map.get(idx);
                                if (now.position_r == i && now.position_c == j) {
                                    if (origin == null) origin = now;
                                    else {
                                        if (origin.size > now.size) {
                                            //size += now.size;
                                            remove_list.add(now.idx);
                                        } else {
                                            //size += origin.size;
                                            remove_list.add(origin.idx);
                                            origin = now;
                                        }
                                    }
                                }
                            }
                            //origin.size += size;
                            visit[origin.position_r][origin.position_c] = origin.idx;
                            map.put(origin.idx, origin);
                            for (int idx : remove_list) map.remove(idx);

                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    private static void findShark(int col) {
        for (int i = 1; i <= r; i++) {
            if (existShark(i, col)) {
                catchShark(i, col);
                removeShark(i, col);
                return;
            }
        }
    }

    private static boolean existShark(int r, int c) {
        return visit[r][c] != 0;
    }

    private static void catchShark(int r, int c) {
        Shark shark = map.get(visit[r][c]);
        ans += shark.size;
    }

    private static void removeShark(int r, int c) {
        map.remove(visit[r][c]);
        visit[r][c] = 0;
    }

    private static void moveShark() {
        for (int idx : map.keySet()) {
            Shark shark = map.get(idx);
            visit[shark.position_r][shark.position_c] = 0;
            Shark temp = shark;
            for (int i = 0; i < shark.speed; i++) {
                int[] nextPos = getPosition(shark);
                if (!isRange(nextPos[0], nextPos[1])) {
                    shark.direction = changeDirection(shark.direction);
                    nextPos = getPosition(shark);
                    if (!isRange(nextPos[0], nextPos[1])) {
                        shark = temp;
                        break;
                    }
                }
                shark.position_r = nextPos[0];
                shark.position_c = nextPos[1];
            }
            if (check[shark.position_r][shark.position_c] != 0) overlap = true;

            check[shark.position_r][shark.position_c]++;
            visit[shark.position_r][shark.position_c] = shark.idx;
            map.put(shark.idx, shark);
        }
    }

    private static int[] getPosition(Shark shark) {
        int[] pos = new int[2];
        pos[0] = shark.position_r;
        pos[1] = shark.position_c;
        switch (shark.direction) {
            case 1:
                pos[0]--;
                break;
            case 2:
                pos[0]++;
                break;
            case 3:
                pos[1]++;
                break;
            case 4:
                pos[1]--;
                break;
            default:
                break;
        }
        return pos;
    }

    private static boolean isRange(int next_r, int next_c) {
        return next_r > 0 && next_c > 0 && next_r <= r && next_c <= c;
    }

    private static int changeDirection(int dir) {
        switch (dir) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 4;
            case 4:
                return 3;
        }
        return -1;
    }

    private static class Shark {
        private int idx;
        private int position_r;
        private int position_c;
        private int speed;
        private int direction;
        private int size;

        public Shark(int idx, int position_r, int position_c, int speed, int direction, int size) {
            this.idx = idx;
            this.position_r = position_r;
            this.position_c = position_c;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }
}

//열의 수만큼 while문을 돌린다
//첫번째 열에서 (row,col)에서 col값은 고정 row값은 plus해주면서 상어있으면 크기만큼 값 plus 하고 삭제
//각각 상어마다 고유한 이동방향과 속력에 따라 움직여줌
//이동할 칸이 벽이면 이동방향을 반대로해서 움직여줌
//이때 이동방향이 벽인것을 먼저 인지해야 함으로 if문을 통해 걸러줌
//이동한 후 속력 count를 minus해줘야 함
//모두 이동한 후 겹치는 상어가 있다면 누가더 큰 상어인지 비교한 후 큰 상어에게 잡아먹은 상어의 크기를 plus해줌
//이때 ArrayList나 boolean을 통해 겹친 부분이 있는지 미리 정해줌
//위에내용 반복
