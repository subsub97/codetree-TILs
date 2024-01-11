import java.util.*;
import java.io.*;

public class Main {
    public static final int MAX_SIZE = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int[] numbers = new int[MAX_SIZE];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;

        for(int i = 0; i < n; i++) {
            int[] sectionNumbers = new int [n];
            int exists = 1;
            for(int j = i; j < n; j++) {
                sectionNumbers[j] = numbers[j];
                int sectionSum = 0;
                sectionSum += sectionNumbers[j];

                double sectionAverage = (double)sectionSum / (j - i + 1);

                for(int k = i; k <= j; k++) {
                    if(sectionNumbers[k] == sectionAverage) {
                        
                        ans++;
                        exists = 0;
                    }
                }
            }
        }
        System.out.print(ans);

    }
}