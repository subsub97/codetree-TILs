import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        int[] numbers = new int[n+1];
        int[] A = new int[3];
        int[] B = new int[3];

        for(int i = 0; i < 3; i++) {
            A[i] = Integer.parseInt(st1.nextToken());
        }

        for(int i = 0; i < 3; i++) {
            B[i] = Integer.parseInt(st2.nextToken());
        }

        for(int i = 0; i < n; i++) {
            numbers[i] = i+1;
        }

        int[][] possibleNumber = new int[3][n+1];

        //겹치는 경우를 빼주면 된다.
        for(int i =0; i < 3; i++) {
            possibleNumber[i][A[i]] = 1;
            possibleNumber[i][B[i]] = 1;

            for(int j = 1; j < 3; j++) {
                int upNumber = (A[i] + j) > n ? (A[i] + j) % n  : (A[i] + j) % (n+1);
                int downNumber = (A[i] - j) <= 0 ? (A[i] - j) + n : (A[i] - j);

                possibleNumber[i][upNumber] = 1;
                possibleNumber[i][downNumber] = 1;
            }

            for(int j = 1; j < 3; j++) {
                int upNumber = (B[i] + j) > n ? (B[i] + j) % n  : (B[i] + j) % (n+1);
                int downNumber = (B[i] - j) <= 0 ? (B[i] - j) + n : (B[i] - j);

                possibleNumber[i][upNumber] = 1;
                possibleNumber[i][downNumber] = 1;
            }


        }

        int ans = 1;
        for(int i =0; i < 3; i++) {
            int cnt = 0;
            for(int j = 0; j <= n; j++) {
                if(possibleNumber[i][j] == 1) cnt++;
            }
            ans *= (10 - cnt);
        }

        System.out.print(250 - ans);

    }
}