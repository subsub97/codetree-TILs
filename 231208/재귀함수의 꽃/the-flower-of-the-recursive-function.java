import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        printNumber(n);

    }

    static void printNumber(int number) {
        if(number == 0) {
            return;
        }

        System.out.print(number + " ");

        printNumber(number - 1);

        System.out.print(number + " ");
    }
}