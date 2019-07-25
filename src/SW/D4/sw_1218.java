package SW.D4;

import java.util.Scanner;

class sw_1218 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int test_case = 1; test_case <= 10; test_case++) {
            int len = sc.nextInt();
            String text = sc.next();
            int ans = result(text, len);
            System.out.println("#" + test_case + " " + ans);
        }
    }

    public static int result(String text, int len) {
        int ans = 0;
        int idx = -1;
        int[] arr = new int[4];
        while (len > 0) {
            len--;
            idx++;
            division((text.substring(idx, idx + 1)), arr);
            if (arr[0] < 0 || arr[1] < 0 || arr[2] < 0 || arr[3] < 0) {
                return ans;
            }
        }
        if (arr[0] != 0 || arr[1] != 0 || arr[2] != 0 || arr[3] != 0) {
            return ans;
        } else {
            ans = 1;
            return ans;
        }
    }

    public static void division(String text, int arr[]) {
        switch (text) {
            case "(":
                arr[0]++;
                break;
            case ")":
                arr[0]--;
                break;
            case "[":
                arr[1]++;
                break;
            case "]":
                arr[1]--;
                break;
            case "{":
                arr[2]++;
                break;
            case "}":
                arr[2]--;
                break;
            case "<":
                arr[3]++;
                break;
            case ">":
                arr[3]--;
                break;
            default:
                break;
        }
    }
}
