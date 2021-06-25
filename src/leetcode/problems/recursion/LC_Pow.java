package leetcode.problems.recursion;

public class LC_Pow {

    public static void main(String[] args) {
        double x = 2.00000;
        int n = Integer.MIN_VALUE;
        final LC_Pow task = new LC_Pow();
        System.out.println(task.myPow(x, n));
    }

    public double myPow(double x, int n) {
        if (n == 0) return 1;
        else if (n > 0) return findPositivePow(x, n);
        else return findNegativePow(x, n);
    }

    private double findPositivePow(double x, int n) {
        if (n == 0) return 1;
        if (n % 2 == 0) {
            return findPositivePow(x * x, n / 2);
        }
        return x * findPositivePow(x, n - 1);
    }

    private double findNegativePow(double x, int n) {
        if (n == 0) return 1;
        if (n % 2 == 0) {
            return findNegativePow(x * x, n / 2);
        }
        return (1 / (x)) * findNegativePow(x, n + 1);
    }
}
