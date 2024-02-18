import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char s1 = st.nextToken().charAt(0);
        char s2 = st.nextToken().charAt(0);

        System.out.print((int)s1 + (int)s2 +" ");
        System.out.print(Math.abs((int)s1 - (int)s2));


    }
}