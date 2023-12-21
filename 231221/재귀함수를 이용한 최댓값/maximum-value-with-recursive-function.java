import java.util.*;
import java.io.*;

public class Main {

    static int[] arr;
    static int maxNumber = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        findMaxNumber(n-1);
        System.out.print(maxNumber);
    }

    public static void findMaxNumber(int n) {
        if(arr[n] > maxNumber) {
            maxNumber = arr[n];
        }

        if(n == 0) {
            return;
        }

        findMaxNumber(n-1);
    }
}