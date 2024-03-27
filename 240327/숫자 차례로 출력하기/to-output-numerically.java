import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        printUp(N);
        System.out.println();
        pritntDown(N);
    }

    public static void printUp(int N){
        if(N<1) return;
        printUp(N-1);
        System.out.print(N + " ");
    }

    public static void pritntDown(int N){
        if(N<1) return;
        System.out.print(N + " ");
        pritntDown(N-1);
    }
}