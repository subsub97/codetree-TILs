import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        System.out.print(f(n));
    }

    public static int f(int n){
        if(n == 1) return 1;
        if(n == 2) return 2;

        return n / 3 + f(n-1);
    }
}