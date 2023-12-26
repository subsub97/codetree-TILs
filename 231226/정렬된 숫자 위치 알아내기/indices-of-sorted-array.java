import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine().trim());
        ClassA[] arr = new ClassA[cnt];
        String str = br.readLine();
        String[] strings = str.split(" ");
        for (int i = 0; i < cnt; i++) {
            arr[i] = new ClassA(Integer.parseInt(strings[i]), i+1);
        }

        Arrays.sort(arr);

        int[] answer = new int[cnt];

        for(int i = 0; i < cnt; i++)
            answer[arr[i].getRank()-1] = i+1; // 인덱스 보정

        // 출력:
        for(int i = 0; i < cnt; i++){
            System.out.print(answer[i] + " ");
        }

    }
}

class ClassA implements Comparable<ClassA>{
    private int num;
    private int rank;

    public ClassA(int num, int rank) {
        this.num = num;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public int compareTo(ClassA o) {
        if(this.num != o.num)
            return this.num - o.num;
        return this.rank - o.rank;
    }
}