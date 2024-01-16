import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        String string = br.readLine();
        
        for (int i = 1; i <= string.length() ; i++) {
            // 글자 개수를 정해줄 for문
            boolean check = true;

            for (int j = 0; j < n-i; j++) {
                // 어디서 부터 시작해서 볼건지
                String curWord = string.substring(j,j+i);

                for (int k = j+1; k <= n-i ; k++) {
                    String compareWord = string.substring(k,k+i);
                    if(curWord.equals(compareWord)){
                        check = false;
                    }
                }
                if(!check) break;
            }

            if(check) {
                System.out.println(i);
                break;
            }
        }

    }
}