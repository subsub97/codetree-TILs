import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.print(sumEachNumberOfPow(n));
    }

    public static int sumEachNumberOfPow(int number) {
            if(number < 10) {
                return number*number;
            }
        return (number % 10) * (number % 10) + sumEachNumberOfPow(number/10);
    }
}