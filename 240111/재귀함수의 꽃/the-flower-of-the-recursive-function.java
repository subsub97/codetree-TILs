import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        number(T);
    }

    public static void number(int n){
    if(n==0){
        return;
    }
    System.out.printf(n + " ");
    number(n-1);
    System.out.printf(n + " ");

    return;
    }
}