import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_STATION = 1000;

    public static void main(String[] args) throws IOException {
        //최소 비용과 최소 시간 구하기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int startA = Integer.parseInt(st.nextToken());
        int endB = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] graph = new int[MAX_STATION + 1][MAX_STATION + 1];

        for (int i = 1; i <= n; i++) {
            //1번 버스부터 n번 버스까지 노선 추가
            st = new StringTokenizer(br.readLine());

            int fare = Integer.parseInt(st.nextToken());
            int stationCount = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] stationsInfo = new int[stationCount];

            for (int j = 0; j < stationCount; j++) {
                stationsInfo[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < stationCount-1; j++) {
                //버스 노선별 연결된 정거장 추가
                for (int k = j+1; k < stationCount; k++) {
                    graph[stationsInfo[j]][stationsInfo[k]] = fare;
                }
            }
        }

        long[] dist = new long[MAX_STATION + 1];
        int[] path = new int[MAX_STATION + 1];

        for (int i = 0; i <= MAX_STATION ; i++) {
            dist[i] = 20_000_000_000L;
        }
        dist[startA] = 0; //시작지점 비용 0으로
        boolean[] visited = new boolean[MAX_STATION + 1];

        for (int i = 1; i <= MAX_STATION; i++) {
            int minIndex = -1;
            for (int j = 1; j <= MAX_STATION ; j++) {
                if(visited[j]) continue;

                if(minIndex == -1 || dist[minIndex] > dist[j]){
                    minIndex = j;
                }
            }

            visited[minIndex] = true;

            for (int j = 1; j <= MAX_STATION ; j++) {
                if(graph[minIndex][j] == 0) continue;

                if(dist[j] > dist[minIndex] + graph[minIndex][j]) {
                    dist[j] = dist[minIndex] + graph[minIndex][j];
                    path[j] = minIndex;
                }
            }
        }
            // 도착점부터 시작점으로 역 순회하면서 최단 경로 찾기
            int x = endB;
            Stack<Integer> minPaths = new Stack<>();
            int minCnt = 1;

            while(x != startA){
                minPaths.push(x);
                minCnt++;
                x = path[x];
            }

        System.out.print(dist[endB] + " ");
        System.out.println(minCnt);


    }
}