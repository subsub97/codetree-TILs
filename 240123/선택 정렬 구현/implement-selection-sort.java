import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[n];

        for(int i =0; i<n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        selectionSort(numbers);
        for(int i = 0; i < n; i++) {
            System.out.print(numbers[i] + " ");
        }
        

    }

    public static void selectionSort(int[] numbers) {
        
        for(int i = 0; i < numbers.length; i++) {
            int min = i;
            int minValue = Integer.MAX_VALUE;
        
            for(int j = i; j < numbers.length; j++) {
                if(numbers[j] < numbers[min]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[min];
                    numbers[min] = temp;
                }
            }
        }
    }
}