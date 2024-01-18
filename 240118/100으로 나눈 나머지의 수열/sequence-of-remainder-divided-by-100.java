import java.util.*;
import java.io.*;

public class Main {
    public static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine().trim());
        System.out.print(getNumber(n));
        
    }

    public static int getNumber(int level) {
        if(level == 1) {
            return 2;
        }
        else if (level == 2) {
            return 4;
        }

        return (getNumber(level-1) * getNumber(level -2) )% 100;
    }
}