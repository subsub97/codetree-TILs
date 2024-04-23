import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];
        for(int i=0; i<n; i++){
            numbers[i] = sc.nextInt();
        }
       
        for (int i=0; i<n; i+=2){
            int[] sliceNumbers = Arrays.copyOfRange(numbers, 0, i+1);
            Arrays.sort(sliceNumbers);
            System.out.print(sliceNumbers[sliceNumbers.length/2]+ " ");
        }
    }
}