package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_8979_구현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        ArrayList<Country> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            list.add(new Country(idx, gold, silver, bronze));
        }

        Collections.sort(list, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                if (o1.gold > o2.gold) {
                    return -1;
                } else if (o1.gold < o2.gold) {
                    return 1;
                } else {
                    if (o1.silver > o2.silver) {
                        return -1;
                    } else if (o1.silver < o2.silver) {
                        return 1;
                    } else {
                        if (o1.bronze > o2.bronze) {
                            return -1;
                        } else if (o1.bronze < o2.bronze) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        });

        int rank = 1;
        int sameCnt = 0;
        Country temp = list.get(0);
        if (k == temp.idx) {
            System.out.println(rank);
        } else {
            for (int i = 1; i < list.size(); i++) {
                Country country = list.get(i);
                if (temp.gold == country.gold && temp.silver == country.silver && temp.bronze == country.bronze) {
                    sameCnt++;
                } else {
                    if (sameCnt != 0) rank += sameCnt + 1;
                    else rank++;

                    temp = country;
                    sameCnt = 0;
                }
                if (country.idx == k) {
                    System.out.println(rank);
                    break;
                }
            }
        }
    }

    private static class Country {
        private int idx;
        private int gold;
        private int silver;
        private int bronze;

        public Country(int idx, int gold, int silver, int bronze) {
            this.idx = idx;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }
    }
}
