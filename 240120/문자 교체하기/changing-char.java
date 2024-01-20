import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] word1 = st.nextToken().toCharArray();
        char[] word2 = st.nextToken().toCharArray();

        word2[0] = word1[0];
        word2[1] = word1[1];

        System.out.println(String.valueOf(word2));


    }
}