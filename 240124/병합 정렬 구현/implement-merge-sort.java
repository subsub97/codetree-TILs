import java.util.*;
import java.io.*;

public class Main {
    public static int[] mergeArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        mergeArr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(arr,0,n-1);

        for (int i : arr) {
            bw.write(i + " ");
        }
        bw.close();

    }

    public static void mergeSort(int[] arr,int low, int high) {
        if(low < high) {
            int mid = (low + high) / 2 ;
            mergeSort(arr,low, mid);
            mergeSort(arr,mid + 1, high);
            mergeArr(arr,low,mid,high);
        }
    }

    public static void mergeArr(int[] arr, int low, int mid, int high) {
        int idx = low;
        int i = low;
        int j = mid + 1;

        while(i <= mid && j <= high) {
            if(arr[i] <= arr[j]) {
                mergeArr[idx++] = arr[i++];
            }
            else{
                mergeArr[idx++] = arr[j++];
            }
        }

        while(i <= mid) {
            mergeArr[idx++] = arr[i++];
        }

        while(j <= high) {
            mergeArr[idx++] = arr[j++];
        }

        for(int index = low; index <= high; index++) {
            arr[index] = mergeArr[index];
        }
    }

}