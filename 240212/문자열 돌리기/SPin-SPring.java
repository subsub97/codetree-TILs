import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int len = s.length();
        System.out.println(s);
        for(int i = 0; i < len; i++) {
            s = s.substring(len-1) + s.substring(0,len-1);
            System.out.println(s);
        }

        
    }
}