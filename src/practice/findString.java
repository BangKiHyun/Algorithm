package practice;

import java.util.Scanner;

public class findString {

    static int find(String parent, String pattern){
        int parenSize = parent.length();
        int patternSize = pattern.length();
        for(int i=0;i<=parenSize - parenSize; i++){
            boolean finded = true;
            for(int j=0; j<patternSize; j++){
                if(parent.substring() != pattern[j]){
                    finded = false;
                    break;
                }
            }
            if(finded){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String pattern = sc.next();

        int result = find(input, pattern);

        System.out.print(result + "번째에서 일치");
    }
}
