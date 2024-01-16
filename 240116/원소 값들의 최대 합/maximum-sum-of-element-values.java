import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int ans = 0;
        int[] numbers = new int[n];

        StringTokenizer st1 = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st1.nextToken());
        }

        for (int i = 0; i < n; i++) {
            // i번부터 시작해서 계산
            int sum = 0;
            int index = i;
            for (int j = 0; j < m; j++) {
                sum += numbers[index];
                index = numbers[index] - 1;
            }

            ans = Math.max(ans,sum);
        }

        System.out.println(ans);
        
    }
}