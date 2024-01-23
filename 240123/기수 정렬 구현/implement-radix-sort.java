import java.util.*;
import java.io.*;

public class Main {
    public static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[n];

        int maxNum = 0;

        for(int i =0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            maxNum = Math.max(maxNum,numbers[i]);
        }

        int cnt = 1;
        while(true) {
            if(maxNum < 10) break;
            maxNum /= 10;
            cnt++;
        }
        k = cnt;

        radixsort(numbers,n);

        for(int i =0; i<n; i++){
            System.out.print(numbers[i] +" ");
        }
    }

    static void radixsort(int arr[], int n) {
        int p = 1;

        for(int pos = 0; pos < k; pos++) {
            ArrayList<Integer>[] arrNew = new ArrayList[10];
            for(int i =0; i <10; i++) {
                arrNew[i] = new ArrayList<>();
            }

            for(int i =0; i < n; i++) {
                int digit = (arr[i]/p) % 10;
                arrNew[digit].add(arr[i]);
            }

            int idx = 0;
            for(int i = 0; i < 10; i++) {
                for(int j = 0; j < arrNew[i].size(); j++) {
                    arr[idx++] = arrNew[i].get(j);
                }
            }
            p *= 10;

        }
    }

}