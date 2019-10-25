package SW.D4;

import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class sw_7701 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        Set<String> set = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int len = o1.length() - o2.length();
                if(len == 0){
                    return o1.compareTo(o2);
                }else
                    return len;
            }
        });

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                set.add(sc.next());
            }
            System.out.println("#" + test_case);
            for (String i : set) {
                System.out.println(i);
            }
            set.clear();
        }
    }
}
