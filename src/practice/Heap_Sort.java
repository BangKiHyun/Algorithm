package practice;

public class Heap_Sort {

    static int number = 9;
    static int heep[] = {7, 6, 5, 8, 3, 5, 9, 1, 6};

    public static void main(String[] args) {
        for (int i = 1; i < number; i++) {
            int c = i;
            do {
                int root = (c - 1) / 2;
                if (heep[root] < heep[c]) {
                    int temp = heep[root];
                    heep[root] = heep[c];
                    heep[c] = temp;
                }
                c = root;
            } while (c != 0);
        }

        for (int i = number - 1; i >= 0; i--) {
            int temp = heep[0];
            heep[0] = heep[i];
            heep[i] = temp;
            int root = 0;
            int c = 1;
            do {
                c = 2 * root + 1;
                if (c < i - 1 && heep[c] < heep[c + 1]) {
                    c++;
                }
                if (c < i && heep[root] < heep[c]) {
                    temp = heep[root];
                    heep[root] = heep[c];
                    heep[c] = temp;
                }
                root = c;
            } while (c < i);
        }

        for (int i = 0; i < number; i++) {
            System.out.print(heep[i] + " ");
        }
    }
}
