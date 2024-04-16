import java.util.*;
import java.io.*;
public class Main {
  

    public static void main(String[] args) throws IOException {
        
       
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        int num = Integer.parseInt(br.readLine());
        System.out.println(calc(num));
    }

    public static int calc (int num){

        if (num==1) return 1;
        
        else if(num==2) return 2;
        
        else if(num<=0) return 1;
        else {
            return calc (num/3)+calc(num-1);
        }

    }
}