package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class bj_1477_이분_Re {
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int length = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreElements()) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        list.add(length);

        Collections.sort(list);

        System.out.println(getAnswer(m, length));
    }

    private static int getAnswer(final int m, final int length) {
        int left = 1;
        int rigth = length - 1;

        int mid = 0;

        while (left <= rigth) {
            mid = (left + rigth) / 2;

            int count = getDistance(mid);

            if (count <= m) {
                rigth = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static int getDistance(final int mid) {
        int count = 0;

        count += list.get(0) / mid;
        if (list.get(0) % mid == 0) {
            count--;
        }

        for (int i = 1; i < list.size(); i++) {
            int interval = list.get(i) - list.get(i - 1);
            count += interval / mid;

            if (interval % mid == 0) {
                count--;
            }
        }

        return count;
    }
}
