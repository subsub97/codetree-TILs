import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        char[] hallOfFame = {'D'};

        int a = 0;
        int b = 0;
        int c = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char name = st.nextToken().charAt(0);
            int score = Integer.parseInt(st.nextToken());

            if(name == 'A') {
                a += score;
            }
            else if(name =='B'){
                b += score;
            }
            else{
                c += score;
            }

            if(a > b && a > c) {
                if(hallOfFame[0] != 'A') {
                    hallOfFame[0] = 'A';
                    ans++;
                }
            } else if (b > a && b > c) {
                if(hallOfFame[0] != 'B') {
                    hallOfFame[0] = 'B';
                    ans++;
                }
            } else if( c > a && c > b){
                if(hallOfFame[0] != 'C'){
                    hallOfFame[0] = 'C';
                    ans++;
                }
            } else if(b == c && c>a){
                if(hallOfFame[0] != 'E'){
                    hallOfFame[0] = 'E';
                    ans++;
                }
            } else if(a == c && a > b) {
                if(hallOfFame[0] != 'F'){
                    hallOfFame[0] = 'F';
                    ans++;
                }
            } else if(a == b && a>c) {
                if(hallOfFame[0] != 'G'){
                    hallOfFame[0] ='G';
                    ans++;
                }
            }
            else{
                if(hallOfFame[0] != 'D') {
                    hallOfFame[0] = 'D';
                    ans++;
                }
            }
        }
        System.out.println(ans);

    }
}