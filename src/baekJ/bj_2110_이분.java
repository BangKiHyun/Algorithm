package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//도현이의 집 N개가 수직선 위에 있다.
//각각의 집의 좌표는 x1, ..., xN이고, 집 여러개가 같은 좌표를 가지는 일은 없다.
//
//도현이는 언제 어디서나 와이파이를 즐기기 위해서 집에 공유기 C개를 설치하려고 한다.
//최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에, 한 집에는 공유기를 하나만 설치할 수 있고,
//가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.
//
//C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.
public class bj_2110_이분 {
    private static int n, c;
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);

        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        int maxDistance = getMaxDistance();
        System.out.println(maxDistance);
    }

    private static int getMaxDistance() {
        Collections.sort(list);

        int min = 1;
        int max = list.get(list.size() - 1);
        int distance = 0;

        while (min <= max) {
            int mid = (min + max) / 2;
            int cnt = getCount(mid);

            if (cnt < c) {
                max = mid - 1;
            } else {
                distance = mid;
                min = mid + 1;
            }
        }

        return distance;
    }

    private static int getCount(int mid) {
        int start = list.get(0);
        int cnt = 1;

        for (int i = 1; i < list.size(); i++) {
            int distance = list.get(i) - start;
            if (mid <= distance) {
                cnt++;
                start = list.get(i);
            }
        }

        return cnt;
    }
}
