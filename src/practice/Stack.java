package practice;

import java.util.*;

public class Stack {

    static final int MAX_N = 100;
    static int stack[] = new int[MAX_N];
    static int top;

    static void stackInit() {
        top = 0;
    }

    static boolean stackEmpty() {
        return (top == 0);
    }

    static boolean stackFull() {
        return (top == MAX_N);
    }

    static boolean stackPush(int value) {
        if (stackFull()) {
            System.out.println("stack overflow!");
            return false;
        }
        stack[top] = value;
        top++;
        return true;
    }

    static boolean stackPop() {
        if (stackEmpty()) {
            System.out.println("stack underflow!");
            return false;
        }
        top--;
        System.out.println(stack[top]);
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        stackInit();
        for (int i = 0; i < 10; i++) {
            int value = sc.nextInt();
            stackPush(value);
        }
        while (top != 0) {
            stackPop();
        }
    }
}
