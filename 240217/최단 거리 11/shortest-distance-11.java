import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n+1][n+1]; //인접 행렬 그래프 생성

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[x][y] = w;
            graph[y][x] = w;
        }
        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] dist = new int[n+1];
        for (int i = 1; i <= n; i++) {
            dist[i] = (int) 1e9;
        }

        dist[end] = 0;
        boolean[] visited = new boolean[n + 1];

        int idx = start;

        //도착점을 기준으로 다익스트라 실행
        for (int i = 1; i <= n; i++) {
            int minIndex = -1;
            for (int j = 1; j <= n; j++) {
                if(visited[j]) continue;

                if(minIndex == -1 || dist[minIndex]  > dist[j])
                    minIndex = j;
            }

            visited[minIndex] = true;

            for (int j = 1; j <= n; j++) {
                if(graph[minIndex][j] == 0) continue;

                dist[j] = Math.min(dist[j], dist[minIndex] + graph[minIndex][j]);
            }
        }
        bw.write(dist[start] + "\n");

        // 다익스트라 구현완료
        int x = start;
        bw.write(x + " ");

        while(x != end) {
            for (int i = 1; i <= n; i++) {
                if(graph[i][x] == 0) continue;
                if(dist[i] + graph[i][x] == dist[x]) {
                    x = i;
                    break;

                }
            }
            bw.write(x + " ");
        }

        bw.close();
    }
}