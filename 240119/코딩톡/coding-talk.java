import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static class Developer {
        char name;
        int info;

        Developer(char name, int info) {
            this.name = name;
            this.info = info;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        Developer[] developers = new Developer[n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            developers[i] = new Developer(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
        }

        int[] unreaders = new int[n];

        int ans = 0;
        int pre = -1;
        for (int i = 0; i < n; i++) {
            // A 개발자부터 고정
            char curDeveloper = (char)('A'+i);
            for (int j = 0; j < m; j++) {
                if(curDeveloper == developers[j].name && p-1 <= j){
                    if(developers[j].info == 0 && p-1 == j){
                        for (int k = 0; k < n; k++) {
                            unreaders[k] = 3;
                        }
                    }
                    unreaders[developers[j].name - 'A'] = 3;
                }
                if (pre < developers[j].info) {
                    for (int k = 0; k < n; k++) {
                        if(unreaders[k] != 3) unreaders[k] = 0;
                    }
                    if(unreaders[developers[j].name - 'A'] == 3)  continue;
                    unreaders[developers[j].name - 'A'] = 1;
                }
                if(p-1 == j){
                    for (int k = 0; k < n; k++) {
                        if(unreaders[k] == 1)
                            unreaders[k] = 3;
                    }
                }
                pre = developers[j].info;
            }
        }

        for (int i = 0; i < n; i++) {
            if(unreaders[i] == 0) {
                char alpa = (char)(int)('A'+i);
                System.out.print(alpa + " ");
            }
        }
    }
}