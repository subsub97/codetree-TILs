import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n*2];
        for (int i=0; i<n*2; i++){
            numbers[i] = sc.nextInt();
  
        }
        Arrays.sort(numbers);
   
        System.out.println(numbers[n-1]+numbers[n]);
    }
}