import java.util.*;
import java.io.*;

public class Main {
    static int N,elaspedTime,dir;
    static int[][] grid;
    static int[][] visited;

    static boolean isEscape = false;
    static boolean neverEscape = false;

    static int startR,startC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        dir = 0;
        elaspedTime = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        startR = Integer.parseInt(st.nextToken()) - 1;
        startC = Integer.parseInt(st.nextToken()) - 1;
    

        grid = new int[N][N];
        visited = new int[N][N];

        for(int i =0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                if(line.charAt(j) == '#') {
                    grid[i][j] = 1;
                }
                else{
                    grid[i][j] = 0;
                }
            }
        }

        int[] dwrs = {1, 0, -1, 0};
        int[] dwcs = {0, -1, 0, 1};

        dir = 0;
        
        while(!isEscape) {
            move();
            if(neverEscape){
                break;
            }
        }

        if(neverEscape){
            System.out.print(-1);        
        }
        else{
            System.out.print(elaspedTime);
        }
        
        
    }

    public static void move() {
        // 동 -> 남 -> 서 -> 북
        int[] drs = {0, 1, 0, -1};
        int[] dcs = {1, 0, -1, 0};
        int[] dwrs = {1, 0, -1, 0};
        int[] dwcs = {0, -1, 0, 1};
        // 바라보고 있는 방향으로 이동하는 것이 가능하지 않은 경우(반시계 90도 전환)
        
        int nR = startR + drs[dir];
        int nC = startC + dcs[dir];
        int nRw = nR + dwrs[dir];
        int nCw = nC + dwcs[dir];

        elaspedTime++;
        
        if(inRange(nR,nC)) {
            if(visited[nR][nC] > 3) {
                neverEscape = true;
                return;
            }

            if(grid[nR][nC] == 1) {

                //벽인 경우
                dir = (dir + 3) % 4;
                nR = startR + drs[dir];
                nC = startC + dcs[dir];
                nRw = nR + dwrs[dir];
                nCw = nC + dwcs[dir];
                if(!inRange(nR,nC)) {
                    isEscape = true;
                    return;
                }
                else{
                    int cnt = 0;
                    while(grid[nR][nC] == 1) {
                        dir = (dir + 3) % 4;

                        nR = startR + drs[dir];
                        nC = startC + dcs[dir];
                        nRw = nR + dwrs[dir];
                        nCw = nC + dwcs[dir];
                        cnt++;
                        if(cnt > 4) {
                            neverEscape = true;
                            return;
                        }
                    }

                    if(grid[nRw][nCw] != 1){
                        //System.out.print("ㅁㅁㅁㅁ");
                        dir = (dir + 1) % 4;
                    }
                }
            }
            else{
                if(grid[nRw][nCw] != 1){
                    // System.out.print("aaa");
                    dir = (dir + 1) % 4;
                }
            }
            visited[nR][nC]++;
            if(visited[nR][nC] > 3) {
                neverEscape = true;
                return;
            }
        }
        else{
            // 바라보고 있는 방향으로 이동 가능한 경우(격자 밖이면 탈출 성공)
            isEscape = true;
            
        }
        // System.out.println(nR + " " + nC);
        startR = nR;
        startC = nC;
        
    }

    public static boolean inRange(int r, int c){
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
//33 42