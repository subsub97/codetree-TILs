import java.util.*;

public class Main {
    
    static int cnt =0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.print(count(N));
    }

    public static int count(int N){
        
        if (N==1){
            return cnt;
        }

        if(N%2==0){
           cnt++;
           return count(N/2);
        }
        else {
           cnt++;
           return count(N/3);
        }
       
    }
}