package programmers;


import java.util.HashMap;

public class pg_42577_Hash {
    public static void main(String[] args){
        String p[] = {"123","456","789"};
        boolean ans = solution(p);
        System.out.println(ans);
    }

    private static boolean solution(String[] phone_book) {
        boolean answer = true;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(int inx = 0; inx < phone_book.length; inx++) {
            for(int jnx = 1; jnx < phone_book[inx].length(); jnx++) {
                map.put(phone_book[inx].substring(0, jnx), 1);
            }
        }
        for(int inx = 0; inx < phone_book.length; inx++) {
            if(map.containsKey(phone_book[inx])) {
                answer = false;
                break;
            } else {
                map.put(phone_book[inx], 1);
            }
        }
        return answer;
    }
}
