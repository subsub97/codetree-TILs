import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();

        int lenA = a.length();
        int lenB = b.length();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i <lenA; i++) {
            if(Character.isDigit(a.charAt(i))) {
                sb.append(a.charAt(i));
            }
        }
        int numberA = Integer.parseInt(sb.toString());
        sb.setLength(0);

        for(int i = 0; i <lenB; i++) {
            if(Character.isDigit(b.charAt(i))) {
                sb.append(b.charAt(i));
            }
        }

        int numberB = Integer.parseInt(sb.toString());
        
        System.out.print(numberA + numberB);

}
}