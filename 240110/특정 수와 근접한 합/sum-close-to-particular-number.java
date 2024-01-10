import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int allSum = 0;
        int answer = Integer.MAX_VALUE;

        int[] numbers = new int[N];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
    
        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st1.nextToken());
            allSum += numbers[i];
        }

        for(int i = 0; i < N; i++) {
            for(int j = i+1; j < N; j++) {
                answer = Math.min(answer,Math.abs(S - (allSum - (numbers[i] + numbers[j]))));
            }
        }
    
        System.out.print(answer);
    }
}