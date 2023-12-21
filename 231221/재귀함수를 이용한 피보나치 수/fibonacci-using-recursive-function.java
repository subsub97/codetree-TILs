import java.util.*;
import java.io.*;

public class Main {
    
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()) -1;

        int answer = fibo(N);

        System.out.print(N);
    }

    public static int fibo(int n) {
        if(n <= 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }

        return fibo(n-1) + fibo(n-2);
    }
}