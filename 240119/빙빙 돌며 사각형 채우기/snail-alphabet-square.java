import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static int m;
    public static boolean[][] visited;
    public static char[][] grid;
    public static boolean inRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }

    public static boolean canGo(int r, int c) {
        if(!inRange(r,c)) return false;
        if(visited[r][c]) return false;
        return true;
    }

    public static void drawChar(int dr,int dc,char alpa){

        int[] drs = {0, 1, 0, -1};
        int[] dcs = {1, 0, -1, 0};

        grid[dr][dc] = alpa;
        alpa = (char) ((int) alpa + 1);
        visited[dr][dc] = true;

        int nextDr = dr;
        int nextDc = dc;

        int dir = 0;
        int error = 0;

        while(true){
            nextDr = nextDr + drs[dir];
            nextDc = nextDc + dcs[dir];

            if(canGo(nextDr,nextDc)) {
                grid[nextDr][nextDc] = alpa;
                visited[nextDr][nextDc] = true;
                error =0;
                alpa = (char) ((int) alpa + 1);
                if((int) alpa > 90){
                    alpa = (char)(90-26);
                }
            }

            else{
                error++;
                nextDr -= drs[dir];
                nextDc -= dcs[dir];
                dir = (dir + 1) %4;

            }
            if(error == 4){
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new char[n][m];
        visited = new boolean[n][m];

        drawChar(0,0,'A');

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}