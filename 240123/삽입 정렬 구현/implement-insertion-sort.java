import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[n];

        for(int i = 0; i<n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < numbers.length; i++) {
            int j = i - 1;
            int key = numbers[i];
            while(j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j];
                j--;
            }
            numbers[j+1] = key;
        }


        for(int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }
}