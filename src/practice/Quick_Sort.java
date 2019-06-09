package practice;

import java.util.Scanner;

public class Quick_Sort {

    static int data[] = new int[1000000];
    static int number;

    public static void quickSort(int data[], int start, int end) {
        if (start >= end)
            return;

        int key = start;
        int i = start + 1, j = end, temp;

        while (i <= j) {
            while (data[i] <= data[key]) {
                i++;
            }
            while (data[j] >= data[key]) {
                j--;
            }
            if (i > j) {
                temp = data[j];
                data[j] = data[key];
                data[key] = temp;
            } else {
                temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }

            quickSort(data, start, j - 1);
            quickSort(data, j + 1, end);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        number = sc.nextInt();
        for (int i = 0; i < number; i++) {
            data[i] = sc.nextInt();
        }
        quickSort(data, 0, number - 1);
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }
}
