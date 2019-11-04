package programmers;

import java.util.Arrays;

public class pg_42577_Re {
    static public boolean solution(String[] phone_book) {
        /*boolean answer = true;
        int[] post = new int[phone_book.length];
        for (int i = 0;i<phone_book.length;i++){
            post[i] = Integer.parseInt(phone_book[i]);
        }
        Arrays.sort(post);
        for (int i = 0;i<phone_book.length;i++){
            phone_book[i] = Integer.toString(post[i]);
        }

        for(int i=0;i<phone_book.length-1;i++){
            for (int j=i+1;j<phone_book.length;j++){
                if(phone_book[i].equals(phone_book[j].substring(0,phone_book[i].length()))){
                    return false;
                }
            }
        }*/

        boolean flag = true;
        for(int i=0; i<phone_book.length;i++) {
            for(int j=0; j<phone_book.length; j++) {
                if(!phone_book[i].equals(phone_book[j])&&phone_book[i].startsWith(phone_book[j])) {
                    return false;
                }
            }
        }
        return flag;
    }

    public static void main(String[] args){
        String p[] = {"123","456","789"};
        boolean ans = solution(p);
        System.out.println(ans);
    }
}
