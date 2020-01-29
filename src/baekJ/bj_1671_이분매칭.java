package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_1671_이분매칭 {
    //어떤 상어는 저녁식사로 서로를 먹는다. 모든 상어는 자신과 다른 상어의 크기, 속도, 지능을 수치로 나타낸 것을 알고 있다. 만약, 상어 A의 크기, 속도, 지능이 상어 B의 크기, 속도, 지능보다 크거나 같다면 상어 A는 상어 B를 먹을 수 있다. 그러나, 상어들의 왕 김재홍은 상어들이 많이 없어지는 것을 방지하기 위해서 한 상어가 최대 두 개의 상어만 먹을 수 있게 했다. 상어들은 김재홍의 말을 모두 듣는다.
    //
    //N마리 상어의 크기, 속도, 지능이 주어졌을 때, 살아남을 수 있는 상어의 최솟값을 구하시오.
    private static ArrayList<Integer>[] eatable_list;
    private static Shark[] list;
    private static boolean[] catched;
    private static int[] eated;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //첫째 줄에 상어의 마리 수 N이 주어진다. 이 값은 50보다 작거나 같은 자연수이다.
        int n = Integer.parseInt(br.readLine());
        catched = new boolean[n + 1];
        eated = new int[n + 1];
        eatable_list = new ArrayList[n + 1];
        list = new Shark[n + 1];

        for (int i = 1; i <= n; i++) {
            eatable_list[i] = new ArrayList<>();
        }


        //둘째 줄부터 각 상어의 크기, 속도, 지능의 정보가 주어진다. 이 값은 2,000,000,000보다 작거나 같은 자연수이다.
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            int size = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int IQ = Integer.parseInt(st.nextToken());

            list[i] = new Shark(size, speed, IQ);
        }

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (isEatable(list[i], list[j])) {
                    eatable_list[i].add(j);
                    continue;
                }
                if (isEatable(list[j], list[i])) {
                    eatable_list[j].add(i);
                }
            }
        }

        int catched_cnt = 0;
        for (int k = 0; k < 2; k++) {
            for (int i = 1; i <= n; i++) {
                Arrays.fill(catched, false);
                if (eatShark(i)) catched_cnt++;
            }
        }

        System.out.println(n - catched_cnt);
    }

    private static boolean isEatable(Shark shark, Shark target) {
        return shark.size >= target.size && shark.speed >= target.speed && shark.IQ >= target.IQ;
    }

    private static boolean eatShark(int num) {
        for (int i : eatable_list[num]) {
            if (catched[i]) continue;
            catched[i] = true;

            if (eated[i] == 0 || eatShark(eated[i])) {
                eated[i] = num;
                return true;
            }
        }
        return false;
    }


    private static class Shark {
        private int size;
        private int speed;
        private int IQ;

        public Shark(int size, int speed, int IQ) {
            this.size = size;
            this.speed = speed;
            this.IQ = IQ;
        }
    }
}
