import java.io.*;
import java.util.*;

public class Main {
    public static int number;
    public static Queue<Node> q = new LinkedList<>();
    public static boolean[] visited = new boolean[1_000_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        number = Integer.parseInt(br.readLine());

        q.add(new Node(number, 0));
        bfs();

    }

    public static void bfs() {
        while(!q.isEmpty()) {
            Node curNode = q.poll();
            if(visited[curNode.number]) continue;
            
            visited[curNode.number] = true;

            int num = curNode.number;

            if(curNode.number == 1) {
                System.out.println(curNode.count);
                return;
            }
            
            q.add(new Node(num - 1, curNode.count + 1)); // 1을 뺀 값
            q.add(new Node(num + 1, curNode.count + 1)); // 1을 더한 값
            if(num % 2 ==0) {
                q.add(new Node(num / 2, curNode.count + 1)); // 2로 나눈 값
            }
            if(num % 3 == 0){
                q.add(new Node(num / 3, curNode.count + 1)); // 3으로 나눈 값
            }

        }
    }

    public static class Node{
        int number;
        int count;

        Node(int n, int cnt) {
            number = n ;
            count = cnt;
        }
    }
}