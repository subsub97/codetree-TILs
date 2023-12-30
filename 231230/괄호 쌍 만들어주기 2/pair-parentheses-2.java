import java.util.*;
import java.io.*;

public class Main {

    static String parenthesis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        parenthesis = br.readLine();
        int answer = 0;

        for(int i = 0; i < parenthesis.length(); i++) {
            if(checkOpenParenthesis(i)) {
                for(int j = i + 2; j < parenthesis.length(); j++ ) {
                    if(checkCloseParenthesis(j)){
                        answer++;
                    }
                }
            }
        }

        System.out.print(answer);
        
    }

    static boolean checkOpenParenthesis(int startIndex) {
        if (startIndex == parenthesis.length() - 1 ) {
            return false;
        }
        if(parenthesis.charAt(startIndex) != '(' || parenthesis.charAt(startIndex + 1) != '(') {
            return false;
        }
        return true;
    }

    static boolean checkCloseParenthesis(int startIndex) {
        if (startIndex == parenthesis.length() - 1 ) {
            return false;
        }
        if(parenthesis.charAt(startIndex) != ')' || parenthesis.charAt(startIndex + 1) != ')') {
            return false;
        }
        return true;
    }
}