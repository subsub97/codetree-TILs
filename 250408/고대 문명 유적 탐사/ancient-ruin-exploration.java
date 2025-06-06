import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int K, M;
    static int R = 5 ,C = 5;
    static int GR = 3, GC = 3;
    static int[] drs = {1, -1, 0, 0};
    static int[] dcs = {0, 0, 1, -1};
    static int[][] grid = new int[R][C];
    static int[][] mTemp = new int[R][C];
    static int ans = 0;
    static ArrayDeque<Integer> q = new ArrayDeque<>();
    static ArrayDeque<Integer> originQ = new ArrayDeque<>();
    static ArrayDeque<Integer> maxQ = new ArrayDeque<>();
    static ArrayDeque<Node> getQ = new ArrayDeque<>();
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            q.add(num);
            originQ.add(num);
        }

        for (int i = 0; i < K; i++) {
            simulate();

            if (ans > 0) {
                sb.append(ans + " ");
                ans = 0;
            }

            grid = copyGrid(mTemp);
            originQ = new ArrayDeque<>();
            int size = q.size();
            for(int j = 0; j < size; j++) {
                int num = q.poll();
                originQ.add(num);
                q.add(num);
            }
        }

        System.out.println(sb);
    }
    /*
    시나리오
    -> 여기 1번 시작하기 전 초기 배열을 기억해야함
    1. N도 돌린다.
    2. 유물획득한다.
    3. 벽면 유물 채운다.
    4. 연쇄 유물 획득한다.

    이걸 한 사이클로 while 문을 돌려서
    max 값 갱신하면서 바꾸기
     */


    // 우선순위 별로 for문 만들어서 처리하기
    // 1. 최소 각도
    // 2  열이 가장 작음
    // 3. 행이 가장 작음

    private static int simulate() {
        int max = 0;
        // 각도 선택 90 -> 180 -> 270 -> 360
        int[][] originGrid = copyGrid(grid);

        for(int i = 1; i < 4; i++) {
            // 열이 작은거 먼저 선택
            for (int c = 1; c <= 3; c++) {
                //행이 작은거 먼저 선택
                for (int r = 1; r <= 3; r++) {
                    int sum = 0;
                    for (int j = 0; j < i; j++) {
                        // 돌리고
                        rotate(r,c);
                    }
                        int curSum = 0;

                        getQ = new ArrayDeque<>();
                        // 유물캐고
                        curSum += getGold();

                        // 채우고
                        if(curSum >= 3) {
                            putBlock();
                            sum += curSum;
                        }

                        if(max < sum) {
                            max = sum;
                            mTemp = copyGrid(grid);
                            maxQ = new ArrayDeque<>();

                            while(!q.isEmpty()) {
                                maxQ.add(q.poll());
                            }
                        }

                    // 초기화
                    grid = copyGrid(originGrid);
                    q = copyQueue();
                }

            }
        }

        grid = copyGrid(mTemp);
        // 연쇄 파편 획득
        int curSum = 3;
        q = maxQ;
        // 유물캐고
        while(curSum >= 3) {
            curSum = 0;
            curSum += getGold();
            if(curSum >= 3 ) {
                putBlock();
                max += curSum;
                mTemp = copyGrid(grid);
            }
        }

        ans = max;
        return max;
    }

    private static void putBlock() {
        // getQ에서 꺼내고 부시면서 pq에 넣기
        boolean[][] visited = new boolean[R][C];
        ArrayDeque<Node> temp = new ArrayDeque<>();

        while(!getQ.isEmpty()) {
            Node cur = getQ.poll();
            visited[cur.r][cur.c] = true;
            pq.add(cur);

            for(int i = 0; i < 4; i++ ){
                int nr = cur.r + drs[i];
                int nc = cur.c + dcs[i];

                if(canGet(cur.r, cur.c ,nr,nc) && !visited[nr][nc]) {
                    Node next = new Node(nr,nc);
                    visited[nr][nc] = true;
                    getQ.add(next);
                }
            }
            temp.add(new Node(cur.r, cur.c));
        }

        while(!temp.isEmpty()) {
            Node cur = temp.poll();
            grid[cur.r][cur.c] = 0;
        }

        // 여기까지는 부셔진 파편을 찾았음
        while(!pq.isEmpty()) {
            Node cur =  pq.poll();

            int newBlock = q.poll();

            grid[cur.r][cur.c] = newBlock;
        }
    }

    private static int getGold() {
        ArrayDeque<Node> goldQ = new ArrayDeque<>();

        boolean[][] visited = new boolean[R][C];
        int getCount = 0;

        for(int i = 0;  i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(visited[i][j]) continue;

                visited[i][j] = true;
                goldQ.add(new Node(i, j));
                int count = 1;

                while(!goldQ.isEmpty()) {
                    Node cur = goldQ.poll();

                    for(int dir = 0; dir < 4; dir++) {
                        int nr = cur.r + drs[dir];
                        int nc = cur.c + dcs[dir];

                        if(canGet(cur.r, cur.c, nr,nc) && !visited[nr][nc]) {
                            visited[nr][nc] = true;
                            goldQ.add(new Node(nr, nc));
                            count++;
                        }
                    }
                }

                if(count >= 3){
                    // 캐내기
                    getCount += count;
                    getQ.add(new Node(i, j));
                }
            }
        }

        return getCount;
    }

    private static ArrayDeque<Integer> copyQueue() {
        ArrayDeque<Integer> temp = new ArrayDeque<>();

        int size = originQ.size();

        for (int i = 0; i < size; i++) {
            int a = originQ.poll();
            temp.add(a);
            originQ.add(a);
        }

        return temp;
    }

    private static boolean canGet(int pr, int pc ,int r, int c){
        if(!inRange(r,c)) return false;
        if(grid[pr][pc] != grid[r][c]) return false;
        return true;
    }

    private static boolean inRange(int r, int c){
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    // 3 * 3 회전 구현하기
    private static void rotate(int r, int c) {
        //회전은 (1,1) 부터 (3,3) 사이에서만 가능하다.
        // 중심점을 파라미터로 받는다.

        int[][] newGrid = new int[3][3];

        newGrid[0][2] = grid[r-1][c-1];
        newGrid[1][2] = grid[r-1][c];
        newGrid[2][2] = grid[r-1][c+1];
        newGrid[2][1] = grid[r][c+1];
        newGrid[2][0] = grid[r+1][c+1];
        newGrid[1][0] = grid[r+1][c];
        newGrid[0][0] = grid[r+1][c-1];
        newGrid[0][1] = grid[r][c-1];
        newGrid[1][1] = grid[r][c];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[r + i - 1][c + j - 1] = newGrid[i][j];
            }
        }

    }

    private static int[][] copyGrid(int[][] grid) {
        int[][] copyGrid = new int[grid.length][grid[0].length];

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                copyGrid[i][j] = grid[i][j];
            }
        }

        return copyGrid;
    }

    private static boolean canRotate(int r, int c) {
        return r >= 1 && r < 4 && c >= 1 && c < 4;
    }

static class Node implements Comparable<Node>{
        int r;
        int c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Node n) {
            if(this.c == n.c) return n.r - this.r;
            return this.c - n.c;
        }
    }
}

//회전
// 유물 1차 획득 가치를 최대화
// 중복인 경우 회전한 각도가 가장 작은 방법 선택
// 중복인 경우 회전 중심 좌표가 열이 가장 작은 구간을
// 열이 같다면 행이 가장 작은 경우

//유물 획득
// 조각들이 3개 이상 연결된 경우
// 획득하면 격자판은 빈칸이 된다.

// 유물 채우기 -> 이거 pq 사용해서 사라지는거 넣어둘래
// 빈칸이된칸을 채울 수 있다.
// 벽지를 보고
// 생성 순서는
// 1. 열번호가 가장 작은 것 부터
// 2. 행 번호가 큰 순으로

// 유물 연쇄 획득
// 새로운 유물 조각이 생겨난 이후에도 조각들이 3개 이상 연결될 수 있다.
// 그럼 유물획득과 동일하게 3개이상 존재하면 사라진다.
// 없을때까지 반복
