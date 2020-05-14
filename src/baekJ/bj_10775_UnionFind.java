package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//오늘은 신승원의 생일이다.
//박승원은 생일을 맞아 신승원에게 인천국제공항을 선물로 줬다.
//공항에는 G개의 게이트가 있으며 각각은 1에서 G까지의 번호를 가지고 있다.
//공항에는 P개의 비행기가 순서대로 도착할 예정이며, 당신은 i번째 비행기를 1번부터 gi (1 ≤ gi ≤ G) 번째 게이트중 하나에 영구적으로 도킹하려 한다.
//비행기가 도킹된 게이트에는 다른 비행기가 도착할 수 없다.
//이러한 사고가 일어나면 공항이 폐쇄되고, 이후 어떤 비행기도 도착할 수 없다.
//신승원은 가장 많은 비행기를 공항에 도킹시켜서 박승원을 행복하게 하고 싶어한다. 승원이는 비행기를 최대 몇 대 도킹시킬 수 있는가?
public class bj_10775_UnionFind {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        parent = new int[g + 1];
        for (int i = 1; i <= g; i++) {
            parent[i] = i;
        }

        int ans = 0;
        for (int pIdx = 1; pIdx <= p; pIdx++) {
            int gateNum = Integer.parseInt(br.readLine());
            int docking = findParent(gateNum);
            if (docking != 0) {
                unionParent(docking, docking - 1);
                ans++;
            } else {
                break;
            }
        }

        System.out.println(ans);
    }

    private static int findParent(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }

    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
}
