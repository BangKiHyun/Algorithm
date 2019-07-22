package SW.D4;

import java.util.Scanner;

public class sw_3143 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            String A = sc.next();
            String B = sc.next();
            int cnt = 0;
            int idx = 0;
            for (int i = 0; i < A.length(); i++) {
                idx = i;
                for (int j = 0; j < B.length(); j++) {
                    if (A.charAt(idx) == B.charAt(j)) {
                        if (j == B.length() - 1) {
                            cnt++;
                            i = idx;
                        } else {
                            if (idx < A.length()-2) {
                                idx++;
                                continue;
                            }else {
                                cnt++;
                                break;
                            }
                        }
                    } else {
                        cnt++;
                        break;
                    }
                }
            }
            System.out.print("#" + test_case + " " + cnt);
        }
    }
}
