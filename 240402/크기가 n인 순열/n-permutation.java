import java.util.*;
import java.io.*;

public class Main {
    public static int n;
    public static boolean[] visited;
    public static ArrayList<Integer> number = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        choose(0);

    }

    public static void printNumber() {
        for(int i = 0; i < n; i++) {
            System.out.print(number.get(i) + " ");
        }
        System.out.println();
        return;
    }

    public static void choose(int depth) {
        if(depth == n) {
            printNumber();
            return;
        }

        for(int i = 1; i <= n; i++) {
            if(visited[i - 1]) {
                continue;
            }
            number.add(i);
            visited[i-1] = true;
            choose(depth + 1);
            visited[i-1] = false;
            number.remove(number.size() - 1);
        }
    }
}