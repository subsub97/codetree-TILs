import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int total=1;
   
        for (int i=0; i<str.length; i++){
    
            total *= Integer.parseInt(str[i]);
      
        
        }
        System.out.println(splitNum(total));

    }

    public static int splitNum(int num){


        if (num == 0){
            return 0;
        }
        return splitNum(num/10) + num%10;


    }
}