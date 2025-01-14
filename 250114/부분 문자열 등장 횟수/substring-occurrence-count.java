import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String T = br.readLine();        
        String P = br.readLine();

        int maxSize = 100_000;
        int[] f = new int[maxSize + 1];
        f[0] = -1;

        int n = T.length();
        int m = P.length();

        T = "#" + T;
        P = "#" + P;

        for(int i = 1; i <= m; i++) {
            //p 내부의 접두,접미 패턴을 찾는다.
            int j = f[i-1];
            while(j >= 0 && P.charAt(j + 1) != P.charAt(i)) {
                j = f[j];
            
            }

            f[i] = j + 1;
        }

        // 한 문자씩 비교하며 패턴 문자열과 일치하게 되는 순간들 구하기
        int j = 0;
        int ans = 0;
        for(int i = 1; i <= n; i++) {
            while(j >= 0 && P.charAt(j + 1) != T.charAt(i)) {
                j = f[j];
                
            }
            j++;

            if(j == m) {
                ans++;
                j = f[j];
            }
        }

        System.out.println(ans);

    }
}