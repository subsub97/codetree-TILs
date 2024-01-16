import java.util.Scanner;

public class Main {
    public static final int MAX_X = 10;
    public static final int MAX_N = 20;
    
    public static int n;
    public static int[] x = new int[MAX_N];
    public static int[] y = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력
        n = sc.nextInt();

        for(int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }

        int ans = 0;

        // 모든 직선에 대해 전부 시도해 봅니다.
        for(int i = 0; i <= MAX_X; i++)
            for(int j = 0; j <= MAX_X; j++)
                for(int k = 0; k <= MAX_X; k++) {
                    // success : 직선 3개로 모든 점을 지나게 할 수 있으면 true
                    boolean success = true;
                    // x축에 평행한 직선 3개로
                    // 모든 점을 지나게 할 수 있는 경우
                    for(int l = 0; l < n; l++) {
                        // 해당 점이 직선에 닿으면 넘어갑니다
                        if(x[l] == i || x[l] == j || x[l] == k)
                            continue;
                        
                        success = false;
                    }
                    if(success){
                        ans = 1;
                        break;
                    }

                    // x축에 평행한 직선 2개와 y축에 평행한 직선 1개로
                    // 모든 점을 지나게 할 수 있는 경우
                    success = true;
                    for(int l = 0; l < n; l++) {
                        // 해당 점이 직선에 닿으면 넘어갑니다
                        if(x[l] == i || x[l] == j || y[l] == k)
                            continue;
                        
                        success = false;
                    }
                    if(success)
                        ans = 1;
                    
                    // x축에 평행한 직선 1개와 y축에 평행한 직선 2개로
                    // 모든 점을 지나게 할 수 있는 경우
                    success = true;
                    for(int l = 0; l < n; l++) {
                        // 해당 점이 직선에 닿으면 넘어갑니다
                        if(x[l] == i || y[l] == j || y[l] == k)
                            continue;
                        
                        success = false;
                    }
                    if(success)
                        ans = 1;
                    
                    // y축에 평행한 직선 3개로
                    // 모든 점을 지나게 할 수 있는 경우
                    success = true;
                    for(int l = 0; l < n; l++) {
                        // 해당 점이 직선에 닿으면 넘어갑니다
                        if(y[l] == i || y[l] == j || y[l] == k)
                            continue;
                        
                        success = false;
                    }
                    if(success)
                        ans = 1;
                    
                }

        System.out.print(ans);
    }
}