import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        print(N);

    }
    
    public static void print(int N){
        if(N<1){
            return;
        }
        print(N-1);
        System.out.println("HelloWorld");
    }
}