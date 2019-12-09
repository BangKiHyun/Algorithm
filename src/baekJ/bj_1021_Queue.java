package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class bj_1021_Queue {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 큐의 크기 N과 뽑아내려고 하는 수의 개수 M이 주어진다. N은 50보다 작거나 같은 자연수이고, M은 N보다 작거나 같은 자연수이다.
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Box box = new Box(N);

        //둘째 줄에는 지민이가 뽑아내려고 하는 수의 위치가 순서대로 주어진다. 위치는 1보다 크거나 같고, N보다 작거나 같은 자연수이다.
        st = new StringTokenizer(br.readLine());
        LinkedList<Integer> findList = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            findList.add(Integer.parseInt(st.nextToken()));
        }

        while (!findList.isEmpty()) {
            int idx = box.getIdx(findList.poll());

            if (box.closerToLeft(idx)) {
                box.leftCycle(idx);
            } else {
                box.rightCycle(idx);
            }

            box.pick();
        }

        System.out.println(box.getCount());
    }

    private static class Box {
        private int n;
        private int cnt;
        private LinkedList<Integer> list = new LinkedList<>();

        public Box(int n) {
            this.n = n;

            init();
        }

        private void init() {
            for (int i = 1; i <= n; i++) {
                list.add(i);
            }
        }

        public boolean closerToLeft(int idx) {
            int left = idx;
            int right = getRight(idx);

            return left < right ? true : false;
        }

        public int getIdx(int num) {
            return list.indexOf(num);
        }

        private int getRight(int idx) {
            return list.size() - idx;
        }

        public void leftCycle(int num) {
            while (num != 0) {
                int temp = this.list.poll();
                this.list.addLast(temp);
                this.cnt++;
                num--;
            }
        }

        public void rightCycle(int idx) {
            int num = getRight(idx);

            while (num != 0) {
                int temp = this.list.pollLast();
                this.list.addFirst(temp);
                this.cnt++;
                num--;
            }
        }

        public void pick() {
            list.poll();
        }

        public int getCount() {
            return this.cnt;
        }
    }
}
