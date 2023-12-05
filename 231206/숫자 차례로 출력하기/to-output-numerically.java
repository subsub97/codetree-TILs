import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        pirntOrderedNumbers(n);
        System.out.println();
        pirntDescendingNumbers(n);
    }

    private static void pirntOrderedNumbers(int n) {
        if(n ==0) return;

        pirntOrderedNumbers(n-1);
        System.out.print(n + " ");
    }

    private static void pirntDescendingNumbers(int n) {
        if(n == 0) return;
        System.out.print(n + " ");
        pirntDescendingNumbers(n-1);
    }

}