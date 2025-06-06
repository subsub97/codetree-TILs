import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int L, N, Q;
    static int[][] grid;
    static int[][] kGrid;
    static int[][] newKGrid;
    static int[] drs = {-1, 0, 1, 0};
    static int[] dcs = {0, 1, 0, -1};
    static Knight[] knights;
    static int[] traps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        // 체스판이고 밖도 벽이다.
        grid = new int[L][L];
        kGrid = new int[L][L];
        knights = new Knight[N];
        traps = new int[N];

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < L; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //기사 정보 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            knights[i] = new Knight(r, c, h, w, k, k);

            //kGrid에 표시하기
            for (int j = 0; j < h; j++) {
                for (int l = 0; l < w; l++) {
                    // 기사의 인덱스는 GAP 1이 존재한다.
                    kGrid[j + r][l + c] = i + 1;
                }
            }
        }

        //기사 이동 입력 받기
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int kIdx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            // 이동하기 전 현재 상태 세이브
            int[][] saveKGrid = copyArr(kGrid);
            Knight[] saveKnights = copyKnights(knights);
            traps = new int[N];

            if(moveKnight(kIdx, dir)) {
                // 이동 가능한 경우

                // 움직인 병사 피 깍고 제외하기.
                updateKnightStat(kIdx);

                //TODO 안움직인 기사들이 사라진다.
                kGrid = copyArr(newKGrid);
            }
            else{
                // 불가능한 경우 원복

                kGrid = copyArr(saveKGrid);
                knights = copyKnights(saveKnights);
            }
        }

        // 살아있는 병사들 데미지 총합 계산
        int ans = 0;

        for(int i = 0; i < N; i++) {
            if(!isLive(i)) continue;

            Knight cur = knights[i];
            ans += cur.hp - cur.curHp;
        }

        System.out.println(ans);
    }

    private static void updateKnightStat(int orderIdx) {
        for(int i = 0; i < N; i++) {
            int damage = traps[i];

            Knight kn = knights[i];
            if(orderIdx == i) continue;
            kn.curHp -= damage;

            if (isLive(i)) continue;
            // 죽은 경우

            // grid에서 지우기
            for(int r = 0 ; r < L; r++) {
                for (int c = 0; c < L; c++) {
                    if(newKGrid[r][c] == i + 1) {
                        newKGrid[r][c] = 0;
                    }
                }
            }

        }

    }

    private static int[][] copyArr(int[][] preGrid) {
        int[][] newGrid = new int[preGrid.length][preGrid[0].length];

        for(int i = 0; i < preGrid.length; i++) {
            for(int j = 0 ; j < preGrid[0].length; j++) {
                newGrid[i][j] = preGrid[i][j];
            }
        }

        return newGrid;
    }

    private static Knight[] copyKnights(Knight[] preKnights) {
        Knight[] newKnights = new Knight[preKnights.length];

        for(int i = 0; i < preKnights.length; i++) {
            Knight k = preKnights[i];

            newKnights[i] = new Knight(k.r, k.c, k.h, k.w, k.hp, k.curHp);
        }

        return newKnights;
    }
    /*
    1. 기사 이동
    왕에게 명령 받은 기사는 상하좌우중 하나로 이동
    이동하려는 위치에 다른 기사가 잇따면 그 기사도 연쇄적으로 이동
    연관된 모든 기사를 옮겨야 한다.
    이때 이동하려는 방향에 벽이 있다면 모든 기사는 이동할 수 없다.

    어떻게 구현할까?
    이동하기전 원본판을 기억하고 있는다.
    연관된 기사를 모두 이동한다음 하나라도 벽 위에 있다면 그냥 원본으로 되돌린다.

    이동 요청이 발생하면 해당 칸으로 이동시킨다.
    이동한 칸에 다른 기사가 있는 경우 이동해야하는 방향과 함께 q에 넣는다.
    만약 이동한 칸에 벽이 있다면 취소
     */
    static private boolean moveKnight(int kIdx, int dir) {
        if(!isLive(kIdx)) return false; //요청을 받은 병사가 이미 죽었다면 불가능
        ArrayDeque<Node> q = new ArrayDeque<>();
        boolean[] moved = new boolean[knights.length];

        //요청 받은 병사를 Q에 넣기
        moved[kIdx] = true;
        q.add(new Node(kIdx, dir));

        newKGrid = new int[L][L];

        while(!q.isEmpty()) {
            //q에서 꺼내거 이동 시킨다.
            Node curNode = q.poll();
            Knight ck = knights[curNode.kIdx];
            int curKnightColor = curNode.kIdx + 1;

            int nr = ck.r + drs[dir];
            int nc = ck.c + dcs[dir];
            ck.r = nr;
            ck.c = nc;
            // 이동 가능 여부 판단.
            // 기사의 방패 크기 만큼 체므
            for(int i = 0; i < ck.h; i++) {
                for(int j = 0; j < ck.w; j++) {
                    int nextR = nr + i;
                    int nextC = nc + j;

                    // 이동 불가능한 경우 X
                    if(!canMove(nextR ,nextC)) return false;

                    // 해당 구역에 다른 기사가 있는지 판단.
                    if(kGrid[nextR][nextC] != 0 && kGrid[nextR][nextC] != curKnightColor && !moved[kGrid[nextR][nextC] - 1]) {
                        int anotherKIdx = kGrid[nextR][nextC] - 1;
                        moved[anotherKIdx] = true;
                        q.add(new Node(anotherKIdx, dir));
                    }

                    // 장애물 갯수도 체크
                    if(grid[nextR][nextC] == 1) {
                        traps[curNode.kIdx]++;
                    }

                    newKGrid[nextR][nextC] = curKnightColor;
                }
            }
            // 이때 제외된 기사인 경우 체크 패스?? 일단 보류
        }

        // 안움직였던 애들은 그대로 그려주기
        for(int i = 0; i < N; i++) {
            if (!moved[i]) {
                //안 움직인 경우
                Knight cur = knights[i];

                for(int r = 0; r < cur.h; r++) {
                    for (int c = 0; c < cur.w; c++) {
                        newKGrid[cur.r + r][cur.c + c] = i + 1;
                    }
                }
            }
        }

        return true;
    }

    static private boolean isLive(int idx) {
        return knights[idx].curHp > 0;
    }

    static private boolean canMove(int r, int c) {
        if(!inRange(r,c)) return false; // 장외 인겨 (벽)
        if(grid[r][c] == 2) return false; // 벽인 경우
        return true;
    }

    static private boolean inRange(int r, int c) {
        return r >= 0 && r < L && c >= 0 && c < L;
    }

    static class Knight {
        int r;
        int c;
        int h;
        int w;
        int hp;
        int curHp; // 현재 남은 체력 관리

        Knight(int r, int c, int h, int w, int hp, int curHp) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.hp = hp;
            this.curHp = curHp;
        }
    }

    static class Node {
        // 어느 기사가 어디로 이동하는지 정보를 관리한다.
        int kIdx; // 기사 인덱스
        int dir; // 이동 방향 관리

        Node(int k, int d) {
            kIdx = k;
            dir = d;
        }
    }
}



/*
1. 기사 이동
왕에게 명령 받은 기사는 상하좌우중 하나로 이동
이동하려는 위치에 다른 기사가 잇따면 그 기사도 연쇄적으로 이동
연관된 모든 기사를 옮겨야 한다.
이때 이동하려는 방향에 벽이 있다면 모든 기사는 이동할 수 없다.

어떻게 구현할까?
이동하기전 원본판을 기억하고 있는다.
연관된 기사를 모두 이동한다음 하나라도 벽 위에 있다면 그냥 원본으로 되돌린다.

이동 요청이 발생하면 해당 칸으로 이동시킨다.
이동한 칸에 다른 기사가 있는 경우 이동해야하는 방향과 함께 q에 넣는다.
만약 이동한 칸에 벽이 있다면 취소


2. 대결 대미지
W x H 직사각형 내에 놓여 있는 함정의 수만큼 피해를 입는다.

현재 체력 이상의 대미지를 받는 경우 기사는 체스판에서 사라진다.



상 우 하 좌 0 , 1, 2, 3
Q 개의 명령이 진행된 이후, 생존한 기사들이 총 받은 대미지의 합을 출력
 */