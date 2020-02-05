package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class bj_10816_HashMap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());

        Map<String, Integer> map = new HashMap<>();
        while (st.hasMoreElements()) {
            String num = st.nextToken();
            if (map.containsKey(num)) map.put(num, map.get(num) + 1);
            else map.put(num, 1);
        }

        br.readLine();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreElements()) {
            String num = st.nextToken();
            System.out.print(map.get(num) == null ? 0 : map.get(num));
            System.out.print(" ");
        }
    }
}
