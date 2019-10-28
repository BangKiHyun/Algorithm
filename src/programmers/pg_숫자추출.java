package programmers;

public class pg_숫자추출 {
    public static void main(String[] args) {
        String s = "a234";
        int len = s.length();
        String reStr = s.replaceAll("[^0-9]", "");
        int reLen = reStr.length();

        if ((len == 4 || len == 6) && len == reLen) {
            System.out.println(true);
        }else{
            System.out.println(false);
        }
    }
}
