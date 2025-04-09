import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int[][] walls;
	static int[][] maze;
	static Human[] humans;
	static boolean[] isFinish;
	static boolean[][] minPath;
	
	static int N,M,K,fcnt;
	static int er, ec;
	static int[] drs = {1,-1,0,0};
	static int[] dcs = {0,0,1,-1};
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	fcnt = 0;
    	walls = new int[N][N];
    	maze = new int[N][N];
    	humans = new Human[M + 1];
    	isFinish = new boolean[M + 1];
    	
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < N; j++) {
				walls[i][j] = Integer.parseInt(st.nextToken());
			}
    	}
    	
    	for(int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		
    		int r = Integer.parseInt(st.nextToken()) - 1;
    		int c = Integer.parseInt(st.nextToken()) - 1;
    		
    		maze[r][c]++;
    		humans[i + 1] = new Human(r,c,0,null); 
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	er = Integer.parseInt(st.nextToken()) - 1;
    	ec = Integer.parseInt(st.nextToken()) - 1;
    	
    	maze[er][ec] = -1; // 출구 표시
    	
    	for(int i = 0; i < K; i++) {
    		int[][] tempMaze = new int[N][N];
    		tempMaze[er][ec] = -1;
    		// 최단 거리 구하기
    		for(int j = 1 ; j <= M; j++) {
    			if(isFinish[j]) continue;
    			
    			Human curH = humans[j];
    			// 거리 받아오고
    			int curMin = getMinDistance(curH.r , curH.c);
    			// 이동하기    			
    			int r = curH.r;
    			int c = curH.c;
    			
    			for(int d = 0; d < 4; d++) {
    				int nr = curH.r + drs[d];
    				int nc = curH.c + dcs[d];
    				int nMin = getMinDistance(nr,nc);
    				
    				if(inRange(nr,nc) && curMin > nMin && walls[nr][nc] <= 0) {
    					// 이동 가능한 경우
    					curH.r = nr;
    					curH.c = nc;
    					curH.cnt++;
    					
    					//근데 출구라면?
    					if(nr == er && nc == ec) {
    						isFinish[j] = true;
    						fcnt++;
    					}
    					break;
    				}
    			}
    			
    			if(!isFinish[j]) {
    				tempMaze[curH.r][curH.c]++;
    			}
    		}
    		//TODO copy
    		maze = copyArr(tempMaze);
    		boolean flag = false;
    		int rotR = 0;
    		int rotC = 0;
    		int size = 0;
    		// 회전 가능 사각형 찾기
    		for(int j = 2; j <= N; j++) {
    			// j크기의 정사각형을 만들기
    			for(int r = 0; r <= N - j; r++) {
    				for(int c = 0; c <= N - j; c++) {
    					if(canMakeSquare(r,c,j)) {
    						flag = true;
    						rotR = r;
    						rotC = c;
    						size = j;
    						break;
    					}
    				}
    				if(flag) break;
    			}
    			if(flag) break;
    		}
    		
    		// 회전하기 
    		rotateSquareAndBreak(rotR, rotC, size);
    		
    		// 모두 탈출했으면 종료
    		if(fcnt == M) break;
    		
    	}
    	
    	int ans = 0;
    	
    	for(int i = 1; i <= M; i++) {
    		ans += humans[i].cnt;
    	}
    	er++;
    	ec++;
    	System.out.println(ans);
    	System.out.println(er +" " + ec);
    	
    	
    }
    
    static private void rotateSquareAndBreak(int r, int c, int size) {
    	// 회전 구간 돌리고
    	// 영향 안받은 구간 넣어주고
    	int[][] tempGrid = new int[size][size];
    	int[][] tempWall = new int[size][size];
    	
    	for(int j = 0; j < size; j++) {
    		for(int i = 0; i < size; i++) {
        		tempGrid[i][size - j - 1] = maze[j + r][i + c];
        		tempWall[i][size - j - 1] = walls[j + r][i + c];
        		
        		if(maze[j + r][i + c] > 0) {
        			for(int k = 1; k <= M; k++) {
        				Human h = humans[k];
        				
        				if(h.r == j + r && h.c == i + c) {
        					h.r = r + i;
        					h.c = size - j - 1 + c;
        				}
        			}
        		}
        		
        		if(tempWall[i][size - j - 1] > 0) {
        			// 벽 내구도 감소
        			tempWall[i][size - j - 1]--;
        		}
        	}
    	}
    	
    	//원본에다가 넣어주기
    	
    	for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size; j++) {
        		maze[r+i][c+j] = tempGrid[i][j];
        		walls[r+ i][c+j] = tempWall[i][j];
        		if(maze[r+i][c+j] == -1) {
        			er = r+i;
        			ec = c+j;
        		}
        	}
    	}
    	
    }
     
    static private int[][] copyArr(int[][] grid) {
    	int[][] newGrid = new int[N][N];
    	
    	for (int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				newGrid[i][j] = grid[i][j];
			}
		}
    	
    	return newGrid;
    }
    
    static private boolean canMakeSquare(int r, int c, int size) {
    	boolean existHuman = false;
    	boolean existExit = false;
    	
    	for(int i = 0; i < size; i++) {
    		for (int j = 0; j < size; j++) {
				//TODO MAZE도 갱신을 해둬야 한다.
    			if(maze[r + i][c + j] == -1) {
    				existExit = true;
    			}
    			else if(maze[r+i][c+j] > 0) {
    				existHuman = true;
    			}
			}
    		if(existExit && existHuman) return true;
     	}
    	
    	return false;
    }
    

    // 출구까지 최단 거리 구하는 함수
    static private int getMinDistance(int r , int c) {	
    	return Math.abs(r - er) + Math.abs(c - ec);
    }
    
    static private boolean inRange(int r, int c) {
    	return r >= 0 && r < N && c >= 0 && c <N;
    }
    
    static class Human {
    	int r;
    	int c;
    	int cnt;
    	Pair[] path;
    	
    	Human(int r, int c, int cnt, Pair[] p) {
    		this.r = r;
    		this.c = c;
    		this.cnt =cnt;
    		if(p != null) {
    			
    		
    		path = new Pair[p.length + 1];
    		
    		for(int i = 0 ; i < p.length; i++) {
    			path[i] = p[i];
    		}
    		
    		path[p.length] = new Pair(r,c);
    		}
    	}
    }
    
    static class Pair {
    	int r; 
    	int c;
    	
    	Pair(int r, int c){
    		this.r = r;
    		this.c = c;
    	}
    }
}


/*
 * [1] 벽
 *  1 이상 9 이하의 내구도를 갖고 있다.
 * 	회전할 때, 내구도가 1 씩 갂인다.
 * 내구도가 0이 되면, 빈 칸으로 변경된다.
 * 
 * []출구
 * 참가자가 해당 칸에 도달하면, 즉시 탈출한다.
 * 
 * 규칙
 * 1. 1초마다 모든 참가자는 한 칸씩 움직인다.
 * 2. 모든 참가자는 동시에 움직인다.
 * 3. 상하좌우로 움직일 수 있다.
 * 4. 벽이 없는 경우만 이동할 수 있다.
 * 5. 움직이는 칸은 현재 머물러 있던 칸보다 
 * 	  출구까지의 최단 거리가 가까워야 한다.
 * 6. 움직일 수 있는 칸이 2개 이상이면 상하로 움직이는 것이 우선
 * 7. 움직일 수 없는 경우 움직이지 않는다.
 * 8. 한칸에 2명 이상의 참가자가 있을 수 있다.
 * 
 * [] 미로 회전
 * 모든 참가자가 이동 끝냈으면, 미로가 회전한다.
 * 1. 한 명 이상의 참가자와 출구를 포함한 가장 작은 정사각형을 잡는다.
 * 2. 가장 작은 크기를 갖는 정사각형이 2개 이상이라면
 *    좌상단 r 좌표가 작은 것이 우선
 *    그래도 같으면 c 좌표가 작은 것이 우선
 * 3. 선택한 정사각형을 90도 회전하고 내구도 1씩 감소
 * 
 * 
 * K초 반복 후 모든 참가자들의 이동 거리 합 과, 출구 좌표를 출력
*/
