import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new  Scanner(System.in);
        int N = sc.nextInt();
        int total = total(N);
        System.out.print(total);

    }

    public static int total (int N){
        if (N<10) return N*N;

        return total(N/10) + (N%10)*(N%10);
    }
}