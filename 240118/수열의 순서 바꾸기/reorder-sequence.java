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

        for (int i = 0; i < n ; i++) {
            boolean change = false;
            for (int j = i; j < n-1; j++) {
                if(numbers[j] > numbers[j+1]){
                    change = true;
                    break;
                }
            }
            if(change) cost++;
        }

        System.out.println(cost);

    }
}