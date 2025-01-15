import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String T = br.readLine();  
        String P;

        StringBuilder sb = new StringBuilder();
        
        for(int i = T.length() - 1; i >= 0; i--) {
            sb.append(T.charAt(i));
        }

        P = sb.toString();

        int maxSize = 100_000;
        int[] f = new int[maxSize + 1];
        f[0] = 0;

        int n = T.length();
        int m = P.length();

        T = "#" + T;
        P = "#" + P;

        int jIdx = 1;

        int j = 0;
        for(int i = 1; i <= m; i++) {
            //p 내부의 접두,접미 패턴을 찾는다.
            
  
            // a b c a b c a a a
            // a a a c b a c b a

            //# a b a b a
            //# a b a b a
            while(j > 0 && T.charAt(j + 1) != P.charAt(i)) {
                j = f[j-1];
            }

            if(T.charAt(j + 1) == P.charAt(i)) {
                
                f[i] = j + 1; // 0 , 
                j = f[i];
            }
        }

        // 한 문자씩 비교하며 패턴 문자열과 일치하게 되는 순간들 구하기
        // int j = 0;
        int ans = 0;
        // for(int i = 1; i <= n; i++) {
        //     while(j >= 0 && P.charAt(j + 1) != T.charAt(i)) {
        //         j = f[j];
        //     }
        //     j++;

        //     if(j == m) {
        //         j = f[j];
        //     }
        // }

        for(int i = 1; i <= m; i++) {
            ans = Math.max(ans,f[i]);
        }

        System.out.println(ans);

    }
}