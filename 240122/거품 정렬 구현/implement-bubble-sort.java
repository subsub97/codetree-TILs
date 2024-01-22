import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[n];

        for(int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        boolean sorted = false;

        while(!sorted) {
            sorted = true;
            for(int i = 0; i < n-1; i++) {
                if(numbers[i] > numbers[i+1]) {
                    sorted = false;
                    int temp = numbers[i];
                    numbers[i] = numbers[i+1];
                    numbers[i+1] = temp;
                }
            }            
        }

        for(int i = 0; i < n; i++) {
            System.out.print(numbers[i] + " ");
        }
    }
}