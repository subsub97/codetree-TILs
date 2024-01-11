public class Main {
    public static void main(String[] args) {
        starPrint(5);
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