import java.io.*;
import java.util.*;

public class Main {
    public static int n,k,x;
    public static boolean hasNext;
    public static int[][] grid;
    public static boolean[][] visited;
    public static Queue<Pair> q = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        int startRow = Integer.parseInt(st.nextToken()) - 1;
        int startCol = Integer.parseInt(st.nextToken()) - 1;

        Pair p = new Pair(startRow, startCol);
        x = grid[startRow][startCol];
        q.add(p);
        int ansRow = 0;
        int ansCol = 0;

        while(k-- > 0) {
            hasNext = false;
            visited = new boolean[n][n];
            p = bfs(); //갈 수 있는 모든 경로를 탐방
            if(!hasNext) {
                //갈 곳이 없는 경우
                ansRow = p.row + 1;
                ansCol = p.col + 1;
                break;
            }
            q.add(p);
            x = grid[p.row][p.col];
            ansRow = p.row + 1;
            ansCol = p.col + 1;
        }

        System.out.println(ansRow + " " + ansCol);

    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public static boolean canGo(int r, int c) {
        // 범위 이내, x보다 작은 경우, 방문하지 않은 경우 방문 가능
        if(!inRange(r,c)) return false;
        if(grid[r][c] >= x || visited[r][c]) return false;
        return true;
    }

    public static Pair bfs() {
        Pair nextPair = new Pair(-1, -1);

        while(!q.isEmpty()) {
            Pair p = q.poll();

            int[] drs = {1,-1,0,0};
            int[] dcs = {0,0,1,-1};
            int dir = 0;

            for(int i = 0; i < 4; i++) {
                int nR = p.row + drs[dir];
                int nC = p.col + dcs[dir];
                dir++;

                if(canGo(nR,nC)) {
                    //갈 수 있는 경우라면
                    visited[nR][nC] = true;
                    hasNext = true;
                    q.add(new Pair(nR,nC));

                    if(nextPair.maxValue < grid[nR][nC]){
                        nextPair = renew(nextPair, nR, nC);
                    }

                    else if(nextPair.maxValue == grid[nR][nC]) {
                        if(nextPair.row > nR) {
                            nextPair = renew(nextPair, nR, nC);
                        }
                        else if(nextPair.row == nR && nextPair.col > nC) {
                            nextPair = renew(nextPair, nR, nC);
                        }
                    }

                }
            }
        }

        return nextPair;
    }

    public static Pair renew(Pair p, int nR, int nC) {
        p.row = nR;
        p.col = nC;
        p.maxValue = grid[nR][nC];
        return p;
    }

    public static class Pair {
        int row;
        int col;
        int maxValue = 0;

        Pair(int r, int c) {
            row = r;
            col = c;
        }
    }

}