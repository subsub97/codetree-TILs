import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Stack<String> s = new Stack<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String par = br.readLine();

        for(int i =0; i < par.length(); i++) {
            if(par.substring(i,i+1).equals("(")) {
                // 열린 괄호라면 push
                s.push(par.substring(i,i+1));
            }
            else{
                // 닫힌 괄호라면 Top이 "(" 인 경우만

                if(s.size() > 0 && s.peek().equals("(")) {
                    s.pop();
                }
            }
        }

        if(s.size() == 0) {
            System.out.print("Yes");
        }
        else{
            System.out.print("No");
        }

    }
}