import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] alpas = new char[4];

        for (int i = 0; i < 4; i++) {
            alpas[i] = st.nextToken().charAt(0);
        }

        int minCount = 0;

        for (int i = 0; i < 4; i++) {
            char findAlpa = (char) (65 + i);
            for (int j = 0; j < 4; j++) {
                if(alpas[j] == findAlpa) {
                    minCount += j - i;
                    char temp = alpas[i];
                    alpas[i] = alpas[j];
                    alpas[j] = temp;
                }
            }
        }
        System.out.println(minCount);

    }
}