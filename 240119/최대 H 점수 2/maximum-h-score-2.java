import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] numbers = new int[n];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
        
        for(int i = 1; i <= 100; i++) {
            int cnt = 0;
            int curL = l;

            for(int j = n-1; j >= 0; j--){
                if(numbers[j] + curL >= i){
                    if(numbers[j] < i){
                        curL -=  (i - numbers[j]);
                    }
                    cnt++;
                    if(cnt >= i){
                        break;
                    }
                }
            }

            if(cnt < i){
                System.out.print(i-1);
                break;
            }
        }
    }
}