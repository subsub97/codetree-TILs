import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int N, M, F; // 2차원 한 변 길이, 3차원 한변 길이, 이상현상 개수
	static int[][][] arr3d;
	static int[][] arr2d;
	static int sr, sc, er, ec, ed, e3r,e3c;
	static int targetR, targetC;
	static int tr, tc, td;
	static int[] drs = {0, 0, 1, -1};
	static int[] dcs = {1, -1, 0, 0};
	static Node[] Nodes;
	static int[][] paths = new int[][] {{3,2}, {2,3}, {0,1},{1,0}}; 
	static boolean[][][] visited;
 	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		
		arr3d = new int[N][N][5]; // 0 : 동 , 1 : 서 , 2 : 남 , 3: 북, 4 : 윗면
		visited = new boolean[N][N][5];
		arr2d = new int[N][N];
		Nodes = new Node[F];
		
		int cnt3d = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr2d[i][j] = Integer.parseInt(st.nextToken());
				if(arr2d[i][j] == 4) {
					targetR = i;
					targetC = j;
				}
				if(arr2d[i][j] == 3) {
					cnt3d++;
					if(cnt3d == 1) {
						sr = i;
						sc = j;
					}
					if(cnt3d == M * M){
						er = i;
						ec = j;
					}
				}
			}
		}
		// 3차원 평면 정보 입력 받기
		for(int d = 0; d < 5; d++) {
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					arr3d[i][j][d] = Integer.parseInt(st.nextToken());
					if(arr3d[i][j][d] == 2) {
						tr = i;
						tc = j;
						td = d;
					}
				}
			}
		}
		
		// F 이상현상 입력 받기 (이상현상 규칙 고려하기)
		// 여기도 기준이 동서남북이다.
		for(int i = 0; i < F; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			Nodes[i] = new Node(r, c, d, v);
		} 
		
		// 3차원과 연결된 2차원 입/출구 찾기
		Pair exitPair = findEntrance();
		// 2차원 위치는 찾았으니까 3차원 -> 2차원으로 연결된 곳 찾기
		Pair exitPair3d = findEntrance3d(exitPair);
		// 3차원 공간에서 exidPair3d로 도달하는 최단 거리 구하기
 		int lenDistance = findShortestPathIn3d(exitPair3d);
 		
 		// 2차원 시간왜곡 체크
 		checkTimeBreaker();
 		
 		// 2차원부터 bfs();
 		System.out.println(getShortest(exitPair,lenDistance));
		
	}
	
	private static int getShortest(Pair start, int slen) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		
		// d는 cnt를 의미
		if(arr2d[start.r][start.c] != 0 && arr2d[start.r][start.c] <= slen + 1) {
			return -1;
		}
		
		q.add(new Node(start.r, start.c, slen + 1, 0));
		boolean[][] visited = new boolean[N][N];
		visited[start.r][start.c] = true;
		while(!q.isEmpty()) {
			
			Node cur = q.poll();
			
			
			for(int i = 0; i < 4; i++) {
				int nr = cur.r + drs[i];
				int nc = cur.c + dcs[i];
				
				if(nr == targetR && nc == targetC) {
					return cur.d + 1;
				}
				
				if(!inRange(nr,nc) || arr2d[nr][nc] == 1 ||arr2d[nr][nc] == -1 || (arr2d[nr][nc] != 0 && arr2d[nr][nc] <= cur.d + 1) || visited[nr][nc]) continue;

				visited[nr][nc] = true;
				q.add(new Node(nr, nc, cur.d + 1, 0));				
			}
		}
		
		return -1;
	}
	
	private static void checkTimeBreaker() {
		ArrayDeque<Node> q = new ArrayDeque<>();
		
		for(int i = 0; i < F; i++) {
			Node no = Nodes[i];
			if(arr2d[no.r][no.c] == 1) continue;
			arr2d[no.r][no.c] = -1;
			int nr = no.r + drs[no.d];
			int nc = no.c + dcs[no.d];
			
			if(!inRange(nr, nc) || arr2d[nr][nc] == 1) {
				// 확산 불가능한 경우
				continue;
			}
			
			arr2d[nr][nc] = no.v;
			
			q.add(new Node(nr, nc, no.d, no.v));
		}
		
		// 방향이랑 배수 체크해서 그리기
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			int nr = cur.r + drs[cur.d];
			int nc = cur.c + dcs[cur.d];
			
			if(!inRange(nr, nc) || arr2d[nr][nc] == 1) {
				// 확산 불가능한 경우
				continue;
			}
			
			arr2d[nr][nc] = arr2d[cur.r][cur.c] + cur.v;
			q.add(new Node(nr,nc,cur.d, cur.v));
		}
	}
	
	//3d 와 2d 연결된 출입구 찾기
	private static Pair findEntrance() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
				// 0인 경우 출/입구 가능성 있음
				if(arr2d[i][j] == 0 && isNear3dSpace(i, j)) {
					return new Pair(i , j);
				}
			}
		}
		// null인 경우는 존재하지 않음
		return null;
	}
	
	/*
	 * 무조건 3차원은 M-1 행과 2차원 좌표가 연결된다.
	 * 3차원을 -> 2차원으로 변환하기
	 * 2차원 좌표는 아는데 3차원은? 
	 * 
	 */
	
 	private static Pair findEntrance3d(Pair p) {
 		// p : 는 2차원 입구 정보
 		// => 3차원 평면과 비교를 위한 조정 필요
 		int r2d = p.r - sr;
 		int c2d = p.c - sc;

 		// 우, 좌 , 하, 상 : 어디 인접한 곳이니?
 		if(p.c > ec) {
 			// 우측인 경우 0번 평면
 			return new Pair(M - 1, M - 1 - r2d, 0);
 		}
 		else if(p.c < sc) {
 			// 좌측인 경우
 			return new Pair(M - 1, r2d, 1);
 		}
 		else if(p.r > sr) {
 			// 하단인 경우 
 			return new Pair(M - 1, c2d,2);
 		}
 		else {
 			// 상단인 경우
 			return new Pair(M - 1, M - 1 - c2d, 3);
 		}
 	}

	private static boolean isNear3dSpace(int r, int c) {
		for(int i = 0; i < 4; i++) {
			int nr = r + drs[i];
			int nc = c + dcs[i];
			
			if(inRange(nr,nc) && arr2d[nr][nc] == 3) {
				return true; 
			}
		}
		
		return false;
	}
	
	private static boolean inRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	
	private static boolean inRangeM(int r, int c) {
		return r >= 0 && r < M && c >= 0 && c < M;
	}
	
	private static int findShortestPathIn3d(Pair exit) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		
		// r,c d는 차원 사용하고 v를 cnt;
		q.add(new Node(tr, tc, td, 0));
		visited[tr][tc][td] = true;
		
		while(!q.isEmpty()) {
			Node curNode = q.poll();
			
			/*
			 * 3차원 이동이기 때문에 평면을 벗어 나는 경우 
			 * 규칙에 맞게 처리하기.
			 */
			for(int i = 0; i < 4; i++) {
				int nr = curNode.r + drs[i];
				int nc = curNode.c + dcs[i];
				int nd = curNode.d;
				Pair nextP = new Pair(nr, nc, curNode.d);

				// 평면이 바뀌는 경우
				if(!inRangeM(nr,nc)) {
					nextP = getNextGrid(nextP);
					
					if(nextP == null) continue;
				}
				
				if(nextP.r == exit.r && nextP.c == exit.c && nextP.dimension == exit.dimension) {
					// 도착한 경우
					q.clear();
					return curNode.v + 1;
				}
				
				if(visited[nextP.r][nextP.c][nextP.dimension] || arr3d[nextP.r][nextP.c][nextP.dimension] == 1) continue;
				visited[nextP.r][nextP.c][nextP.dimension] = true;
				
				q.add(new Node(nextP.r, nextP.c, nextP.dimension, curNode.v + 1));
			}
		}
		
		return -1; 
	}
	
	// 어느 평면에서 -> 어느 방향으로 이동함?
	private static Pair getNextGrid(Pair p) {
		int dir = calcDir(p);
		if(dir == -1) {
			return p;
		}
		
		if(p.dimension == 4) { 		// 가장 윗면인 경우
			//여기서는 무조건 0 행으로 이동하는건 고정
			if(dir == 0) {
				//동쪽으로 가는 경우 (r 값이 c로)
				return new Pair(0, M - 1 - p.r,0);
			}
			else if(dir == 1) {
				// 서쪽 (행 값이 열로)
				return new Pair(0, p.r, 1);
			}
			else if(dir == 2) {
				// 남쪽
				return new Pair(0, p.c, 2);
			}
			else {
				// 북쪽
				return new Pair(0, M - 1-p.c ,3);
			}
		}
		else {
			// 윗면이 아닌 경우 공통
			if(dir == 2) {
				// 남쪽으로는 못 나감
				// TODO : 못 나가는 경우니까 버리기.
				return null;
			}
			else if(dir == 0) {
				// 동쪽
				int nextD = paths[p.dimension][0];
				return new Pair(p.r, 0,nextD);
			}
			else if(dir == 1) {
				//서쪽
				int nextD = paths[p.dimension][1];
				return new Pair(p.r, M-1 ,nextD);
			}
			else {
				// 북쪽
				// 이 경우엔 어느 평면에서 올라 가는지가 중요
				if(p.dimension == 0) {
					// 동 -> 윗면인 경우
					// M - 열 -> 행
					return new Pair(M - 1 - p.c , M - 1, 4);
				}
				else if(p.dimension == 1) {
					// 서 -> 윗면
					// 열 -> 행
					return new Pair(p.c, 0, 4);
				}
				
				else if(p.dimension == 2) {
					// 남 -> 윗면
					return new Pair(M - 1, p.c, 4);
				}
				else {
					// 북 -> 윗면
					// M - 열 
					return new Pair(0, M - 1 - p.c, 4);
				}
			}
		}
		
	}
	
	private static int calcDir(Pair p) {
		if(p.c >= M) {
			// 동쪽으로 이동
			return 0;
		}
		else if(p.c < 0) {
			// 서쪽으로 
			return 1;
		}
		else if(p.r < 0) {
			// 북쪽
			return 3;
		}
		else if(p.r >= M){
			//남쪽
			return 2;
		}
		else return -1;
	}
	
	static class Pair {
		int r; 
		int c;
		int dimension;
		
		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		Pair(int r, int c, int d) {
			this(r,c);
			this.dimension = d;
		}
	}
	
	static class Node {
		int r;
		int c;
		int d;
		int v;
		
		Node(int r, int c, int d, int v) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.v = v;
		}
	}
}


/*
 * 0 : 도로
 * 1 : 장애물
 * 2 : 시작 위치
 * 3 : 공간 표시
 * 4 : 도착지 
 * 
 * 동, 서 , 남 , 북, 윗면으로 단면도가 주어진다.
 * 
 * 풀이방법
 * 1. 시작 위치, 탈출 위치 찾기
 * 1-1. 3차원에서 2차원으로 나갈 때 규칙 찾아 구현하기
 * 2. 3차원 공간 이동 구현하기
 * 3. 2차원 평면  시간 이상 현상 고려한 평면 구하기
 */
