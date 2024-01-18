import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int odd = 0;
        int even = 0;

        for (int i = 0; i < n; i++) {
            if(Integer.parseInt(st.nextToken()) % 2 == 0) even++;
            else odd++;
        }
        int ans = 0;

        if (odd == 0) {
            System.out.println(1);
        }
        else{
            if(odd > even){
                int gap = odd - even;
                even += gap/2;
                odd -= (gap/2) * 2;
            }

            if(odd < even){
                System.out.println(odd * 2 + 1);
            }
            else if(odd == even){
                System.out.println(odd * 2);
            }
        }
    }
}