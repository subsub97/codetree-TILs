import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int T=sc.nextInt();
        startPrint(T);
    }
    public static void startPrint(int n){

    if(n==0){
        return;
    }
    for (int i=0; i<n; i++){
        System.out.printf("* ");
    }
    System.out.printf("\n");
    startPrint(n-1);
    for (int i=0; i<n; i++){
        System.out.printf("* ");
    }
    System.out.printf("\n");
    return;
}
}