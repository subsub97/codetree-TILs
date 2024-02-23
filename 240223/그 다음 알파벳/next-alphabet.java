import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char a = br.readLine().charAt(0);
        if((int)a  + 1 > 122){
            System.out.print('a');
        }
        else{
            System.out.print((char)(int)(a +1));
        }
        
    }
}