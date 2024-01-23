import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[n];

        int maxNum = 0;

        for(int i =0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            Math.max(maxNum,numbers[i]);
        }

        radixsort(numbers,n);

        for(int i =0; i<n; i++){
            System.out.print(numbers[i] +" ");
        }
    }

    static void radixsort(int arr[], int n) {
        int m = Arrays.stream(arr).max().getAsInt();
 
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }
 
    static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n]; 
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);
 
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;
 
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];
 
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
 
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }
}