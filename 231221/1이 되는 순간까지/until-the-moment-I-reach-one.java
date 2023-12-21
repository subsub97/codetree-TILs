import java.util.*;
import java.io.*;

public class Main {
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        
        functionMakeOne(n);

        System.out.print(count);
    }

    public static void functionMakeOne(int n) {
        if(n == 1) {
            return;
        }
        count++;

        if(n % 2 == 0) {
            functionMakeOne(n / 2);
        }
        else {
            functionMakeOne(n / 3);
        }
    }
}