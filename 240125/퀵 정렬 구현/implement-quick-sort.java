import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        quikSort(arr,0,n-1);

        for(int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    public static int partition(int[] arr,int low, int high) {
        int pivot = high;

        int i = low - 1;
        for(int j = low; j <= high; j++) {
            if(arr[j] < arr[pivot]) {
                int temp = arr[j];
                arr[j] = arr[++i];
                arr[i] = temp;
            }
        }
        int temp = arr[pivot];
        arr[pivot] = arr[i + 1];
        arr[i+1] = temp;

        return i + 1;
    }

    public static void quikSort(int[] arr, int low, int high) {
        if(low < high) {
            int pos = partition(arr, low, high);

            quikSort(arr, low, pos - 1);
            quikSort(arr, pos + 1, high);
        }
    }
}