import java.util.*;
import java.io.*;

// """
// 풀이
// 1. 최초 배열에 입력받은 인덱스 위치와 값을 기억한다.
// 2. 값을 기준으로 정렬을 한다.
// 3. 정렬된 위치를 기록한다.
// 4. 최초 인덱스 위치를 기준으로 정렬한다. 
// 5. 순서대로 3번에서 기록한 값을 출력한다.
// """
class NumberLocation {
    int initIndex;
    int sortingIndex = 0;
    int number;

    NumberLocation(int i, int n) {
        this.initIndex = i;
        this.number = n;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        final int n;
        NumberLocation[] numberList;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        numberList = new NumberLocation[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) { //주어진 숫자번호와 인덱스 위치를 배열에 추가
            int number = Integer.parseInt(st.nextToken());
            numberList[i] = new NumberLocation(i,number);
        }
        
        Arrays.sort(numberList, (a,b) -> a.number - b.number);

        for(int i = 0; i < n; i++) {
            // 정렬이된 index를 기록
            numberList[i].sortingIndex = i+1;
        }

        Arrays.sort(numberList, (a,b) -> a.initIndex - b.initIndex);
        
        for(NumberLocation num: numberList) {
            System.out.print(num.sortingIndex + " ");
        }
    }
}