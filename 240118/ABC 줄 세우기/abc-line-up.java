import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        String a = "";

        for (int i = 0; i < 4; i++) {
            a += st.nextToken();
        }

        int minCount = 0;

        for (int i = 0; i < 4; i++) {
            char findAlpa = (char) (65 + i);
            for (int j = 0; j < 4; j++) {
                if(a.charAt(j) == findAlpa && j != i) {
                    minCount += j - i;
                    a =a.substring(0,i) +a.substring(j,j+1) +a.substring(i,j)+a.substring(j+1);
                }
            }
        }
        System.out.println(minCount);

    }
}