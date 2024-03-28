import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        star(N);
    }

    public static void star(int N){
        
       if( N<1){
            return;
        }
       
        for (int i=0; i<N; i++){
            System.out.print("* ");
        }
        System.out.println();
        star(N-1);
         for (int i=0; i<N; i++){
            System.out.print("* ");
        }
        System.out.println();

    }
}