import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        char[] hallOfFame = {'C'};

        int a = 0;
        int b = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char name = st.nextToken().charAt(0);
            int score = Integer.parseInt(st.nextToken());

            if(name == 'A') {
                a += score;
            }
            else{
                b += score;
            }

            if(a > b) {
                if(hallOfFame[0] != 'A') {
                    hallOfFame[0] = 'A';
                    ans++;
                }
            } else if (b > a) {
                if(hallOfFame[0] != 'B') {
                    hallOfFame[0] = 'B';
                    ans++;
                }
            }
            else{
                if(hallOfFame[0] != 'C') {
                    hallOfFame[0] = 'C';
                    ans++;
                }
            }
        }
        System.out.println(ans);

    }
}