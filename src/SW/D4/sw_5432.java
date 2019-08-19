package SW.D4;

import java.util.Scanner;

public class sw_5432 {
    static String s;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            s = sc.next();
            int ans = solution();
            System.out.println("#" + test_case + " " + ans);
        }
        sc.close();
    }

    static int solution() {
        int ans = 0;
        int stick = 0;
        int check = 0;

        for (int i = 0; i < s.length(); i++) {
            String c = s.substring(i, i + 1);
            if(c.equals("(")){
                stick++;
                check = 1;
            }else if(c.equals(")") && check == 1){
                stick--;
                ans += stick;
                check=0;
            }else{
                stick--;
                ans += 1;
                check = 0;
            }
        }
        return ans;
    }
}

