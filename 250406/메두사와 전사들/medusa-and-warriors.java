import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] drs = {-1, 1, 0, 0};
    static int[] dcs = {0, 0, -1, 1};
    static int[][] grid;
    static Pair[] paths;
    static ArrayDeque<Pair> wq = new ArrayDeque<>(); //전사들 관리
    static int[][] counts; // 각 그리드에 전사 수 체크
    static int[][] stoneMemo; // 1이면 돌로 안변함

    static int sr, sc, er, ec;
    static int curR, curC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        counts = new int[N][N];
        stoneMemo = new int[N][N];


        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());
        er = Integer.parseInt(st.nextToken());
        ec = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            counts[r][c]++;
            wq.add(new Pair(r, c));
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        if(canArrivePark()) {
            // 갈 수 있는 경우니까 움직이면서 해야함
            //TODO :: curR, curC 설정하기
            for(int i = 1; i < paths.length; i++) {

                int moveCnt = 0;
                int stoneCnt = 0;
                int attackCnt = 0;

                // 움직임
                sr = paths[i].r;
                sc = paths[i].c;
                curR = sr;
                curC = sc;
                int[][] curStoneMemo = new int[N][N];

                // 메두사한테 공격받아 죽음
                counts[sr][sc] = 0;

                int max = 0;
                int dir = 0;
                for(int j = 0; j < 4; j++) {

                    stoneMemo = new int[N][N];

                    if(j == 0){
                        int cur = Math.max(max, checkRange(sr - 1, sc, sc - 1, j, 3));
                        if(cur > max) {
                            dir = 0;
                            max = cur;
                            curStoneMemo = copyMemo(stoneMemo);
                        }
                    }
                    else if(j == 1) {
                        int cur = Math.max(max, checkRange(sr + 1, sc, sc - 1, j, 3));
                        if(cur > max) {
                            dir = 1;
                            max = cur;
                            curStoneMemo = copyMemo(stoneMemo);
                        }

                    }
                    else if(j == 2) {
                        int cur = Math.max(max, checkRange(sr, sc - 1, sr - 1, j, 3));
                        if(cur > max) {
                            dir = 2;
                            max = cur;
                            curStoneMemo = copyMemo(stoneMemo);
                        }
                    }
                    else{
                        int cur = Math.max(max, checkRange(sr, sc + 1, sr - 1, j, 3));
                        if(cur > max) {
                            dir = 3;
                            max = cur;
                            curStoneMemo = copyMemo(stoneMemo);
                        }
                    }
                }

                stoneCnt = max;
                // 메두사 공격 방향도 찾았고, 돌 된 녀석들오 찾았음
                stoneMemo = copyMemo(curStoneMemo);

                // 전사 이동 및 공격
                int[] result = warriorMoveAndAttack();

                // 전사 수 그리드 업데이트
                updateWarriorGrid();

                moveCnt = result[0];
                attackCnt = result[1];
                sb.append(moveCnt).append(" ").append(stoneCnt).append(" ").append(attackCnt).append("\n");
            }
            sb.append("0");
            System.out.println(sb);
        }
        else{
            System.out.println(-1);
        }
    }

    private static void updateWarriorGrid() {
        counts = new int[N][N];
        int size = wq.size();

        for(int i = 0; i < size; i++) {
            Pair p = wq.poll();

            counts[p.r][p.c]++;  
            wq.add(p);
        }

    }

    //메두사의 집부터 공원까지 가는 경로 없으면 -1 출력
    private static boolean canArrivePark() {
        boolean[][] visited = new boolean[N][N];
        ArrayDeque<Node> q = new ArrayDeque<>();

        visited[sr][sc] = true;
        Pair[] p = new Pair[1];
        p[0] = new Pair(sr, sc);
        q.add(new Node(1, p, new Pair(sr, sc)));

        while(!q.isEmpty()) {
            Node curNode = q.poll();
            Pair current = curNode.history[curNode.cnt - 1];

            for (int i = 0; i < 4; i++) {
                int nr = current.r + drs[i];
                int nc = current.c + dcs[i];

                if (canGo(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;

                    if(nr == er && nc == ec) {
                        //목적지인 경우
                        //경로 카피하기
                        q.clear();
                        paths = curNode.history;
                        return true;
                    }

                    q.add(new Node(curNode.cnt + 1, curNode.history, new Pair(nr, nc)));
                }
            }
        }
        return false;
    }

    private static boolean canGo(int r, int c) {
        if(!inRange(r,c)) return false;
        if(grid[r][c] == 1) return false;
        return true;
    }

    private static boolean inRange(int r ,int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static class Pair {
        int r;
        int c;

        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    //메두사 공격범위 체크하기
    private static int checkRange(int r, int c, int start, int dir, int cnt) {
        if(!inRange(r,c)) return 0;
        int origin = start;
        if(start < 0) start = 0;
        int sum = 0;

        if(dir == 0) {
            // 아래서 위로 쏘는 방향임 무조건
            for(int i = start; i < origin + cnt; i++) {
                if(i == N) break;
                if(counts[r][i] > 0 && stoneMemo[r][i] == 0) {
                    // c를 기준으로 전파
                    if(curC > i) { // 메두사 왼쪽인 경우
                        checkSafeArea(r - 1, c, i - 1, dir, 0, 2);
                    } else if (curC == i) { // 동일 선상인 경우
                        checkSafeArea(r - 1, c, i, dir, 1, 1);
                    }
                    else{ // 메두사 오른쪽인 경우
                        checkSafeArea(r - 1, c, i, dir, 2, 2);
                    }
                    sum += counts[r][i];
                }
                if(stoneMemo[r][i] != 1) stoneMemo[r][i] = 2;
            }
            return sum + checkRange(r - 1, c, origin - 1, dir, cnt + 2);
        }

        else if(dir == 1) {
            for(int i = start; i < origin + cnt; i++) {
                if(i == N) break;
                if(counts[r][i] > 0 && stoneMemo[r][i] == 0) {
                    if(curC > i) {
                        checkSafeArea(r + 1, c, i - 1, dir, 0, 2);
                    } else if (curC == i) {
                        checkSafeArea(r + 1, c, i, dir, 1, 1);
                    }
                    else{
                        checkSafeArea(r + 1, c, i, dir, 2, 2);
                    }
                    sum += counts[r][i];
                }
                if(stoneMemo[r][i] != 1) stoneMemo[r][i] = 2;

            }
            return sum + checkRange(r + 1, c, origin - 1, dir, cnt + 2);
        }

        else if(dir == 2){
            for(int i = start; i < origin + cnt; i++) {
                if(i == N) break;
                if(counts[i][c] > 0 && stoneMemo[i][c] == 0) {
                    // c를 기준으로 전파
                    if(curR > i) { // 메두사보다 위에 있는 경우
                        checkSafeArea(r, c - 1, i - 1, dir, 0, 2);
                    } else if (curR == i) { // 동일 선상인 경우
                        checkSafeArea(r, c - 1, i, dir, 1, 1);
                    }
                    else{ // 메두사보다 아래 있는 경우
                        checkSafeArea(r, c- 1, i, dir, 2, 2);
                    }
                    sum += counts[i][c];
                }
                    if(stoneMemo[i][c] != 1) {
                        stoneMemo[i][c] = 2;
                    }
            }
            return sum + checkRange(r, c - 1, origin - 1, dir, cnt + 2);
        }
        else {
            for(int i = start; i < origin + cnt; i++) {
                if(i == N) break;
                if(counts[i][c] > 0 && stoneMemo[i][c] == 0) {
                    // c를 기준으로 전파
                    if(curR > i) { // 메두사보다 위에 있는 경우
                        checkSafeArea(r, c + 1, i - 1, dir, 0, 2);
                    } else if (curR == i) { // 동일 선상인 경우
                        checkSafeArea(r, c + 1, i, dir, 1, 1);
                    }
                    else{ // 메두사보다 아래 있는 경우
                        checkSafeArea(r, c + 1, i, dir, 2, 2);
                    }
                    sum += counts[i][c];
                }
                if(stoneMemo[i][c] != 1) stoneMemo[i][c] = 2;
            }
            return sum + checkRange(r, c + 1, origin - 1, dir, cnt + 2);
        }
    }

    private static void checkSafeArea(int r, int c, int start, int dir,int cmd , int cnt) {
        if(!inRange(r,c)) return;
        int origin = start;
        // cmd가 1인 경우 동일 선상
        if(start < 0) start = 0;

        if(dir == 0) {
            for(int i = start; i < origin + cnt; i++) {
                if(i == N) break;
                stoneMemo[r][i] = 1;
            }
            if(cmd == 0) {
                checkSafeArea(r - 1, c, origin - 1, dir, cmd, cnt + 1);
            }
            else if(cmd == 1){
                checkSafeArea(r - 1, c, origin, dir, cmd, cnt);
            }
            else{
                checkSafeArea(r - 1, c, origin, dir, cmd, cnt + 1);
            }
        }

        else if(dir == 1) {
            for(int i = start; i < origin + cnt; i++) {
                if(i == N) break;
                stoneMemo[r][i] = 1;
            }
            if(cmd == 0) {
                checkSafeArea(r + 1, c, origin - 1, dir, cmd, cnt + 1);
            }
            else if(cmd == 1){
                checkSafeArea(r + 1, c, origin, dir, cmd, cnt );
            }
            else{
                checkSafeArea(r + 1, c, origin, dir, cmd, cnt + 1);
            }
        }

        else if(dir == 2){
            for(int i = start; i < origin + cnt; i++) {
                if(i == N) break;
                stoneMemo[i][c] = 1;
            }
            if(cmd == 0) {
                checkSafeArea(r, c - 1, origin - 1, dir, cmd, cnt + 1);
            }
            else if (cmd == 1) {
                checkSafeArea(r, c - 1, origin, dir, cmd, cnt );
            }
            else{
                checkSafeArea(r, c - 1, origin, dir, cmd, cnt + 1);
            }

        }
        else {
            for(int i = start; i < origin + cnt; i++) {
                if(i == N) break;
                stoneMemo[i][c] = 1;
            }
            if(cmd == 0) {
                checkSafeArea(r, c + 1, origin -1, dir, cmd, cnt + 1);
            }
            else if(cmd == 1) {
                checkSafeArea(r, c + 1, origin, dir, cmd, cnt);
            }
            else {
                checkSafeArea(r, c + 1, origin, dir, cmd, cnt + 1);
            }
        }
    }

    private static int[][] copyMemo(int[][] stoneMemo) {
        int[][] copyMemo = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                copyMemo[i][j] = stoneMemo[i][j];
            }
        }

        return copyMemo;
    }

    // 전사 이동 및 공격
    private static int[] warriorMoveAndAttack() {
        // 메두사한테 공격 당한전사 스킵
        // 돌 된 병사 스킵
        int size = wq.size();
        int moveCnt = 0;
        int attackCnt = 0;
        for(int i = 0; i < size; i++) {
            Pair current = wq.poll();
            boolean canAttack = false;

            if(current.r == curR && current.c == curC) {
                // 공격당한 전사
                continue;
            }

            if(isSkip(current)){
                // 돌된 경우
                wq.add(current);
                continue;
            }

            //움직일 수 있는 경우

            // 거리를 좁힐 수 있는 방향으로 이동하기
            for(int j = 1; j <= 2; j++) {
                Pair next = getNextSpot(current, j);
                if(next.r != current.r || next.c != current.c) moveCnt++;
                current.r = next.r;
                current.c = next.c;
                if(next.r == -1 && next.c == -1) {
                    //메두사를 공격한 경우
                    canAttack = true;
                    break;
                }
            }

            if(canAttack) {
                attackCnt++;
                continue;
            }

            wq.add(new Pair(current.r, current.c));
        }

        return new int[]{moveCnt, attackCnt};
    }

    private static boolean isSkip(Pair p) {
        if(stoneMemo[p.r][p.c] == 2) return true;
        return false;
    }

    private static Pair getNextSpot(Pair cur, int count) {
        if(count == 1) {
            // 첫 이동인 경우
            for(int i = 0; i < 4; i++) {
                int nr = cur.r + drs[i];
                int nc = cur.c + dcs[i];

                if (inRange(nr, nc) && canLessDistance(cur.r, cur.c, nr, nc) && stoneMemo[nr][nc] != 2) {
                    if (nr == curR && nc == curC) {
                        if(counts[cur.r][cur.c] < 0)counts[cur.r][cur.c] = 0;
                        return new Pair(-1, -1);
                    }
                    return new Pair(nr, nc);
                }
            }
        }
        else{
            // 두번째 이동인 경우
            // idx == 2부터 4번
            for(int i = 0; i < 4; i++) {
                int idx = (i + 2) % 4;
                int nr = cur.r + drs[idx];
                int nc = cur.c + dcs[idx];

                if (inRange(nr, nc) && canLessDistance(cur.r, cur.c, nr, nc) && stoneMemo[nr][nc] != 2) {
                    if (nr == curR && nc == curC) {
                        return new Pair(-1, -1);
                    }
                    return new Pair(nr, nc);
                }
            }
        }
        return new Pair(cur.r, cur.c);
    }

    private static boolean canLessDistance(int preR, int preC, int nR, int nC) {
        int oldDist = Math.abs(preR - curR) + Math.abs(preC - curC);
        int newDist = Math.abs(nR - curR) + Math.abs(nC - curC);
        return newDist < oldDist;
    }



    static class Node {
        int cnt = 0;
        Pair[] history;

        Node(int cnt, Pair[] preHistory, Pair next) {
            history = new Pair[cnt];
            this.cnt = cnt;

            for(int i = 0; i < preHistory.length; i++) {
                history[i] = new Pair(preHistory[i].r, preHistory[i].c);
            }
            history[cnt - 1] = new Pair(next.r, next.c);
        }
    }

}

/*
    1. 메두사는 오직 최단 경로로 이동한다.
    2. 전사들은 도로와 비도로를 구분하지 않고 움직인다.
    3. 전사들은 메두사를 향해 최단 경로로 이동한다.
    4. 메두사의 집에 전사들이 초기부터 위치하는 경우 없음

    도로는 0 , 비도로는 1

    메두사 스킬
    1. 이동
    -> 도로를 따라 한 칸 이동, 공원까지 최단 경로 이용
    -> 메두사가 이동한 칸에 전사가 있을 경우 전사는 공격받고 사라짐
    -> 만약 집부터 공원까지 여러 최단경로가 존재하는 경우
    -> 상, 하 ,좌, 우 우선순위를 따름
    -> 메두사의 집으로부터 공원까지 도달하는 경로가 없을 수 도 있음!!!!

    2. 메두사의 시선
    상하좌우 하나의 방향을 선택해 바라본다.
    바라보는 방향으로 90도 시야각을 가진다.
    시야각 범위 안에 있는 전사들을 볼 수 있다.

    3 -> 5 -> 7 이렇게 커진다.
    시야각 안에 들어와있지만 다른 전사에 가려진 전사의 경우 메두사에게 보이지 않는다.

    가려지는 범위
    상하좌우 대각선 8방향을 나누었을 때 메두사로부터 8방향중 한방향에
    위치한 경우 그 전사가 동일한 방향으로 바라본 범위에 포함된 모든 칸은
    메두세에게 보이지 않는다. (*여기가 어렵네..?)

    아무튼 메두사가 본 전사들은 모두 돌로 변해 현재 턴에는 움직일 수 없음
    턴 종료시 풀려난다.(기능 구현)
    -> 만약 두 명 이상의 전사들이 같은 칸에 있다면 해당 칸의 전사 모두 돌로 변함

    메두사는 상하좌우 중 전사를 가장 많이 바라볼 수 있는 방향으로 바라본다.
    -> 동일한 경우 상하좌우의 우선순위로 방향 결정


    [3] 전사들 이동
    돌로 변하지 않은 전사들은 메두사를 향해 최대 두칸 이동 가능
    -> 움직일 때 같은 칸 공유 가능
    1. 첫 번째 이동
    메두사와의 거리를 줄일 수 있는 방향으로 한 칸 이동
    동일한 경우 (상하좌우)
    -> 격자 밖으로 나갈 수 없음
    -> 메두사의 시야에 들어오는 곳으로 이동 X

    두 번째 이동
    메두사와의 거리를 줄일 수 있는 방향으로 한 칸 이동
    우선순위 (좌우상하) !!! 2개 밀고 4번해야겠다.
    -> 격자 밖으로 나갈 수 없음
    -> 메두사의 시야에 들어오는 곳으로 이동 X


    [4] 전사의 공격
    메두사와 같은 칸에 도달한 전사는 메두사를 공격
    그러나 이기지 못하고 사라짐;;

    최단 경로를 계산할 때는 맨해튼 거리를 기준으로

    위 4가지 단계가 반복되어 메두사가 공원에 도달할 때까지
    1. 매턴 마다 해당 턴에서 모든 전사가 이동한 거리의 합
    2. 메두사로 인해 돌이 된 전사의 수
    3. 메두사를 공격한 전사의 수
    **메두사가 공원에 도착하는 턴에는 0 출력하고 종료**




 */