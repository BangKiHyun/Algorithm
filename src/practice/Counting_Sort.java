package practice;

public class Counting_Sort {
    public static void main(String[] args) {
        int temp;
        int count[] = new int[5];
        int array[] = {1, 3, 2, 4, 5, 1, 3, 3, 2, 1,
                2, 3, 1, 5, 2, 3, 3, 1, 2, 4,
                4, 2, 1, 4, 5, 2, 1, 3, 5, 4};

        for (int i = 0; i < array.length; i++) {
            count[array[i] - 1]++;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                for (int j = 0; j < count[i]; j++) {
                    System.out.print(i + 1 + " ");
                }
            }
        }
    }
}
