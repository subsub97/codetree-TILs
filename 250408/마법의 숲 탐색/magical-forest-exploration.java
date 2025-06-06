import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int R, C, K; // 숲 행 , 숲 옆, 정령의 수
    static int answer;
    static int[][] grid;
    static int[][] exitGrid;
    static int[] drs = {-1, 0, 1, 0};
    static int[] dcs = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()) + 3;
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        answer = 0;
        grid = new int[R][C];
        exitGrid = new int[R][C];

        for (int i = 0; i < K; i++) {
            //각 골렘이 출발하는 열 Ci, 골렘의 출구 방향 정보 di 제공
            // 북 동 남 서
            st = new StringTokenizer(br.readLine());

            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            Golem gol = new Golem(1, c, d);
            simulate(gol, i + 1);
            //골렘 내리기..
        }

        System.out.println(answer);

    }

    // 골렘 내려가기 구현
    // -> 직선, 서쪽, 동쪽
    // -> 현재 숲 골렘 정보 기록하기.
    private static void simulate(Golem gol, int color) {

        //하강
        if (!downGolem(gol)) {
            grid = new int[R][C];
            exitGrid = new int[R][C];
            return;
        }

        gol.color = color;
        gol.drawMyGolem();
        moveFairy(gol); //최대로 내려갔으면 정렬 탈출

    }

    private static boolean downGolem(Golem gol) {
        int r = gol.row;
        int c = gol.col;

        while (true) {
            // 0 : 직 , 1 : 서쪽 턴 하강, 2 : 동쪽 턴 하강
            while (canCenterDown(r, c)) {
                // 하강 가능한 경우 계속 내려가기
                r++;
            }

            // 서쪽 턴 하강
            if (canLeftDown(r, c)) {
                r++;
                c--;

                gol.dir = (gol.dir + 3) % 4;
                continue;
            }

            if (canRightDown(r, c)) {
                r++;
                c++;

                gol.dir = (gol.dir + 1) % 4;
                continue;

            }

//            if (!inGrid(r, c) || !inGrid(r-1, c) || !inGrid(r + 1, c)  || !inGrid(r, c - 1) || !inGrid(r, c + 1)) {
//                return false;
//            }
            if(!inGrid(r,c)) {
                return false;
            }

            gol.row = r;
            gol.col = c;
            break;
        }
        return true;
    }

    private static boolean canCenterDown(int r, int c) {
        // 항상 r,c는 중심 기준
        Pair lp = new Pair(r + 1, c - 1);
        Pair dp = new Pair(r + 2, c);
        Pair rp = new Pair(r + 1, c + 1);

        // 하강 가능한 범위 판단
        if (!inRange(lp) || !inRange(dp) || !inRange(rp))
            return false;
        // 겹치는지 확인
        if (isOverlap(lp) || isOverlap(dp) || isOverlap(rp))
            return false;

        // 하강
        return true;
    }

    private static boolean canLeftDown(int r, int c) {
        Pair lup = new Pair(r - 1, c - 1);
        Pair lp = new Pair(r, c - 2);
        Pair lldp = new Pair(r + 1, c - 2);
        Pair ldp = new Pair(r + 1, c - 1);
        Pair lddp = new Pair(r + 2, c - 1);

        if (!inRange(lup) || !inRange(lp) || !inRange(lldp) || !inRange(ldp) || !inRange(lddp))
            return false;
        if (isOverlap(lup) || isOverlap(lp) || isOverlap(lldp) || isOverlap(ldp) || isOverlap(lddp))
            return false;

        return true;
    }

    private static boolean canRightDown(int r, int c) {
        Pair rup = new Pair(r - 1, c + 1);
        Pair rp = new Pair(r, c + 2);
        Pair rrdp = new Pair(r + 1, c + 2);
        Pair rdp = new Pair(r + 1, c + 1);
        Pair rddp = new Pair(r + 2, c + 1);

        if (!inRange(rup) || !inRange(rp) || !inRange(rrdp) || !inRange(rdp) || !inRange(rddp))
            return false;
        if (isOverlap(rup) || isOverlap(rp) || isOverlap(rrdp) || isOverlap(rdp) || isOverlap(rddp))
            return false;

        return true;
    }

    private static boolean inRange(Pair p) {
        return p.r >= 0 && p.r < R && p.c >= 0 && p.c < C;
    }

    private static boolean isOverlap(Pair p) {
        return grid[p.r][p.c] != 0;
    }

//    private static boolean inGrid(int r, int c) {
//        return r >= 3 && r < R  && c >= 0 && c < C;
//    }

    private static boolean inGrid(int r, int c) {
        return r >= 4 && r < R - 1  && c >= 1 && c < C - 1;
    }

    private static void moveFairy(Golem gol) {
        //현재 골램의 방향
        int dir = gol.dir;
        int max = 0;
        boolean[][] visited = new boolean[R][C];

        int exitRow = gol.row + drs[dir];
        int exitCol = gol.col + dcs[dir];

        exitGrid[exitRow][exitCol] = 1;

        ArrayDeque<Pair> q = new ArrayDeque<>();
        visited[gol.row][gol.col] = true;
        q.add(new Pair(gol.row, gol.col));

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            max = Math.max(cur.r, max);
            if (exitGrid[cur.r][cur.c] == 1) {
                // 다른골렘이랑 커넥션이 있는지 체크
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + drs[i];
                    int nc = cur.c + dcs[i];

                    if (inRange(new Pair(nr, nc)) && grid[nr][nc] != 0 && !visited[nr][nc]) {
                        q.add(new Pair(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + drs[i];
                int nc = cur.c + dcs[i];

                if (inRange(new Pair(nr, nc)) && !visited[nr][nc] && grid[cur.r][cur.c] == grid[nr][nc]) {
                    // 나랑 같은 골렘인 경우
                    visited[nr][nc] = true;
                    q.add(new Pair(nr, nc));
                    // 같은 골렘은 아니지만 현재가 출구라 연결되어있어서 이동 가능한 경우
                }
            }
        }

        answer += max - 2;
    }

    static class Golem {
        int row;
        int col;
        int dir;
        int color;

        Golem(int r, int c, int d) {
            row = r;
            col = c;
            dir = d;
        }

        private void drawMyGolem() {
            grid[this.row][this.col] = color;
            grid[this.row + 1][this.col] = color;
            grid[this.row][this.col - 1] = color;
            grid[this.row][this.col + 1] = color;
            grid[this.row - 1][this.col] = color;
        }

    }

    static class Pair {
        int r;
        int c;

        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

/*
 *    정령들은 숲의 북쪽을 통해서만 숲에 들어올 수 있다.
 * K 명의 정령은  골렘을 타고 숲을 탐색함
 * 골렘은 십자 모양 구조 5칸을 차지
 *
 *  골렘 탑승 방법 (어디서나 가능)
 *  골렘 하차 : 정해진 출구를 통해서만
 *
 *  골렘은 숲의 가장 북쪽에서 시작해서 골렘의 중앙이 Ci열
 *  이 되도록 하는 위치에서 내려오기 시작한다.
 *  초기 골렘의 출구는 Di 방향
 *
 *  골렘은 숲을 탐색하기 위한 우선순위 반복(못 움직일 때까지)
 *  1. 남쪽 한칸 내려가기 (십자모양으로 비어있어야함)
 *
 *  2. 1번이 불가능한 경우 서쪽 방향으로 회전하면서 내려간다.
 *  -> 십자가 시계방향 90도 회전
 *  -> (왼쪽으로 십자 모양이 비어있어 함.)
 *  -> 회전 후 -> 내려가야함
 *  -> 근데 이렇게 내려가면 출구가 반시계방향으로 이동한다.
 *
 *  3. 1,2번 밥벙으로 이동할 수 없는 경우
 *  -> 동쪽 방향으로 회전하면서 내려간다.
 *  -> 2번이랑 같은데 반대방향임.
 *  -> 출구 시계방향 회전함
 *
 *
 *  골렘이 이동할 수 있는 가장 남쪽에 도달해 이동할 수 없는 경우
 *  정령은 골렘 내에서 상하좌우 인전합 칸으로 이동 가능하다.
 *
 *  만약 현재 위치하고 있는 골렘의 출구가 다른 골렘과 인전한 경우
 *  해당 출구를 통해 다른 골렘으로 이동 가능
 *
 *  정령은 갈 수 있는 모든 칸 중 가장 남쪽 칸으로 이동하고 종료
 *  종료 시점이 정령의 최종 위치임.
 *  (암튼 갈 수 있는 가장 남쪽으로 가야함)
 *
 *  **정령의 최종 위치의 행 번호의 합을 구해야 함**
 *
 *  !!! 골렘이 최대한 남쪽으로 이동했는데 몸 일부가 이탈한 경우!!!
 *  해당 골렘을 포함해 숲에 위치한 모든 골렘들은 숲을 빠져나감 (클리어?)
 *  다음 골렘부터 새롭게 숲 탐색 시작 (이 경우 정령은 합산 하지 않음)
 *
 *  -> 숲이 다시 텅 비게 돼도 행의 총합은 누적된다.
 *
 */


/*

(r , c) : 중심점
행을 2추가했어ㅗ


 */