package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class bj_1076_수학 {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        map.put("black", 0);
        map.put("brown", 1);
        map.put("red", 2);
        map.put("orange", 3);
        map.put("yellow", 4);
        map.put("green", 5);
        map.put("blue", 6);
        map.put("violet", 7);
        map.put("grey", 8);
        map.put("white", 9);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str_first = br.readLine();
        String str_second = br.readLine();
        String str_mul = br.readLine();
        int num_first = map.get(str_first) * 10;
        int num_second = map.get(str_second);
        int num = num_first + num_second;
        int zero_cnt = map.get(str_mul);
        long mul = 1;
        while (zero_cnt != 0) {
            mul *= 10;
            zero_cnt--;
        }
        System.out.println(num * mul);
    }
}
