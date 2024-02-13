import java.util.Scanner;

public class Main {
    public static int[] arr = new int[5];

    public static int minGap = Integer.MAX_VALUE;

    public static int diff(int sum, int i, int j, int k, int p) {

        int first = arr[i] + arr[j];
        int second = arr[k] + arr[p];
        int third = sum - first - second;

        int minteam=Math.min(first,second);
        minteam = Math.min(minteam,third);
        int maxteam=Math.max(first,second);
        maxteam=Math.max(maxteam,third);

        int minGap = maxteam-minteam;
        if (maxteam==minteam){
            minGap=Integer.MAX_VALUE;
        }
        if (first==second || second ==third || first==third){
            minGap=Integer.MAX_VALUE;
        }
        return minGap;
    }

    public static void main(String[] args) {

        int sum = 0;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++)

                for (int k = 0; k < 5; k++) {
                    for (int p = k + 1; p < 5; p++) {
                        if (i == k || i == p || j == k || j == p) {
                            continue;

                        }

                        minGap = Math.min(minGap, diff(sum, i, j, k, p));
                        if(minGap==Integer.MAX_VALUE){
                            minGap=-1;
                        }
                    }
                }
        }

        System.out.println(minGap);

    }
}