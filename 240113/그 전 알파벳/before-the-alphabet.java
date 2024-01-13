import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char alpa = br.readLine().charAt(0);
        int number = ((int)alpa - 1);
        if(number < 97) {
            number += 26;
        }
        System.out.print((char)number);
    }
}