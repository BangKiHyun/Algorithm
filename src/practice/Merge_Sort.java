package practice;


public class Merge_Sort {
    static int number = 8;
    static int sorted[] = new int[8];

    public static void merge(int a[], int m, int middle, int n) {
        int i = m;
        int j = middle + 1;
        int k = m;

        while (i <= middle && j <= n) {
            if (a[i] <= a[j]) {
                sorted[k] = a[i];
                i++;
            } else {
                sorted[k] = a[j];
                j++;
            }
            k++;
        }

        if (i > middle) {
            for (int t = j; t <= n; t++) {
                sorted[k] = a[t];
                k++;
            }
        } else {
            for (int t = i; t <= middle; t++) {
                sorted[k] = a[t];
                k++;
            }
        }

        for (int t = m; t <= n; t++) {
            a[t] = sorted[t];
        }
    }

    public static void mergeSort(int a[], int m, int n) {
        if (m < n) {
            int middle = (m + n) / 2;
            mergeSort(a, m, middle);
            mergeSort(a, middle + 1, n);
            merge(a, m, middle, n);
        }
    }

    public static void main(String[] args) {
        int array[] = {7, 6, 5, 8, 3, 5, 9, 1};
        mergeSort(array, 0, number - 1);
        for (int i = 0; i < number; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
