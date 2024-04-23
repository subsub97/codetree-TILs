import java.util.*;
public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int count = 0; 
        int n = sc.nextInt();
        int k = sc.nextInt();
        String t = sc.next();
        String[] strArr = new String[n];
        for(int i=0; i<n; i++){
            strArr[i] = sc.next();
        }
        Arrays.sort(strArr);
        for(String word : strArr){
            if(word.startsWith(t)) {
                count+=1;
            }
            if(count == k){
                System.out.println(word);
                return;
            }
        }
    }
}