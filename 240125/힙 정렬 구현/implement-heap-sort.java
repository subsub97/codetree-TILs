import java.io.*;
import java. util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n+1];

        for(int i =1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        heapSort(arr,n);

        for(int i = 1; i <= n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void heapifiy(int[] arr,int n, int largest) {
        // 부모 노드와 자식 노드를 비교해서 최대 값을 부모노드로 지정함
        int idx = largest;
        int leftNode = idx * 2;
        int rightNode = idx * 2 + 1;
        int temp;

        if(n >= leftNode && arr[leftNode] > arr[largest]) {
            largest = leftNode;
        }

        if(n >= rightNode && arr[rightNode] > arr[largest]) {
            largest = rightNode;
        }
        if(largest != idx) {
            temp = arr[idx];
            arr[idx] = arr[largest];
            arr[largest] = temp;
            heapifiy(arr,n,largest);
        }

    }

    public static void heapSort(int[] arr, int n) {
        for(int i = n/2; i >=1; i--) {
            heapifiy(arr,n,i);
        }
        for(int i = n; i > 1; i--) {
            int temp = arr[i];
            arr[i] = arr[1];
            arr[1] = temp;
            heapifiy(arr,i-1,1);
        }
    }
}