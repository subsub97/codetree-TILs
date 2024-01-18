import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int[] devA = new int[n];
        int[] devB = new int[n];

        String[][] gameRule = {{"가위", "바위", "보"},{"가위","보","바위"},
                {"바위","가위","보"}, {"바위","보","가위"}, {"보","바위","가위"},{"보","가위","바위"}};

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            devA[i] = Integer.parseInt(st.nextToken());
            devB[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;

        for (int i = 0; i < 6; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                String a = gameRule[i][devA[j]-1];
                String b = gameRule[i][devB[j]-1];

                if(a.equals("가위") && b.equals("보")) {
                    cnt++;
                }
                else if(a.equals("바위") && b.equals("가위")){
                    cnt++;
                }
                else if(a.equals("보") && b.equals("바위")) {
                    cnt++;
                }
            }
            ans = Math.max(cnt, ans);
        }

        System.out.println(ans);



    }
}