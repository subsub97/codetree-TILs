import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.print(sumFunction(n));
    }

    public static int sumFunction(int n) {
        if(n <= 1)
            return n;

        return sumFunction(n-2) + n;
        
    }
}