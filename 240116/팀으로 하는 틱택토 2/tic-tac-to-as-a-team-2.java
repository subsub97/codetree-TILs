import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        
        int[][] tictackto = new int[3][3];
        
        for (int i = 0; i < 3; i++) {
            String num = br.readLine();
            for (int j = 0; j < 3; j++) {
                tictackto[i][j] = Integer.parseInt(num.substring(j,j+1));
            }   
        }

        int cnt = 0;
        
        for (int i = 1; i <= 9; i++) {
            for (int j = i+1; j <= 9 ; j++) {
                // i와 j가 한팀

                for (int k = 0; k < 3; k++) {
                    if(k == 0){
                        if((tictackto[0][0] == i || tictackto[0][0] == j) &&
                                (tictackto[1][1] == i || tictackto[1][1] == j) &&
                                (tictackto[2][2] == i || tictackto[2][2] == j )) {
                            // 가로 틱텍토 성립인 경우
                            if(tictackto[0][0] != tictackto[1][1] || tictackto[0][0] != tictackto[2][2]) {
                                cnt++;
                            }
                        }

                        if((tictackto[0][2] == i || tictackto[0][2] == j) &&
                                (tictackto[1][1] == i || tictackto[1][1] == j) &&
                                (tictackto[2][0] == i || tictackto[2][0] == j )) {
                            // 가로 틱텍토 성립인 경우
                            if(tictackto[0][2] != tictackto[1][1] || tictackto[0][2] != tictackto[2][0]) {
                                cnt++;
                            }
                        }
                    }

                    if((tictackto[0][k] == i || tictackto[0][k] == j ) &&
                            (tictackto[1][k] == i || tictackto[1][k] == j) &&
                            (tictackto[2][k] == i || tictackto[2][k] ==j )) {
                        // 가로 틱텍토 성립인 경우
                        if(tictackto[0][k] != tictackto[1][k] || tictackto[0][k] != tictackto[2][k]) {
                          cnt++;
                        }
                    }


                    if((tictackto[k][0] == i || tictackto[k][0] == j) &&
                            (tictackto[k][1] == i || tictackto[k][1] == j) &&
                            (tictackto[k][2] == i || tictackto[k][2] == j )) {
                        // 가로 틱텍토 성립인 경우
                        if(tictackto[k][0] != tictackto[k][1] || tictackto[k][0] != tictackto[k][2]) {
                            cnt++;
                        }
                    }


                }

            }
        }
        System.out.println(cnt);
    }
}