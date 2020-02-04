package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Day Of Mourning의 기타리스트 강토가 사용하는 기타에서 N개의 줄이 끊어졌다. 따라서 새로운 줄을 사거나 교체해야 한다. 강토는 되도록이면 돈을 적게 쓰려고 한다. 6줄 패키지를 살 수도 있고, 1개 또는 그 이상의 줄을 낱개로 살 수도 있다.
//
//끊어진 기타줄의 개수 N과 기타줄 브랜드 M개가 주어지고, 각각의 브랜드에서 파는 기타줄 6개가 들어있는 패키지의 가격, 낱개로 살 때의 가격이 주어질 때, 적어도 N개를 사기 위해 필요한 돈의 수를 최소로 하는 프로그램을 작성하시오.
public class bj_1049_그리디 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫째 줄에 N과 M이 주어진다. N은 100보다 작거나 같은 자연수이고, M은 50보다 작거나 같은 자연수이다.
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        int min_bundle = 1001;
        int min_piece = 1001;
        //둘째 줄부터 M개의 줄에는 각 브랜드의 패키지 가격과 낱개의 가격이 공백으로 구분하여 주어진다. 가격은 0보다 크거나 같고, 1,000보다 작거나 같은 정수이다.
        for (int i = 0; i < m; i++) {
            line = br.readLine().split(" ");
            min_bundle = Math.min(min_bundle, Integer.parseInt(line[0]));
            min_piece = Math.min(min_piece, Integer.parseInt(line[1]));
        }

        int sum = 0;
        if (min_piece * 6 < min_bundle) {
            sum = min_piece * n;
        } else {
            sum += (n / 6) * min_bundle;
            int measure = n % 6;

            if (measure * min_piece < min_bundle) sum += measure * min_piece;
            else sum += min_bundle;
        }

        System.out.println(sum);
    }
}

//Test Case
//8 2 12 1 15 27
//7 2 10 100 100 5
//7 2 12 3 15 4
//36 1 2 1
//9 1 1 1