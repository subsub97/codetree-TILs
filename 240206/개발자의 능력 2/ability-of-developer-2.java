import java.util.Scanner;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int arr[]=new int[6];
        for(int i=0; i<6; i++){
            int abililty = sc.nextInt();
            arr[i]=abililty;
        }
        Arrays.sort(arr);
        int [][] team = new int[2][3];
        for (int i=0; i<3; i++){
            team[0][i]=arr[i];
        }
        for (int i=3; i<6; i++){
            team[1][5-i]=arr[i];
        }
        int sum[]=new int[3];
        for (int i=0; i<3; i++){
            sum[i]=team[0][i]+team[1][i];
        }
        Arrays.sort(sum);
        System.out.println(sum[2]-sum[0]);
    }
}