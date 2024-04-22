import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];
        for (int i=0; i<n; i++){
            numbers[i] = sc.nextInt();
        }
        
        System.out.println(calc(n-1, numbers));
        

    }

    public static int calc(int n,int numbers[]){
            if (n==0) return numbers[0];

            int bigNum = numbers[n]>numbers[n-1]? numbers[n] :numbers[n-1];
            for (int i=bigNum; bigNum<=numbers[n]*numbers[n-1]; bigNum++){
                if((bigNum%numbers[n]==0) &&(bigNum%numbers[n-1]==0)){
                    break;
                }
            }

            numbers[n-1]= bigNum;
            return calc(n-1,numbers);
            

           
        }
    }