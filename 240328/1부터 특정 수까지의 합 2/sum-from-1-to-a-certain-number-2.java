import java.util.*;
public class Main {

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int N = sc.nextInt();
       int total = plus(N);
       System.out.print(total);

    }

    public static int plus(int N){
        if (N<1) return 0 ;
        return plus(N-1)+N;
    }
}