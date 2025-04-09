import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static class Human {
		int r;
		int c;
		int t; // 흐른 시간
		int tr; //목적지 정보
		int tc;
		
		public Human(int r, int c, int t, int tr, int tc) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
			this.tr = tr;
			this.tc = tc;
		} 	
	}
	
	static class Pair {
		int r;
		int c;
		int nr = -1;
		int nc = -1;
		
		Pair(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static class Node {
		Pair p;
		int cnt; 
		
		Node(Pair p, int c) {
			this.p = p;
			cnt = c;
		}
	}
	
	static int N, M;
	static int[][] grid;
	static boolean[][] blocked; //이동 불가 구역 표시
	static Human[] humans;
	// 현재 활동 중인 사람들 관리
	static ArrayDeque<Human> hq = new ArrayDeque<>();
	// 해당 턴이 지나고 지나가지 못 할 곳 임시로 저장
	static ArrayDeque<Pair> bq = new ArrayDeque<>();
	static int fCnt = 0;
	
	// 상 좌 우 하
	static int[] drs = {-1, 0, 0, 1};
	static int[] dcs = {0, -1, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		grid = new int[N][N];
		blocked = new boolean[N][N];
		humans = new Human[M + 1]; //시간이 인덱스로 사용하도록
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			
			// 첫 좌표, 흐른 시간, 목표 편의점
			humans[i] = new Human(-1,-1,i,r,c);
		}
		
		fCnt = 0; //목표 편의점에 도달한 사람 수
		int elapsedTime = 0;
		
		while(fCnt < M) {
			// 사람들 움직이기
			elapsedTime++;
			moveHuman();
			// 편의점 도달시 멈추기
			//사용 불가 체크
			while(!bq.isEmpty()) {
				Pair p = bq.poll();
				
				blocked[p.r][p.c] = true;
			}
			
			// 시간 됐으니까 베이스캠프 찾아주기
			if(elapsedTime <= M) {
				Human nh = humans[elapsedTime];
				int tr = nh.tr;
				int tc = nh.tc;
				Pair camp = findNearCamp(tr, tc);
				hq.add(new Human(camp.r,camp.c,nh.t,tr,tc));
			}
			

		}
		
		System.out.println(elapsedTime);
		
		
		
	}
	
	static private void moveHuman() {
		int size = hq.size();
		
		for(int i = 0; i < size; i++) {
			Human cur = hq.poll();
			
			if(cur.r == -1 && cur.c == -1) {
				// 아직 못 움직이는 경우
				hq.add(cur);
				continue;
			}
			
			//내 위치로부터 최단거리를 구해서 이동하기
			Pair next = findNearStore(cur.r, cur.c, cur.tr, cur.tc);
			
			if(next.r == cur.tr && next.c == cur.tc) {
				//도착한 경우
				fCnt++;
				continue;
			}
			
			hq.add(new Human(next.r, next.c, cur.t + 1, cur.tr, cur.tc));
		}
	}
	

	
	/*
	 * 가장 가까운 베이스 캠프 찾기
	 * 목적 편의점을 기준으로 bfs를 하자
	 * 우선 순위
	 * 행이 작아야함. 
	 * 열이 작아야함.
	 * 상 좌 우 하
	 */
	static private Pair findNearCamp(int r, int c) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		boolean[][] vis = new boolean[N][N];
		
		q.add(new Node(new Pair(r,c),0));
		int minValue = (int) 1e9;
		int maxR = (int) 1e9;
		int maxC = (int) 1e9;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			if(minValue < cur.cnt) {
				//종료하기
				bq.add(new Pair(maxR,maxC));
				return new Pair(maxR, maxC);
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur.p.r + drs[i];
				int nc = cur.p.c + dcs[i];
				
				if(canMove(nr, nc) && !vis[nr][nc]) {
					
					// 베이스 캠프인가요?
					if(grid[nr][nc] == 1) {
						minValue = cur.cnt;
						if(maxR > nr) {
							maxR = nr;
							maxC = nc;
						}
						if(maxR == nr && maxC > nc) {
							maxR = nr;
							maxC = nc;
						}
						continue;
					}
					vis[nr][nc] = true;
					q.add(new Node(new Pair(nr, nc),cur.cnt + 1));
				}
			}
		}
		// 여기에 도달할 일은 없음 
		return null;
	}
	
	static private Pair findNearStore(int r, int c, int tr, int tc) {
		ArrayDeque<Pair> q = new ArrayDeque<>();
		boolean[][] vis = new boolean[N][N];
		
		q.add(new Pair(r,c));
		vis[r][c] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + drs[i];
				int nc = cur.c + dcs[i];
				
				if(canMove(nr, nc) && !vis[nr][nc]) {
					vis[nr][nc] = true;

					if(nr == tr && nc == tc) {
						// 첫 걸음을 어디로 가라고 하지?
						if(cur.nr == -1 && cur.nc == -1) {
							// 편의점 도착한 경우
							bq.add(new Pair(nr, nc));
							return new Pair(nr, nc);
						}
						return new Pair(cur.nr, cur.nc);
					}
					
					Pair next = new Pair(nr, nc);
					
					if(cur.r == r && cur.c == c) {
						//첫 이동인 경우 
						next.nr = nr;
						next.nc = nc;
					}
					else {
						next.nr = cur.nr;
						next.nc = cur.nc;
					}
					q.add(next);
				}
			}
		}
		
		return null;
	}
	
	static private boolean canMove(int r, int c) {
		if(!inRange(r,c)) return false; //범위 밖
		if(blocked[r][c]) return false; // 점유 베이스, 점유 편의점
		return true;
	}
	
	static private boolean inRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	


}

/*
M명의 사람
N 격자 크기 
상 좌 우 하
최단거리 : bfs로 구하는듯? -> 첫 이동만 기억하고 bfs로 가면 될듯?
사람들은 예정된 시간에 정확히 출발한다.

사람들이 목표로하는 편의점은 모두 다르다.

N * N 격자에서 일어난다.

사람들은 3가지 방법으로 움직인다.

1. 본인이 가고 싶은 방향으로 1칸 움직인다.
-> 최단 거리로 상 좌 우 하 의 우선순위를 가진다.
-> 물론 이동 가능한 경우만

2. 편의점에 도착한 경우
-> 일단 멈춘다.
-> 다른 사람들은 해당 편의점이 있는 칸을 지나갈수 없다.
-> 바로 못가는건 아니고 모두 이동한 뒤 칸을 지날 수 없음

3. t <= m 을 만족하는 시간이라면 (베이스 캠프가 존재하는 경우)
-> t번 사람은 자신이 가고 싶은 편의점과 가장 가까이 있는 베이스 캠프에 간다.
-> 가장 가까이? 최단 거리를 의미
-> 최단 베이스가 여러개 인경우 행이 가장 작은 베이스로 간다.
-> 이것도 같으면 열이 작은 곳
-> t번 사람이 베이스 캠프로 이동하는데는 시간이 전혀 소요되지 않는다.
-> 이때 부터 다른 사람들은 해당 베이스 캠프를 지날 수 없음
-> t번 사람이 떠나도 절대 못 지나감
-> 이것도 해당 시간이 지난후 지나갈 수 없어짐
*/
