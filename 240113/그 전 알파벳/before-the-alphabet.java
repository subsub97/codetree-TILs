import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char alpa = br.readLine().charAt(0);
        
        System.out.print((char)((int)alpa - 1));
    }
}