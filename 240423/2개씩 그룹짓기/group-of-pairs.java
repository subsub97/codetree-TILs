import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int max = 0;
        int[] numbers = new int[n*2];
        for (int i=0; i<n*2; i++){
            numbers[i] = sc.nextInt();
        }
        Arrays.sort(numbers);
        for (int i=0; i<n; i++){
            max = numbers[i]+numbers[numbers.length-i-1] > max ? numbers[i]+numbers[numbers.length-i-1] : max;
        }
        System.out.println(max);
    }
}