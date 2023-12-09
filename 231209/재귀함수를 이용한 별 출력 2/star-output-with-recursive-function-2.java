import java.util.*;
import java.io.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String stringN = br.readLine();
        StringTokenizer st = new StringTokenizer(stringN);

        int n = Integer.parseInt(st.nextToken());

        printStar(n);
        
        String answer = sb.toString();
        System.out.print(answer);
    }

    static void printStar(int n) {
        if(n == 0)  {
            return;
        }
    
        for(int i = 0; i < n; i++) {
            sb.append("*" + " ");
        }
        sb.append("\n");
        printStar(n-1);
        for(int i = 0; i < n; i++) {
            sb.append("*" + " ");
        }
        sb.append("\n");

    }
}