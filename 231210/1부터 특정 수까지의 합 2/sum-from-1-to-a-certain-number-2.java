import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int answer = sumUntilN(n);
        System.out.print(answer);
    }

    public static int sumUntilN(int n) {
        if(n == 0)  {
            return 0;
        }

        return n + sumUntilN(n-1);


    }
}