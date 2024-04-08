import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int n,maxSum;
    public static int[][] grid;
    public static boolean[] selected;
    public static ArrayList<Integer> numbers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        maxSum = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        grid = new int[n][n];
        selected = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        choose(0);
        System.out.println(maxSum);

    }

    public static int sumNumbers() {
        int sumValue = 0;

        for (Integer number : numbers) {
            sumValue += number;
        }

        return sumValue;
    }

    public static void choose(int depth) {
        if(depth == n) {
            maxSum = Math.max(sumNumbers(), maxSum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if(selected[i]) continue;
            numbers.add(grid[depth][i]);
            selected[i] = true;
            choose(depth + 1);
            selected[i] = false;
            numbers.remove(numbers.size() - 1);
        }
    }
}