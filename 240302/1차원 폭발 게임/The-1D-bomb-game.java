import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int n,m;
    public static int[][] bombs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        bombs = new int[n][1];

        for (int i = 0; i < n; i++) {
            bombs[i][0] = Integer.parseInt(br.readLine().trim());
        }

        if(m == 1) {
            bombs = new int[n][1];
        }

        for (int i = 0; i < n; i++) {
            simulate();
        }

        int bombCnt = 0;

        for (int i = 0; i < n; i++) {
            if(bombs[i][0] != 0) bombCnt++;
        }

        //남은 폭탄의 개수 출력
        System.out.println(bombCnt);
        //남은 폭탄 출력
        for (int i = 0; i < n; i++) {
            if(bombs[i][0] != 0)
                System.out.println(bombs[i][0]);
        }
    }

    public static void simulate() {
        int seqCnt = 1;

        for (int i = n-1; i > 0; i--) {
            if(bombs[i-1][0] == bombs[i][0]) {
                seqCnt++;
            }
            else{
                if(seqCnt >= m) {
                    //폭탄 터트리기
                    explode(i, i+seqCnt);
                }
                seqCnt = 1;
            }
        }

        //터진 폭탄이 있는 경우 떨어트리기
        dropBombs();
    }

    public static void dropBombs() {
        int[] temp = new int[n];
        int tempIdx = 0;

        for (int i = n-1; i >= 0; i--) {
            if(bombs[i][0] != 0) {
                temp[tempIdx++] = bombs[i][0];
            }
        }
        tempIdx = 0;

        for (int i = n-1; i >= 0; i--) {
            bombs[i][0] = temp[tempIdx++];
        }
    }

    public static void explode(int start, int end) {
        // start부터 end까지 터트리기
        for (int i = start; i < end ; i++) {
            bombs[i][0] = 0;
        }
    }
}