import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];
        for(int i=0; i<n; i++){
            numbers[i] = sc.nextInt();
        }
        sort(numbers);
    }

    public static void sort(int numbers[]){
        Arrays.sort(numbers);
        for (int i=0; i<numbers.length; i++){
            System.out.print(numbers[i]+ " ");
        }
        Integer[] newNumbers = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
        Arrays.sort(newNumbers,Collections.reverseOrder());
        System.out.println();
        for (int i=0; i<newNumbers.length; i++){
            System.out.print(newNumbers[i]+ " ");
        }
    }
}