import java.util.*;
import java.io.*;

public class Main {
    static int a=2;
    static int b=4;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        int num = Integer.parseInt(br.readLine());
        System.out.println(calc(num));
    }

    
    

    public static int calc (int num){

        if(num==1){
            return 2;
        }
        else if(num==2){
            return 4;
        }

        else if(num<=0) return 1;

        else{
            
            return (calc(num-2)*calc(num-1))%100;
        }
    }
}