import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] people = new int[d+1];
        int[] cheezes = new int[d+1];
        int[] times = new int[d+1];

        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(br.readLine());
            people[i] = Integer.parseInt(st.nextToken());
            cheezes[i] = Integer.parseInt(st.nextToken());
            times[i] = Integer.parseInt(st.nextToken());
        }

        int[] sickTimes = new int[s];
        int[] patients = new int[s];

        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            patients[i] = Integer.parseInt(st.nextToken());
            sickTimes[i] = Integer.parseInt(st.nextToken());
        }

        int[] rottenCheeze = new int[m+1];

        for (int i = 0; i < s; i++) {
            // 환자와 발생시간 고정하여 찾기
            int patient = patients[i];
            int sickTime = sickTimes[i];
            for (int j = 0; j < d; j++) {
                if(patient == people[j] && times[j] < sickTime) {
                    if(rottenCheeze[cheezes[j]] == 3) continue;
                    rottenCheeze[cheezes[j]] = 1;
                }else if(patient == people[j] && sickTime <= times[j]){
                    rottenCheeze[cheezes[j]] = 3;
                }
            }
            for (int j = 1; j <= m; j++) {
                if(rottenCheeze[j] == 0){
                    rottenCheeze[j] = 3;
                }
            }
        }

        int maxPatients = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < d; j++) {
                if(i == people[j] && rottenCheeze[cheezes[j]] == 1){
                    maxPatients++;
                    break;
                }
            }
        }
        System.out.println(maxPatients);
    }
}