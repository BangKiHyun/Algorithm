package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//나무 재테크란 작은 묘목을 구매해 어느정도 키운 후 팔아서 수익을 얻는 재테크이다.
//상도는 나무 재테크로 더 큰 돈을 벌기 위해 M개의 나무를 구매해 땅에 심었다. 같은 1×1 크기의 칸에 여러 개의 나무가 심어져 있을 수도 있다.
//
//이 나무는 사계절을 보내며, 아래와 같은 과정을 반복한다.
//
//봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
//각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
//하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
//만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
//
//여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
//각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.
//
//가을에는 나무가 번식한다.
//번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
//어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다.
//상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
//
//겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다.
//각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
//
//K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구하는 프로그램을 작성하시오.
public class bj_16235_구현 {

    private static int n, k;
    private static int[][] map;

    private static List<Nourishment> nourishments = new ArrayList<>();
    private static Queue<Tree> alive = new PriorityQueue<>();
    private static Queue<Tree> dead = new LinkedList<>();
    private static Queue<Tree> breeding = new LinkedList<>();

    private static int[] dx = {1, 1, 1, -1, -1, -1, 0, 0};
    private static int[] dy = {1, -1, 0, 1, -1, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 N, M, K가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];
        //둘째 줄부터 N개의 줄에 A배열의 값이 주어진다. r번째 줄의 c번째 값은 A[r][c]이다.
        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], 5);
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int amount = Integer.parseInt(st.nextToken());
                nourishments.add(new Nourishment(i, j, amount));
            }
        }

        //다음 M개의 줄에는 상도가 심은 나무의 정보를 나타내는 세 정수 x, y, z가 주어진다.
        //처음 두 개의 정수는 나무의 위치 (x, y)를 의미하고, 마지막 정수는 그 나무의 나이를 의미한다.
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            alive.add(new Tree(x, y, z));
        }

        getAnswer();
        System.out.println(alive.size());
    }

    private static void getAnswer() {
        for (int i = 0; i < k; i++) {
            goSpring();
            goSummer();
            goFall();
            goWinter();
        }
    }

    private static void goSpring() {
        List<Tree> temp = new ArrayList<>();
        int size = alive.size();
        for (int i = 0; i < size; i++) {
            Tree cur = alive.poll();
            int x = cur.x;
            int y = cur.y;
            if (map[x][y] >= cur.age) {
                map[x][y] -= cur.age;
                temp.add(new Tree(x, y, ++cur.age));
                if (canBreeding(cur.age)) {
                    breeding.add(new Tree(x, y, 1));
                }
            } else {
                dead.add(new Tree(x, y, cur.age / 2));
            }
        }
        alive.addAll(temp);
    }

    private static boolean canBreeding(final int age) {
        return age >= 5 && (age % 5 == 0);
    }

    private static void goSummer() {
        while (!dead.isEmpty()) {
            Tree cur = dead.poll();
            map[cur.x][cur.y] += cur.age;
        }
    }

    private static void goFall() {
        while (!breeding.isEmpty()) {
            Tree cur = breeding.poll();
            int x = cur.x;
            int y = cur.y;

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isRange(nx, ny)) {
                    alive.add(new Tree(nx, ny, cur.age));
                }
            }
        }
    }

    private static void goWinter() {
        for (Nourishment nourishment : nourishments) {
            int x = nourishment.x;
            int y = nourishment.y;

            map[x][y] += nourishment.amount;
        }
    }

    private static boolean isRange(final int x, final int y) {
        return x > 0 && y > 0 && x <= n && y <= n;
    }

    private static class Nourishment {
        private int x;
        private int y;
        private int amount;

        public Nourishment(final int x, final int y, final int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }

    private static class Tree implements Comparable<Tree> {
        private int x;
        private int y;
        private int age;

        public Tree(final int x, final int y, final int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(final Tree o) {
            return this.age - o.age;
        }
    }
}
