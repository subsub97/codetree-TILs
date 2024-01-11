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
            for(int j = i; j < n; j++) {

                int sectionSum = 0;
                for(int k = i; k <= j; k++) { 
                       sectionSum += numbers[k];
                    }
                double sectionAverage = (double)sectionSum / (j - i + 1);

                int exist = 1;

                for(int k = i; k <= j; k++) {
                    if(numbers[k] == sectionAverage) {
                        exist = 0;
                    }
                }
                if(exist == 0) {
                    ans++;
                }
            }
        }
        System.out.print(ans);

    }
}