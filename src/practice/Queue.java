package practice;

import java.util.LinkedList;
import java.util.Scanner;

public class Queue {

    public static void main(String[] args){
        java.util.Queue q = new LinkedList();

        q.offer("1");
        q.offer("2");
        q.offer("3");

        while (!q.isEmpty()){
            System.out.println(q.poll());
        }
    }
/*    static int MAX = 100;
    static int queue[] = new int[MAX];
    static int top;
    static int bottom;

    static void init(){
        top = 0;
        bottom = 0;
    }
    static boolean push(int num){
        if(top == MAX) {
            System.out.println("queue overflow!");
            return false;
        }
        queue[top] = num;
        top++;
        return true;
    }
    static boolean pop(){
        if(top == 0){
            System.out.println("queue underflow!");
            return false;
        }
        System.out.print(queue[bottom]);
        bottom++;
        return true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<5;i++){
            int num = sc.nextInt();
            push(num);
        }
        while (top)
    }*/
}
