import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K; 
	static int[][] grid;
	static boolean[][] used; // 유관한 포탑인지 판단.
	static boolean[][] visited;
	static int[][] atkGrid;
	static int tCnt = 0;
	static int POWER;
	static PriorityQueue<Tower> wpq = new PriorityQueue<>();
	static PriorityQueue<Tower> spq = new PriorityQueue<>();
	
	// 우, 하 , 좌, 상
	static int[] drs = {0, 1, 0, -1, 1, 1, -1, -1};
	static int[] dcs = {1, 0, -1, 0, 1, -1, -1, 1};
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	grid = new int[N][M];
    	atkGrid = new int[N][M];
    	POWER = N + M;
    	
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < M; j++) {
    			grid[i][j] = Integer.parseInt(st.nextToken());
    			
    			if(grid[i][j] > 0) {
    				int r = i;
    				int c = j;
    				int p = grid[i][j];
    				
    				Tower wt = new Tower(r,c,p,0);
    				Tower pt = new Tower(-r, -c, -p, 0);
    				
    				wpq.add(wt);
    				spq.add(pt);
    			}
    		}
    	}
    
    	for(int i = 1; i <= K; i++) {

    		simulate(i);
    		if(tCnt <= 1) {
    			
    			break;
    		}
    	}
    	
    	System.out.println(spq.peek().p * -1);
    }
    
/*
 *  * [3] 공격
 * 레이저 공격 시도하고 불가능하면 포탄 공격
 * (레이저 공격) 공격력 만큼 피해를 준다.
 * 1. 상하좌우 4개 방향 
 * 2. 부서진 포탑이 있는 위치는 지날 수 없다.
 * 3. 가장자리에서 막힌 방향으로 진행한 경우
 * 	    반대편으로 나오게된다.
 * 
 * 공격 대상 포탑까지의 최단 경로로 공격한다.
 * (해당 경로가 존재하지 않는 경우!)
 * 최단 경로가 여러개인 경우 우/하/좌/상 순서로 한다.
 * 
 * (포탄 공격)
 * 공격력 만큼 피해를 준다.
 * 1. 공격 대상에 포탄을 던진다.
 * 2. 추가적으로 8개의 방향에 있는 포탑도 피해를 입는다.
 *	-> 이 경우는 절반 피해, 공격자는 피해를 받지 않는다.
 *  -> 가장자리로 떨어지는 경우 M -> 0 으로 이동해서 공격
 */
    
    static private void simulate(int time) {
    	
    	visited = new boolean[N][M];
    	Attack(time);
    	removeTower();
    	repairTower();
    	//포탑 우선순위 재정비
    	updateTower();
    }

    static private void Attack(int time) {
    	//가장 약한포탑, 강한 포탑 선정
    	Tower wt = findWeakTower();
    	Tower st = findStrongTower();
    	
    	atkGrid[wt.r][wt.c] = time;
    	grid[wt.r][wt.c] += POWER;
    	used = new boolean[N][M];
    	used[wt.r][wt.c] = true;
    	
    	// 가장 약한 포탑이 가장 강한 포탑을 공격
    	if(canAtkLaser(wt, st)) {
    		//레이저 공격 가능한 경우
    		
    		// 경로에 있는 경우 절반 데미지
    	}
    	else {
        	//포탑으로 공격
    		used = new boolean[N][M];
    		used[wt.r][wt.c] = true;
    		atkBall(wt, st);
    	}
    }
    
    static private void repairTower() {
    	for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(grid[i][j] > 0 && !used[i][j]) {
					grid[i][j]++;
				}
			}
		}
    }
    
    static private void removeTower() {
    	for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(grid[i][j] <= 0) {
					grid[i][j] = 0;
				}
			}
		}
    }
    
    static private void updateTower() {
    	wpq = new PriorityQueue<>();
    	spq = new PriorityQueue<>();
    	
    	tCnt = 0;
    	
    	for(int i = 0; i < N; i++) {    	
    		for(int j = 0; j < M; j++) {    		
    			
    			if(grid[i][j] > 0) {
    				int r = i;
    				int c = j;
    				int p = grid[i][j];
    				int cnt = atkGrid[i][j];
    				Tower wt = new Tower(r,c,p,cnt);
    				Tower pt = new Tower(-r, -c, -p, -cnt);
    				tCnt++;
    				wpq.add(wt);
    				spq.add(pt);
    			}
    		}
    	}
    }
    
    static private void atkBall(Tower wt,Tower st) {
    	//st를 기준으로 8방향 공격
    	int r = st.r;
    	int c = st.c;
    	int power = grid[wt.r][wt.c]; 
    	
    	if(r != wt.r || c != wt.c) {
    		grid[r][c] -= power;	
    	}
    	
    	used[r][c] = true;
    	
    	for(int i = 0; i < 8; i++) {
    		int nr = r + drs[i];
    		int nc = c + dcs[i];
    		
    		nr = getValidValue(nr, 0);
    		nc = getValidValue(nc, 1);
    		
    		if(grid[nr][nc] <= 0 || (nr == wt.r && nc == wt.c)) continue;
    		used[nr][nc] = true;
    		grid[nr][nc] -= power/2;
     	}
    }
    
    static private Tower findWeakTower() {
    	return wpq.peek();
    }
    
    static private Tower findStrongTower() {
    	// 강제로 음수 처리해서 넣었으니까 쓰기 편하게 
    	// 양수로 변환해서 주기
    	Tower t = spq.peek();
    	
    	Tower result = new Tower(t.r * -1, t.c * -1, t.p * -1, t.lastAtk * -1);
    	return result;
    }
    
    
    static private boolean canAtkLaser(Tower wt, Tower st) {
    	// wt -> st 가는 경로 있니?
    	ArrayDeque<Node> q = new ArrayDeque<>();
    	int power = grid[wt.r][wt.c]; // 가장 약한 포탑
    	visited[wt.r][wt.c] = true;
    	used[wt.r][wt.c] = true;
    	
    	Pair[] history = new Pair[1];
    	history[0] = new Pair(wt.r, wt.c);
    	
    	q.add(new Node(history[0], history));
    	
    	while(!q.isEmpty()) {
    		Node cur = q.poll();
    		int r = cur.pair.r;
    		int c = cur.pair.c;
    		
    		for(int i = 0; i < 4; i++) {
        		int nr = r + drs[i];
        		int nc = c + dcs[i];
        		
        		nr = getValidValue(nr, 0);
        		nc = getValidValue(nc, 1);
        		
        		if(canMove(nr, nc)) {
        			visited[nr][nc] = true;
        			if(nr == st.r && nc == st.c) {
        				// 도달 가능한 경우
        				grid[nr][nc] -= power;
        				used[nr][nc] = true;
        				
        				for(int j = 1; j < cur.history.length; j++) {
        					Pair curP = cur.history[j];
        					if(used[curP.r][curP.c] || (curP.r == wt.r && curP.c == wt.c)) {
        						used[curP.r][curP.c] = true;
        						continue;
        					}
        					used[curP.r][curP.c] = true;
        					grid[curP.r][curP.c] -= power/2;
        				}

        				return true;
        			}
        			Node next = new Node(new Pair(nr, nc), cur.history); 
        			q.add(next);
        		}
    		}
    	}
    	
    	
    	
    	return false;
    }
        
    static private boolean canMove(int r, int c) {
    	if(grid[r][c] == 0) return false;
    	if(visited[r][c]) return false;
    	return true;
    }
    
    static private int getValidValue(int num, int cmd) {
    	if(cmd == 0) {
    		//행 변환
    		if(num < 0) return N - 1;
    		if(num >= N) return 0;
    		return num;
    	}
    	// 열 변환
    	if(num < 0) return M - 1;
    	if(num >= M) return 0;
    	return num;
    }
     
    //공격자 선정
    static class Tower implements Comparable<Tower>{
    	 int r;
    	 int c;
    	 int p;
    	 int lastAtk;
    	 
    	 Tower(int r, int c, int p, int la) {
    		 this.r = r;
    		 this.c = c;
    		 this.p = p;
    		 this.lastAtk = la;
    	 }
    	 
    	 // 공격자 선정 우선순위
    	 @Override
    	 public int compareTo(Tower t) {
    		 if(this.p == t.p) {
    			 if(t.lastAtk == this.lastAtk) {
    				 //행과 열합
    				 int thisSum = this.r + this.c;
    				 int anSum = t.r + t.c;
    				 
    				 if(thisSum == anSum) {
    					 return t.c - this.c;
    				 }
    				 return anSum - thisSum; 
    			 }
    			 return t.lastAtk - this.lastAtk;
    		 }
    		 return this.p - t.p;
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
    
    static class Node {
    	Pair pair;
    	Pair[] history;
    	
    	Node(Pair p, Pair[] history) {
    		pair = p;
    		
    		this.history = new Pair[history.length + 1];	
 
    		for(int i =0 ; i < history.length; i++) {
    			Pair pre = history[i];
    			
    			this.history[i] = new Pair(pre.r, pre.c); 
    		}
    		
			this.history[history.length] = p;
    		
    	}
    	
    }
}

/*
 * 경로 이동 구현하기
 * 격자 밖을 나갔을 때 스무스하게 하는거
 */

/*
 * [3] 공격
 * 레이저 공격 시도하고 불가능하면 포탄 공격
 * (레이저 공격) 공격력 만큼 피해를 준다.
 * 1. 상하좌우 4개 방향 
 * 2. 부서진 포탑이 있는 위치는 지날 수 없다.
 * 3. 가장자리에서 막힌 방향으로 진행한 경우
 * 	    반대편으로 나오게된다.
 * 
 * 공격 대상 포탑까지의 최단 경로로 공격한다.
 * (해당 경로가 존재하지 않는 경우!)
 * 최단 경로가 여러개인 경우 우/하/좌/상 순서로 한다.
 * 
 * (포탄 공격)
 * 공격력 만큼 피해를 준다.
 * 1. 공격 대상에 포탄을 던진다.
 * 2. 추가적으로 8개의 방향에 있는 포탑도 피해를 입는다.
 *	-> 이 경우는 절반 피해, 공격자는 피해를 받지 않는다.
 *  -> 가장자리로 떨어지는 경우 M -> 0 으로 이동해서 공격
 * 
 * [4] 포탑 정비
 * 공격이 끝났으면, 부서지지 않은 포탑 중 공격과 무관한 포탑은
 * 공격력이 1씩 올라간다.
 * 공격자도, 피해자도 아닌 포탑
 * 
 * 
 */
