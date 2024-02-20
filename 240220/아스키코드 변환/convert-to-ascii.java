import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char a = st.nextToken().charAt(0);
        int b = Integer.parseInt(st.nextToken());

        System.out.print((int)a  + " " + (char)b);
    }
}