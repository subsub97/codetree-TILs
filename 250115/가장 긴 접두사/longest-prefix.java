import java.util.*;
import java.io.*;

public class Main {

    static int[] table;
    static String T;
    static String rt;
    static int max = 0;

    public static void main(String[] args) throws Exception{
        // Please write your code here.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = br.readLine();
        table = new int[T.length()];

        rt = new StringBuilder(T).reverse().toString();
        failureFunction();

        System.out.println(max);
        
    }

    public static void failureFunction() {
        
        int pIdx = 0;

        char[] arr = T.toCharArray();

        for(int idx = T.length() - 1; idx >= 0; idx--) {
            while(pIdx != 0 && arr[idx] != arr[pIdx]) pIdx = table[pIdx - 1];

            if(arr[idx] == arr[pIdx]) {
                pIdx++;
                table[idx] = pIdx;
                max = Math.max(pIdx, max);
            }
        }
    }
}