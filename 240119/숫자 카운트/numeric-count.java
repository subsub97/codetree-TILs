import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] numbers = new int[n];
        int[] strikes = new int[n];
        int[] balls = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            numbers[i] = Integer.parseInt(st.nextToken());
            strikes[i] = Integer.parseInt(st.nextToken());
            balls[i] = Integer.parseInt(st.nextToken());
        }
        int ans = 0;
        for (int i = 1; i <= 9 ; i++) {
            for (int j = 1; j <= 9 ; j++) {
                for (int k = 1; k <= 9; k++) {
                    if(i != j && i != k && j != k){
                        boolean canAns = true;

                    for (int l = 0; l < n; l++) {
                        int strike = 0;
                        int ball = 0;
                        {
                            //서로 다른 수를 골랐을 때
                            if (i == numbers[l] / 100) {
                                strike++;
                            }
                            if (i == (numbers[l] / 10) % 10 || i == (numbers[l] % 10)) {
                                ball++;
                            }
                            if (j == numbers[l] / 10 % 10) {
                                strike++;
                            }
                            if (j == (numbers[l] / 100) || j == (numbers[l] % 10)) {
                                ball++;
                            }
                            if (k == numbers[l] % 10) {
                                strike++;
                            }
                            if (k == (numbers[l] / 10) % 10 || k == (numbers[l] / 100)) {
                                ball++;
                            }

                            if (strike != strikes[l] || ball != balls[l]) {
                                canAns = false;
                                break;
                            }
                        }
                    }
                    if (canAns) {
                        ans++;
                    }
                    }
                }
            }
        }
        System.out.println(ans);

    }
}