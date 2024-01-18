import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_SIZE = 10;
    public static char[][] grid = new char[MAX_SIZE][MAX_SIZE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int rowL = 0,colL = 0, rowB= 0,colB = 0,rowR=0,colR=0;

        for (int i = 0; i < 10; i++) {
            String st = br.readLine();
            for (int j = 0; j < 10; j++) {
                grid[i][j] = st.charAt(j);
                if(grid[i][j] == 'L') {
                    rowL = i;
                    colL = j;
                } else if (grid[i][j] == 'B') {
                    rowB = i;
                    colB = j;
                }
                else if(grid[i][j] == 'R'){
                    rowR = i;
                    colR = j;
                }
            }
        }
        if((rowB == rowL && rowB == rowR) && ((colB > colR && colR > colL) || (colB<colR && colL > colR))){
            System.out.println((Math.abs(rowB - rowL) + Math.abs(colB-colL)+1));    
        }
        else if((colB == colL && colB == colR) && ((rowB > rowR && rowR > rowL) || (rowB<rowR && rowL > rowR)) ){
            System.out.println((Math.abs(rowB - rowL) + Math.abs(colB-colL)+1));    
        }
        else{
            System.out.println((Math.abs(rowB - rowL) + Math.abs(colB-colL)-1));
        }
        
    }


}