import java.io.*;
import java.util.*;

public class Main {
    public static final int MAX_SIZE = 1001;
    public static char[] line = new char[MAX_SIZE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());



        for(int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());

            char initial = st1.nextToken().charAt(0);
            int location = Integer.parseInt(st1.nextToken());

            line[location] = initial;
        }

        int ans =0;

        for(int i = a; i <= b; i++) {
            int d1 = findS(i);
            int d2 = findN(i);

            if(d1 <= d2) {
                ans++;
            }
        }
        System.out.print(ans);
    }



    public static int findS(int location) {
        int cnt = 0;

        while(true) {
            if(line[location] == 'S') return 0;
            cnt++;
            if(location - cnt >= 0) {
                if(line[location - cnt] == 'S'){
                    return cnt;
                }
            }
            if(location + cnt < 1001){
                if(line[location + cnt] == 'S') {
                    return cnt;
                }
            }
        }
    }

    public static int findN(int location) {
        int cnt = 0;

        while(true) {
            if(line[location] == 'N') return 0;
            cnt++;
            if(location - cnt >= 0) {
                if(line[location - cnt] == 'N'){
                    return cnt;
                }
            }
            if(location + cnt < 1001){
                if(line[location + cnt] == 'N') {
                    return cnt;
                }
            }

        }
    }
}