import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        starPrint(T);
    }

    public static void starPrint(int n){
    if (n==0){
        return;
    }
    starPrint(n-1);
    for (int i=0; i<n; i++){
        System.out.printf("*");
    }
    System.out.printf("\n");
    
    }

}