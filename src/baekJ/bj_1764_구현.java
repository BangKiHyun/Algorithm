package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_1764_구현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> unHeard = new HashSet<>();
        for (int i = 0; i < n; i++) {
            unHeard.add(br.readLine());
        }

        ArrayList<String> unHeardAndSeen = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String unSeen = br.readLine();
            if (unHeard.contains(unSeen)) unHeardAndSeen.add(unSeen);
        }

        Collections.sort(unHeardAndSeen);

        StringBuilder ans = new StringBuilder();
        ans.append(unHeardAndSeen.size()).append('\n');
        for (String s : unHeardAndSeen) ans.append(s).append('\n');

        System.out.println(ans);

        br.close();
    }
}
