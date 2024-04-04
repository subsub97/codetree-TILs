import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Main {
    public static ArrayList<Integer> number = new ArrayList<>();
    public static boolean[] selected;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();

        selected = new boolean[a+1];

        choose(0,a);

    }

    public static void printNumber() {
        for(int i = 0; i < number.size(); i++) {
            System.out.print(number.get(i) + " ");
        }
        System.out.println();
        return;
    }

    public static void choose(int depth,int n) {
        if(depth == n) {
            printNumber();
            return;
        }

        for(int i = n; i > 0; i--) {
            if(selected[i]) continue;
            selected[i] = true;
            number.add(i);
            choose(depth + 1, n);
            selected[i] = false;
            number.remove(number.size() - 1);
        }
    }

    
}