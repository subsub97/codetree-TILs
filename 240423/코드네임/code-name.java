import java.util.*;
public class Main {

    static class CodeName{

        String code;
        int score; 

        CodeName(String code, int score){
            this.code = code;
            this.score = score;
        }
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CodeName[] codeNames = new CodeName[5];
        int minScore = 200;
        String minCode="";
        for (int i=0; i<5; i++){
            String code = sc.next();
            int score = sc.nextInt();
            codeNames[i]= new CodeName(code,score);
            
            if(minScore > score){
                minScore = score;
                minCode = code;
            }
       
        }
        System.out.println(minCode +" "+ minScore);
    }

        
    
}