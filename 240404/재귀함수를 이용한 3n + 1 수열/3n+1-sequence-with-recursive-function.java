import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        System.out.println(calc(num));


    }

    public static int calc(int num){

        if(num==1) return 0;

        else if(num%2==0) return calc(num/=2) +1 ;

        else {
            num = 3*num +1;
            return calc(num) +1 ;
        }   
    }
}