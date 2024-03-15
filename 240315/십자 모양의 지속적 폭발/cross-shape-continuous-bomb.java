import java.util.Scanner;

public class Main {
    public static final int OUT_OF_GRID = -1;
    public static final int MAX_N = 200;
    
    public static int n, m;
    public static int[][] grid = new int[MAX_N][MAX_N];
    public static int[][] nextGrid = new int[MAX_N][MAX_N];
    
    public static boolean inBombRange(int x, int y, int centerX, int centerY, int bombRange) {
        return (x == centerX || y == centerY) && 
               Math.abs(x - centerX) + Math.abs(y - centerY) < bombRange;
    }
    
    public static void bomb(int centerX, int centerY) {
        // Step1. nextGrid 값을 0으로 초기화합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextGrid[i][j] = 0;
    
        // Step2. 폭탄이 터질 위치는 0으로 채워줍니다.
        int bombRange = grid[centerX][centerY];
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(inBombRange(i, j, centerX, centerY, bombRange))
                    grid[i][j] = 0;
        
        // Step3. 폭탄이 터진 이후의 결과를 nextGrid에 저장합니다.
        for(int j = 0; j < n; j++) {
            int nextRow = n - 1;
            for(int i = n - 1; i >= 0; i--) {
                if(grid[i][j] > 0)
                    nextGrid[nextRow--][j] = grid[i][j];
            }
        }
        
        // Step4. grid로 다시 값을 옮겨줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = nextGrid[i][j];
    }
    
    // 해당 col 열에 폭탄이 터질 위치를 구합니다.
    // 없다면 OUT_OF_GRID를 반환합니다.
    public static int getBombRow(int col) {
        for(int row = 0; row < n; row++)
            if(grid[row][col] != 0)
                return row;
        
        return OUT_OF_GRID;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        
        // m번에 걸쳐 폭탄이 터집니다.
        while(m-- > 0) {
            int bombCol = sc.nextInt();
            bombCol--;
            
            // 폭탄이 터지게 될 위치를 구합니다.
            int bombRow = getBombRow(bombCol);
            
            if(bombRow == OUT_OF_GRID)
                continue;
            
            bomb(bombRow, bombCol);
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++)
                System.out.print(grid[i][j] + " ");
            System.out.println();
        }
    }
}