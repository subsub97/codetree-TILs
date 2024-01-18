import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int cost = 0;
        int cnt = 1;
        for(int i = n-1; i > 0; i--) {
            if(numbers[i] > numbers[i-1]){
                cnt++;
                continue;
            }
            break;
        }
        System.out.println(n - cnt);

    }
}